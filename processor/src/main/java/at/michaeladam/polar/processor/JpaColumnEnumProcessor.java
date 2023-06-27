package at.michaeladam.polar.processor;

import com.google.auto.service.AutoService;
import jakarta.persistence.Column;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"jakarta.persistence.Entity"})
@SupportedSourceVersion(SourceVersion.RELEASE_19)
@AutoService(Processor.class)
public class JpaColumnEnumProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //how can I see if this is executed?
        Messager messager = super.processingEnv.getMessager();
        messager .printMessage(Diagnostic.Kind.NOTE, "Starting JpaColumnEnumProcessor");

        long startTime = System.currentTimeMillis();

        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : annotatedElements) {
                if (element.getKind() == ElementKind.CLASS) {
                    TypeElement typeElement = (TypeElement) element;
                    generateEnumForEntity(typeElement);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        messager.printMessage(Diagnostic.Kind.NOTE, "Finished JpaColumnEnumProcessor in " + (endTime - startTime) + "ms");
        return true;
    }

    private void generateEnumForEntity(TypeElement typeElement) {
        String packageName = getPackageName(typeElement);
        String enumClassName = typeElement.getSimpleName().toString() + "Column";
        String qualifiedEnumClassName = packageName + "." + enumClassName;

        List<String> columnNames = getColumnNames(typeElement);

        StringBuilder enumBuilder = new StringBuilder();
        enumBuilder.append("package ").append(packageName).append(";\n\n");
        enumBuilder.append("public enum ").append(enumClassName).append(" {\n");

        for (String columnName : columnNames) {
            enumBuilder.append("    ").append(columnName.toUpperCase()).append(",\n");
        }

        enumBuilder.append("}");

        try (Writer writer = processingEnv.getFiler().createSourceFile(qualifiedEnumClassName).openWriter()) {
            writer.write(enumBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPackageName(TypeElement typeElement) {
        Element enclosingElement = typeElement.getEnclosingElement();
        if (enclosingElement instanceof PackageElement packageElement) {
            return packageElement.getQualifiedName().toString();
        }
        return "";
    }

    private List<String> getColumnNames(TypeElement typeElement) {
        List<String> columnNames = new ArrayList<>();
        for (Element element : typeElement.getEnclosedElements()) {
            if (element.getKind() == ElementKind.FIELD) {
                String name = "";
                if(element.getAnnotation(Column.class) != null){
                    Column column = element.getAnnotation(Column.class);
                    name = column.name().toLowerCase();
                }
                if (name.isEmpty()) {
                    name = element.getSimpleName().toString();
                }
                columnNames.add(name);
            }
        }
        return columnNames;
    }
}

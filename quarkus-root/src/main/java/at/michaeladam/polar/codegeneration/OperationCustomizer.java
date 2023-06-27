package at.michaeladam.polar.codegeneration;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;

import java.util.Map;

/**
 * This class changes openapi generated code to fit the needs of the project.
 *
 */
public class OperationCustomizer implements OASFilter {

    //set Tag to Get, Post, Put, Delete MethodName
    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        OASFilter.super.filterOpenAPI(openAPI);
        Map<String, PathItem> paths = openAPI.getPaths().getPathItems();
        for (Map.Entry<String, PathItem> path : paths.entrySet()) {
            PathItem pathItem = path.getValue();
            for (Map.Entry<PathItem.HttpMethod, Operation> httpMethodOperationEntry : pathItem.getOperations().entrySet()) {
                PathItem.HttpMethod httpMethod = httpMethodOperationEntry.getKey();
                Operation operation = httpMethodOperationEntry.getValue();
                operation.setOperationId( httpMethod.name().toLowerCase()+ capitalize(operation.getOperationId()));
            }
        }
    }

    private String capitalize(String operationId) {
        //if operationId starts with any of the http methods, remove it
        for (PathItem.HttpMethod httpMethod : PathItem.HttpMethod.values()) {
            if (operationId.startsWith(httpMethod.name().toLowerCase())) {
                operationId = operationId.substring(httpMethod.name().length());
            }
        }
        return operationId.substring(0, 1).toUpperCase() + operationId.substring(1);
    }
}

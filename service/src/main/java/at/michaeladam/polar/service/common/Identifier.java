package at.michaeladam.polar.service.common;

import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.UUID;

public class Identifier<T> {

    private UUID id;

    public Identifier() {
    }

    public Identifier(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return MessageFormat.format("{0}({1})", getEntityClass().getSimpleName(), id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Identifier<?> identifier) {
            return identifier.getId().equals(id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    public static <T> Identifier<T> fromString(String id) {
        return new Identifier<>(UUID.fromString(id));
    }

    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}

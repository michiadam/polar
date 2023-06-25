package at.michaeladam.polar.service.common;

import at.michaeladam.polar.persistence.common.ID;

import java.util.UUID;

public class Identifier<T> {

    private UUID id;

    public Identifier(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Identifier) {
            return ((Identifier) obj).getId().equals(id);
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

}

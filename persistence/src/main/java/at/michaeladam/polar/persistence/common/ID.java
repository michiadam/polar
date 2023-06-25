package at.michaeladam.polar.persistence.common;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
public class ID<T> implements Serializable, Comparable<ID<T>> {

    private UUID oid;

    protected ID() {
    }
    public ID(UUID id) {
        if(id != null) {
            this.oid = id;
        }
    }


    @NonNull
    public static <T> ID<T> generateID( ) {
        ID<T> id = new ID<>();
        id.setOid(UUID.randomUUID());
        return id;
    }


    @Override
    public @NonNull String toString() {
        return MessageFormat.format("ID: {0}", oid);
    }


    @Override
    public int compareTo(ID<T> o) {
        return oid.compareTo(o.oid);
    }
}
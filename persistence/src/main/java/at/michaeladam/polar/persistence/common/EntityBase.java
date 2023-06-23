package at.michaeladam.polar.persistence.common;


import jakarta.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EntityBase<T > {


    private int version;

    @PrePersist
    public void prePersist() {
        if (getId() == null) {
            setId(ID.generateID());
        }
        version = 1;
    }

    @PreUpdate
    public void preUpdate() {
        version++;
    }

    protected abstract ID<T> getId();

    protected abstract void setId(ID<T> id);

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }
}

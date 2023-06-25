package at.michaeladam.polar.persistence.common;


import jakarta.persistence.*;

@MappedSuperclass
public abstract class EntityBase<T > {

    @Id
    protected ID<T> id;
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

    public ID<T> getId() {
        return id;
    }

    public void setId(ID<T> id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }
}

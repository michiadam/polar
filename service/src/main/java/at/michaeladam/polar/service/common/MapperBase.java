package at.michaeladam.polar.service.common;

import at.michaeladam.polar.persistence.common.EntityBase;
import at.michaeladam.polar.persistence.common.ID;


public interface MapperBase<E extends EntityBase<E>,V> {



    default Identifier<V> toViewID(E project) {
        if(project == null)
            return null;
        return new Identifier<>(project.getId().getOid());
    }

    default ID<E> toEntityID(Identifier<V> id){
        if(id == null)
            return null;
        return new ID<>(id.getId());
    }
}

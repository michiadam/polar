package at.michaeladam.polar.persistence.common.dao;

import at.michaeladam.polar.persistence.common.EntityBase;
import at.michaeladam.polar.persistence.common.ID;

import java.util.List;
import java.util.Optional;

public interface IGenericDao<T extends EntityBase> {

    Class<T> getEntityClass();

    Optional<T> findOne(final ID<T> id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final ID<T> entityId);
}
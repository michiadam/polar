package at.michaeladam.polar.persistence.common.dao;

import at.michaeladam.polar.persistence.common.EntityBase;
import at.michaeladam.polar.persistence.common.ID;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends EntityBase<?>> implements IGenericDao<T>{

    @Inject
    protected EntityManager entityManager;


    @Override
    public abstract Class<T> getEntityClass();

    @Override
    public Optional<T> findOne(ID<T> id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
         return entityManager.createQuery("from " + getEntityClass().getName()).getResultList();
    }

    @Override
    @Transactional
    public T create(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
       entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void deleteById(ID<T> entityId) {
        Optional<T> entity = findOne(entityId);
        if(entity.isPresent()) {
            delete(entity.get());
        } else {
            throw new IllegalArgumentException("Entity with id " + entityId + " not found");
        }
    }
}

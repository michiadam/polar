package at.michaeladam.polar.persistence.kanban.repository;


import at.michaeladam.polar.persistence.common.dao.AbstractDao;
import at.michaeladam.polar.persistence.kanban.model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class KanbanRepository {

    @Inject
    protected EntityManager entityManager;

    @Inject
    protected ProjectDao projectDao;





}

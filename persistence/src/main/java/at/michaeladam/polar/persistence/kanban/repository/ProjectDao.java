package at.michaeladam.polar.persistence.kanban.repository;

import at.michaeladam.polar.persistence.common.dao.AbstractDao;
import at.michaeladam.polar.persistence.kanban.model.Project;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectDao extends AbstractDao<Project> {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }



}

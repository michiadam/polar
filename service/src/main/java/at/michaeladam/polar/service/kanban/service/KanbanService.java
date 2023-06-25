package at.michaeladam.polar.service.kanban.service;


import at.michaeladam.polar.persistence.kanban.model.Project;
import at.michaeladam.polar.persistence.kanban.repository.ProjectDao;
import at.michaeladam.polar.service.common.Identifier;
import at.michaeladam.polar.service.kanban.mapper.ProjectMapper;
import at.michaeladam.polar.service.kanban.view.ProjectView;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class KanbanService {


    protected static final ProjectMapper projectMapper = ProjectMapper.getInstance();

    @Inject
    protected ProjectDao projectDao;


    public Identifier<ProjectView> createProject(ProjectView projectView) {
        Project entity = projectMapper.toEntity(projectView);
        return projectMapper.toViewID(projectDao.create(entity));
    }

    public  Optional<ProjectView> getProject(Identifier<ProjectView> id) {
        Optional<Project> project = projectDao.findOne(projectMapper.toEntityID(id));
        return project.map(projectMapper::toView);
    }

    public List<ProjectView> findMyProjects() {
        return projectDao.findAll().stream().map(projectMapper::toView).toList();
    }
}

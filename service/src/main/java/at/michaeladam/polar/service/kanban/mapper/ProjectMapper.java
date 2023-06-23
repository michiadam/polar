package at.michaeladam.polar.service.kanban.mapper;


import at.michaeladam.polar.persistence.kanban.model.Project;
import at.michaeladam.polar.service.kanban.view.ProjectView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = WorkflowStatusMapper.class)
public interface ProjectMapper {

    static ProjectMapper getInstance() {
        return Mappers.getMapper(ProjectMapper.class);
    }

    Project toEntity(ProjectView projectView);

    ProjectView toView(Project project);


}

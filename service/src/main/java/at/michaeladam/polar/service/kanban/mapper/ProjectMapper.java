package at.michaeladam.polar.service.kanban.mapper;


import at.michaeladam.polar.persistence.common.ID;
import at.michaeladam.polar.persistence.kanban.model.Project;
import at.michaeladam.polar.service.common.IDMapper;
import at.michaeladam.polar.service.common.Identifier;
import at.michaeladam.polar.service.kanban.view.ProjectView;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;



@Mapper(uses = {
        WorkflowStatusMapper.class,
        IDMapper.class
}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ProjectMapper {

    static ProjectMapper getInstance() {
        return Mappers.getMapper(ProjectMapper.class);
    }

    Project toEntity(ProjectView projectView);

    ProjectView toView(Project project);


    default Identifier<ProjectView> toViewID(Project project) {
        if(project == null)
            return null;
        return new Identifier<>(project.getId().getOid());
    }

    default ID<Project> toEntityID(Identifier<ProjectView> id){
        if(id == null)
            return null;
        return new ID<>(id.getId());
    }
}

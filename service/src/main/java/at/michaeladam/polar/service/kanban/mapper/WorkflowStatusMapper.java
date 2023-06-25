package at.michaeladam.polar.service.kanban.mapper;

import at.michaeladam.polar.persistence.kanban.model.WorkflowStatus;
import at.michaeladam.polar.service.common.IDMapper;
import at.michaeladam.polar.service.kanban.view.WorkflowStatusView;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        IDMapper.class,
        IssueMapper.class
}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface WorkflowStatusMapper {

    static WorkflowStatusMapper getInstance() {
        return Mappers.getMapper(WorkflowStatusMapper.class);
    }

    @Mapping(target = "workflowType",source = "workflowType", qualifiedByName = "workflowType")
    WorkflowStatus toEntity(WorkflowStatusView workflowStatusView);

    WorkflowStatusView toView(WorkflowStatus workflowStatus);

    @Named("workflowType")
    default WorkflowStatus.WorkflowType workflowType( WorkflowStatus.WorkflowType workflowType) {
        if(workflowType == null)
            return WorkflowStatus.WorkflowType.NORMAL;
        return workflowType;
    }

}

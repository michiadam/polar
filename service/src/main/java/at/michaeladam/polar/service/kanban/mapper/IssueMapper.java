package at.michaeladam.polar.service.kanban.mapper;

import at.michaeladam.polar.persistence.kanban.model.WorkflowStatus;
import at.michaeladam.polar.service.kanban.view.WorkflowStatusView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueMapper {

    static IssueMapper getInstance() {
        return Mappers.getMapper(IssueMapper.class);
    }

    WorkflowStatus toEntity(WorkflowStatusView workflowStatusView);

    WorkflowStatusView toView(WorkflowStatus workflowStatus);
}

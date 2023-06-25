package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.persistence.common.ID;
import at.michaeladam.polar.persistence.kanban.model.WorkflowStatus;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class WorkflowStatusView  {
    private ID<WorkflowStatusView> id;
    private String name;
    private String description;
    private List<IssueView> issues;
    private WorkflowStatus.WorkflowType workflowType;

    public List<IssueView> getIssues() {
        if(issues == null)
            issues = new ArrayList<>();
        return issues;
    }
}

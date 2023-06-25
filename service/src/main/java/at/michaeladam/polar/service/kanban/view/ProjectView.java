package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.persistence.common.ID;
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
public class ProjectView {
    private ID<ProjectView> id;
    private String name;
    private String description;
    private List<WorkflowStatusView> workflowStatus;

    public List<WorkflowStatusView> getWorkflowStatus() {
        if (workflowStatus == null) {
            workflowStatus = new ArrayList<>();
        }
        return workflowStatus;
    }

}

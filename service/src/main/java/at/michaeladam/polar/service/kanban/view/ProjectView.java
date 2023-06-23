package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.persistence.common.ID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class ProjectView {
    private ID<ProjectView> oid;
    private String name;
    private String description;
    private Set<WorkflowStatusView> workflowStatuses = new LinkedHashSet<>();

}

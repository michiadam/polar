package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.persistence.common.ID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class WorkflowStatusView  {
    private ID<WorkflowStatusView> oid;
    private String name;
    private String description;
    private List<IssueView> issues;
}

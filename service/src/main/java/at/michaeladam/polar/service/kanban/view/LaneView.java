package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.persistence.kanban.model.Lane;
import at.michaeladam.polar.service.common.Identifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class LaneView {
    private Identifier<LaneView> id;
    private String name;
    private String description;
    private List<IssueView> issues;
    private Lane.WorkflowType workflowType;

    public List<IssueView> getIssues() {
        if(issues == null)
            issues = new ArrayList<>();
        return issues;
    }
}

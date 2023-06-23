package at.michaeladam.polar.persistence.kanban.model;


import at.michaeladam.polar.persistence.common.EntityBase;
import at.michaeladam.polar.persistence.common.ID;
import at.michaeladam.polar.persistence.kanban.KanbanException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Issue   {
    @EmbeddedId
    protected ID<Issue> pk;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "WORKFLOW_STATUS_ID", referencedColumnName = "oid")
    private WorkflowStatus workflowStatus;



    public static Issue prepareOpeningIssue(Project project, String message) throws KanbanException {
        Issue issue = new Issue();
        issue.workflowStatus = project
                .getDefaultWorkflowStatus()
                .orElseThrow(() -> KanbanException.noDefaultWorkflowStatusFound(project));
        issue.setTitle(message);
        return issue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(pk, issue.pk) && Objects.equals(title, issue.title) && Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, title, description);
    }
}

package at.michaeladam.polar.persistence.kanban.model;

import at.michaeladam.polar.persistence.common.EntityBase;
import at.michaeladam.polar.persistence.common.ID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class WorkflowStatus extends EntityBase<WorkflowStatus>  {


    @Enumerated(EnumType.STRING)
    private WorkflowType workflowType = WorkflowType.NORMAL;

    private String name;
    private String description;

    @OneToMany(mappedBy = "workflowStatus", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderColumn(name = "ISSUE_ORDER")
    private List<Issue> issues = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumns({@JoinColumn(name = "PROJECT_OID", referencedColumnName = "OID", nullable = false)})
    private Project project;


    public static WorkflowStatus createStatus( String done) {
        WorkflowStatus workflowStatus = new WorkflowStatus();
        workflowStatus.setName(done);
        return workflowStatus;
    }

    public void addIssue(Issue issue) {
        issue.setWorkflowStatus(this);
        this.issues.add(issue);
    }


    public enum WorkflowType {
        INITIAL, FINAL, NORMAL
    }
}


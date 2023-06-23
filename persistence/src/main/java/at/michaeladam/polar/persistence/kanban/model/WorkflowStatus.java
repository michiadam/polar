package at.michaeladam.polar.persistence.kanban.model;

import at.michaeladam.polar.persistence.common.EntityBase;
import at.michaeladam.polar.persistence.common.ID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class WorkflowStatus   {

    @EmbeddedId
    protected ID<WorkflowStatus> pk;

    @Enumerated(EnumType.STRING)
    private WorkflowType workflowType = WorkflowType.NORMAL;

    private String name;

    @OneToMany(mappedBy = "workflowStatus", orphanRemoval = true)
    @OrderColumn(name = "ISSUE_ORDER")
    private Set<Issue> issues = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "oid")
    private Project project;





    public static WorkflowStatus createStatus(Project project, String done) {
        WorkflowStatus workflowStatus = new WorkflowStatus();
        workflowStatus.setProject(project);
        workflowStatus.setName(done);
        return workflowStatus;
    }


    public enum WorkflowType {
        INITIAL, FINAL, NORMAL
    }
}


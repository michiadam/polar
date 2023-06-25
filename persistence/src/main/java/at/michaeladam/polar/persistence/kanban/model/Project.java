package at.michaeladam.polar.persistence.kanban.model;

import at.michaeladam.polar.persistence.common.EntityBase;
import at.michaeladam.polar.persistence.common.ID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Array;
import java.util.*;

@Entity
@Getter
@Setter
public class Project extends EntityBase<Project> {


    private String name;

    private String description;

    @OneToMany(mappedBy = "project", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderColumn(name = "WORKFLOW_STATUS_ORDER")
    private List<WorkflowStatus> workflowStatus = new ArrayList<>();

    public Optional<WorkflowStatus> getDefaultWorkflowStatus() {
        return workflowStatus.stream()
                .filter(workflowStatus -> workflowStatus.getWorkflowType() == WorkflowStatus.WorkflowType.INITIAL)
                .findFirst();
    }
    public void addWorkflowStatus(WorkflowStatus workflowStatus) {
        workflowStatus.setProject(this);
        this.workflowStatus.add(workflowStatus);
    }
}

package at.michaeladam.polar.persistence.kanban.model;

import at.michaeladam.polar.persistence.common.ID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
public class Project  {

    @Id
    protected ID<Project> pk;
    private String name;

    private String description;

    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private Set<WorkflowStatus> workflowStatuses = new LinkedHashSet<>();

    public Optional<WorkflowStatus> getDefaultWorkflowStatus() {
        return workflowStatuses.stream()
                .filter(workflowStatus -> workflowStatus.getWorkflowType() == WorkflowStatus.WorkflowType.INITIAL)
                .findFirst();
    }

}

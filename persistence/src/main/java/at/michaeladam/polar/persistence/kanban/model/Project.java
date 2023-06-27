package at.michaeladam.polar.persistence.kanban.model;

import at.michaeladam.polar.persistence.common.EntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Project extends EntityBase<Project> {


    private String name;

    private String description;

    @OneToMany(mappedBy = "project", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderColumn(name = "orderIndex")
    private List<Lane> lanes = new ArrayList<>();

    public Optional<Lane> getDefaultWorkflowStatus() {
        return lanes.stream()
                .filter(status -> status.getWorkflowType() == Lane.WorkflowType.INITIAL)
                .findFirst();
    }
    public void addWorkflowStatus(Lane lane) {
        lane.setProject(this);
        this.lanes.add(lane);
    }
}

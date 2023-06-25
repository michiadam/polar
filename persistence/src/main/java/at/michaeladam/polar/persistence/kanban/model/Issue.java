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
public class Issue  extends EntityBase<Issue> {

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "WORKFLOW_STATUS_ID", referencedColumnName = "oid")
    private WorkflowStatus workflowStatus;


}

package at.michaeladam.polar.persistence.kanban.model;


import at.michaeladam.polar.persistence.common.EntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Issue  extends EntityBase<Issue> {

    private String title;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "workflowStatus_oid", referencedColumnName = "oid", nullable = false)
    private Lane lane;

    @Column(nullable = false)
    private int orderIndex;
}

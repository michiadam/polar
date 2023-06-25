package at.michaeladam.polar.persistence.kanban.model;

import at.michaeladam.polar.persistence.common.EntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Lane extends EntityBase<Lane>  {


    @Enumerated(EnumType.STRING)
    private WorkflowType workflowType = WorkflowType.NORMAL;

    private String name;
    private String description;

    @Column(nullable = false)
    private int orderIndex;

    @OneToMany(mappedBy = "lane", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderColumn(name = "orderIndex")
    private List<Issue> issues = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PROJECT_OID", referencedColumnName = "OID", nullable = false)
    private Project project;



    public void addIssue(Issue issue) {
        issue.setLane(this);
        this.issues.add(issue);
    }


    public enum WorkflowType {
        INITIAL, FINAL, NORMAL
    }
}


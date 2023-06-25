package at.michaeladam.polar.persistence.kanban.repository;

import at.michaeladam.polar.persistence.common.dao.AbstractDao;
import at.michaeladam.polar.persistence.kanban.model.Issue;
import at.michaeladam.polar.persistence.kanban.model.Lane;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class LaneDao extends AbstractDao<Lane> {

    @Override
    public Class<Lane> getEntityClass() {
        return Lane.class;
    }

    public Optional<Issue> getIssueAt(Lane lane, int sourceIndex) {

        return entityManager.createQuery("SELECT i FROM Issue i WHERE i.lane = :lane AND i.orderIndex = :index", Issue.class)
                .setParameter("lane", lane)
                .setParameter("index", sourceIndex)
                .getResultStream()
                .findFirst();
    }


    public void addIssueAt(Lane lane, int targetIndex, Issue issue) {
        entityManager.createQuery("UPDATE Issue i SET i.orderIndex = i.orderIndex + 1 WHERE i.lane = :lane AND i.orderIndex >= :index")
                .setParameter("lane", lane)
                .setParameter("index", targetIndex)
                .executeUpdate();
        issue.setOrderIndex(targetIndex);
        issue.setLane(lane);
        entityManager.merge(issue);
    }

    public void shiftIssues(Lane sourceLane, int sourceIndex) {
        entityManager.createQuery("UPDATE Issue i SET i.orderIndex = i.orderIndex - 1 WHERE i.lane = :lane AND i.orderIndex > :index")
                .setParameter("lane", sourceLane)
                .setParameter("index", sourceIndex)
                .executeUpdate();
    }
}

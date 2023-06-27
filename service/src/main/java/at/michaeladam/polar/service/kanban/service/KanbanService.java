package at.michaeladam.polar.service.kanban.service;


import at.michaeladam.polar.persistence.kanban.model.Issue;
import at.michaeladam.polar.persistence.kanban.model.Lane;
import at.michaeladam.polar.persistence.kanban.model.Project;
import at.michaeladam.polar.persistence.kanban.repository.LaneDao;
import at.michaeladam.polar.persistence.kanban.repository.ProjectDao;
import at.michaeladam.polar.service.common.Identifier;
import at.michaeladam.polar.service.kanban.mapper.LaneMapper;
import at.michaeladam.polar.service.kanban.mapper.ProjectMapper;
import at.michaeladam.polar.service.kanban.view.ProjectView;
import at.michaeladam.polar.service.kanban.view.LaneView;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class KanbanService {


    protected static final ProjectMapper projectMapper = ProjectMapper.getInstance();
    protected static final LaneMapper laneMapper = LaneMapper.getInstance();

    @Inject
    protected ProjectDao projectDao;

    @Inject
    protected LaneDao laneDao;


    public Identifier<ProjectView> createProject(ProjectView projectView) {
        Project entity = projectMapper.toEntity(projectView);
        return projectMapper.toViewID(projectDao.create(entity));
    }

    public Optional<ProjectView> getProject(Identifier<ProjectView> id) {
        Optional<Project> project = projectDao.findOne(projectMapper.toEntityID(id));
        return project.map(projectMapper::toView);
    }

    public List<ProjectView> findMyProjects() {
        return projectDao.findAll().stream().map(projectMapper::toView).toList();
    }


    @Transactional
    public boolean moveIssue(int sourceIndex, int targetIndex, Identifier<LaneView> sourceLaneId, Identifier<LaneView> targetLaneId) {

        Optional<Lane> sourceLaneO = laneDao.findOne(laneMapper.toEntityID(sourceLaneId));
        if (sourceLaneO.isEmpty())
            return false;

        Optional<Lane> targetLaneO;
        if (Objects.equals(sourceLaneId, targetLaneId)) {
            targetLaneO = sourceLaneO;
        } else {
            targetLaneO = laneDao.findOne(laneMapper.toEntityID(targetLaneId));
        }
        if (targetLaneO.isEmpty()) {
            return false;
        }

        Lane sourceLane = sourceLaneO.get();
        Lane targetLane = targetLaneO.get();
        Optional<Issue> issueO = laneDao.getIssueAt(sourceLane, sourceIndex);
        if (issueO.isEmpty()) {
            return false;
        }
        Issue issue = issueO.get();
        laneDao.addIssueAt(targetLane, targetIndex, issue);
        laneDao.shiftIssues(sourceLane, sourceIndex);

        return true;


    }
}

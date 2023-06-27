package at.michaeladam.polar.resource.kanban;

import at.michaeladam.polar.service.common.Identifier;
import at.michaeladam.polar.service.kanban.service.KanbanService;
import at.michaeladam.polar.service.kanban.view.LaneView;
import at.michaeladam.polar.service.kanban.view.ProjectView;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import java.util.List;
import java.util.Optional;

@Path("/kanban")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class KanbanResource {


    @Inject
    protected KanbanService kanbanService;

    @GET
    @Path("/project/{project-id}")
    public Optional<ProjectView> getProject(@PathParam("project-id") Identifier<ProjectView> projectId) {
        return kanbanService.getProject(projectId);
    }

    @GET
    @Path("/findMyProjects")
    public List<ProjectView> findMyProjects() {
        return kanbanService.findMyProjects();
    }

    @POST
    @Path("/project/{project-id}/move")
    public boolean moveIssue(@PathParam("project-id") Identifier<ProjectView> projectId, MoveIssueRequest moveIssueRequest) {
        //Todo: handle security here, that's why projectId is passed but not yet used

          return kanbanService.moveIssue(
                    moveIssueRequest.sourceIndex,
                    moveIssueRequest.targetIndex,
                    moveIssueRequest.sourceLane,
                    moveIssueRequest.targetLane
            );
    }
    protected record MoveIssueRequest( int sourceIndex, int targetIndex, Identifier<LaneView> sourceLane, Identifier<LaneView> targetLane) {
    }
}

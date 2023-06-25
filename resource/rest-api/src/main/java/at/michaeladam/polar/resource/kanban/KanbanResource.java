package at.michaeladam.polar.resource.kanban;

import at.michaeladam.polar.service.common.Identifier;
import at.michaeladam.polar.service.kanban.service.KanbanService;
import at.michaeladam.polar.service.kanban.view.ProjectView;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

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

}

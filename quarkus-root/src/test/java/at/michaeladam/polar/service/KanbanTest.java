package at.michaeladam.polar.service;

import at.michaeladam.polar.persistence.kanban.model.Lane;
import at.michaeladam.polar.service.common.Identifier;
import at.michaeladam.polar.service.kanban.mapper.ProjectMapper;
import at.michaeladam.polar.service.kanban.service.KanbanService;
import at.michaeladam.polar.service.kanban.view.IssueView;
import at.michaeladam.polar.service.kanban.view.LaneView;
import at.michaeladam.polar.service.kanban.view.ProjectView;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

@QuarkusTest
class KanbanTest {


    protected static final Logger logger = Logger.getLogger(KanbanTest.class.getName());
    @Inject
    protected KanbanService kanbanService;

    protected static final ProjectMapper projectMapper = ProjectMapper.getInstance();

    @Test
    void setupDefaultProject() {
        ProjectView projectView = createTestingProject();

        Identifier<ProjectView> project = kanbanService.createProject(projectView);

        Assertions.assertNotNull(project);

        ProjectView loadedProject = kanbanService.getProject(project).get();
        logger.info("Loaded project: " + loadedProject);
        Assertions.assertEquals(projectView.getName(), loadedProject.getName());
        Assertions.assertEquals(projectView.getDescription(), loadedProject.getDescription());
        Assertions.assertEquals(3, loadedProject.getLanes().size());


        {
            LaneView todo = loadedProject.getLanes().stream()
                    .filter(workflowStatusView -> workflowStatusView.getName().equals("To Do"))
                    .findFirst()
                    .orElse(null);

            Assertions.assertEquals("To Do", todo.getName());
            Assertions.assertEquals("Tasks that need to be done.", todo.getDescription());
            Assertions.assertEquals(3, todo.getIssues().size());
        }
        {
            LaneView inProgress = loadedProject.getLanes().stream()
                    .filter(workflowStatusView -> workflowStatusView.getName().equals("In Progress"))
                    .findFirst()
                    .orElse(null);
            Assertions.assertEquals("In Progress", inProgress.getName());
            Assertions.assertEquals("Tasks that are currently being worked on.", inProgress.getDescription());
            Assertions.assertEquals(1, inProgress.getIssues().size());
            IssueView issue = inProgress.getIssues().get(0);
            Assertions.assertEquals("Testing the Kanban service", issue.getTitle());
            Assertions.assertEquals("This task was created to test the Kanban service.", issue.getDescription());
        }
    }

    @Test
    void createHugeProject(){
        int lanes = 5;
        int issuesPerLane = 10;
        ProjectView projectView = new ProjectView();
        projectView.setName("Huge Project");
        projectView.setDescription("This is a huge project with " + lanes + " lanes and " + issuesPerLane + " issues per lane.");
        for (int i = 0; i < lanes; i++) {
            LaneView laneView = new LaneView();
            laneView.setName("Lane " + i);
            laneView.setDescription("This is lane " + i + " of the huge project.");
            for (int j = 0; j < issuesPerLane; j++) {
                IssueView issueView = new IssueView();
                issueView.setTitle("Issue " + j + " of lane " + i);
                issueView.setDescription("This is issue " + j + " of lane " + i + " of the huge project.");
                laneView.getIssues().add(issueView);
            }
            projectView.getLanes().add(laneView);
        }

        Identifier<ProjectView> project = kanbanService.createProject(projectView);
        System.out.println("Created project: " + project);

        ProjectView loadedProject = kanbanService.getProject(project).get();
        Assertions.assertEquals(projectView.getName(), loadedProject.getName());
        Assertions.assertEquals(projectView.getDescription(), loadedProject.getDescription());
        Assertions.assertEquals(lanes, loadedProject.getLanes().size());

    }


    private static ProjectView createTestingProject() {
        ProjectView projectView = ProjectView.builder()
                .name("Kanban - Project")
                .description("""
                        This is a test project for the Kanban service.
                        It is used to test the functionality of the Kanban service.""")
                .build();

        LaneView todo = LaneView.builder()
                .name("To Do")
                .description("Tasks that need to be done.")
                .workflowType(Lane.WorkflowType.INITIAL)
                .build();

        LaneView inProgress = LaneView.builder()
                .name("In Progress")
                .description("Tasks that are currently being worked on.")
                .build();

        LaneView done = LaneView.builder()
                .name("Done")
                .description("Tasks that are done.")
                .build();

        projectView.getLanes().add(todo);
        projectView.getLanes().add(inProgress);
        projectView.getLanes().add(done);

        //add a few tasks to the project
        todo.getIssues()
                .add(createTestingTask("Test the Kanban service",
                        "This task was created to test the Kanban service."));

        todo.getIssues()
                .add(createTestingTask("Test the Kanban service1",
                        "This task was created to test the Kanban service."));
        todo.getIssues()
                .add(createTestingTask("Test the Kanban service2",
                        "This task was created to test the Kanban service."));


        inProgress.getIssues()
                .add(createTestingTask("Testing the Kanban service",
                        "This task was created to test the Kanban service."));

        return projectView;
    }

    private static IssueView createTestingTask(String name, String description) {
        return IssueView.builder()
                .title(name)
                .description(description)
                .build();
    }
}

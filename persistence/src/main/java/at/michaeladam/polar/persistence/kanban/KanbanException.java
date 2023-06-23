package at.michaeladam.polar.persistence.kanban;

import at.michaeladam.polar.persistence.kanban.model.Project;

import java.text.MessageFormat;

public class KanbanException extends Exception {

    public KanbanException(String message) {
        super(message);
    }

    public static KanbanException noDefaultWorkflowStatusFound(Project project) {
        return new KanbanException(
                MessageFormat.format(
                        "No default workflow status found for project {0}",
                        project.getName()
                )
        );
    }
}

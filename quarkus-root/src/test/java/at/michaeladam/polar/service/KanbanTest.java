package at.michaeladam.polar.service;

import at.michaeladam.polar.service.kanban.service.KanbanService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class KanbanTest {

    @Inject
    protected KanbanService kanbanService;

    @Test
    void testKanbanService() {
        String hello = kanbanService.getHello();
        Assertions.assertEquals("Hello", hello);
    }
}

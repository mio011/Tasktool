import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LifeCycleTest {

    @Test
    void testMakeWorkCycle_ProgressUpdate() {
        Project project = new Project("Test Project");
        Task task = new Task("Task 1", 8);
        project.addTask(task);

        Worker worker = mock(Worker.class);
        when(worker.getName()).thenReturn("Worker 1");
        when(worker.getWorkSpeed()).thenReturn(1.0);
        project.assignWorker(worker);

        Main.makeWorkCycle(List.of(project));

        assertTrue(task.getProgress() > 0, "Task progress should be updated.");
    }

    @Test
    void testMakeWorkCycle_TaskCompletion() {
        Project project = new Project("Test Project");
        Task task = new Task("Task 1", 8);
        project.addTask(task);

        Worker worker = mock(Worker.class);
        when(worker.getName()).thenReturn("Worker 1");
        when(worker.getWorkSpeed()).thenReturn(1.0);
        project.assignWorker(worker);

        for (int i = 0; i < 30; i++) {
            Main.makeWorkCycle(List.of(project));
        }
        assertTrue(task.isCompleted(), "Task should be marked as completed.");
    }

    @Test
    void testMakeWorkCycle_WorkerSick() {
        Project project = new Project("Test Project");
        Task task = new Task("Task 1", 8);
        project.addTask(task);

        Worker worker = new Worker("Worker 1", 1.0);
        project.assignWorker(worker);

        // Mock the static method getRandomInt
        try (MockedStatic<Main> mockedMain = mockStatic(Main.class)) {
            mockedMain.when(Main::getRandomInt).thenReturn(1); // Simulate worker being sick

            Main.makeWorkCycle(List.of(project));

            assertEquals(0, task.getProgress(), "Task progress should not change if the worker is sick.");
        }
    }
}
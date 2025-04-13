import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testProjectInitialization() {
        Project project = new Project("New Project");

        assertEquals("New Project", project.getName());
        assertTrue(project.getTasks().isEmpty());
        assertTrue(project.getWorkers().isEmpty());
    }

    @Test
    void testAddTask() {
        Project project = new Project("New Project");
        Task task = new Task("Task 1", 5);

        project.addTask(task);
        List<Task> tasks = project.getTasks();

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    void testAssignWorker() {
        Project project = new Project("New Project");
        Worker worker = new Worker("John Doe", 1.5);

        project.assignWorker(worker);
        List<Worker> workers = project.getWorkers();

        assertEquals(1, workers.size());
        assertEquals(worker, workers.get(0));
    }
}
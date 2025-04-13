import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void testAssignTasksToProject() {
        Project project = new Project("Test Project");
        Task task1 = new Task("Task 1", 5);
        Task task2 = new Task("Task 2", 3);
        List<Task> tasks = Arrays.asList(task1, task2);

        Manager manager = new Manager();
        manager.assignTasksToProject(project, tasks);

        List<Task> projectTasks = project.getTasks();
        assertEquals(2, projectTasks.size());
        assertTrue(projectTasks.contains(task1));
        assertTrue(projectTasks.contains(task2));
    }

    @Test
    void testAssignWorkersToProject() {
        Project project = new Project("Test Project");
        Worker worker1 = new Worker("Worker 1", 1.5);
        Worker worker2 = new Worker("Worker 2", 2.0);
        List<Worker> workers = Arrays.asList(worker1, worker2);

        Manager manager = new Manager();
        manager.assignWorkersToProject(project, workers);

        List<Worker> projectWorkers = project.getWorkers();
        assertEquals(2, projectWorkers.size());
        assertTrue(projectWorkers.contains(worker1));
        assertTrue(projectWorkers.contains(worker2));
    }
}
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test {         // alle Tests in einer Klasse


    @org.junit.jupiter.api.Test
    void testWorkerInitialization() {
        Worker worker = new Worker("John Doe", 1.5);

        assertEquals("John Doe", worker.getName());
        assertEquals(1.5, worker.getWorkSpeed());
    }

    @org.junit.jupiter.api.Test
    void testSetWorkSpeed() {
        Worker worker = new Worker("John Doe", 1.5);

        worker.setWorkSpeed(2.0);
        assertEquals(2.0, worker.getWorkSpeed());
    }

    @org.junit.Test
    public void testTaskInitialization() {
        Task task = new Task("Test Task", 10);
        assertEquals("Test Task", task.getTitle());
        assertEquals(10, task.getEstimatedHours());
        assertEquals(0, task.getActualHours());
        assertEquals(0, task.getProgress());
        assertFalse(task.isCompleted());
    }

    @org.junit.Test
    public void testAddProgress() {
        Task task = new Task("Test Task", 10);

        task.addProgress(5);
        assertEquals(5, task.getProgress());
        assertFalse(task.isCompleted());

        task.addProgress(5);
        assertEquals(10, task.getProgress());
        assertTrue(task.isCompleted());
    }

    @org.junit.Test
    public void testAddWorkTime() {
        Task task = new Task("Test Task", 10);

        task.addWorkTime(4.5);
        assertEquals(4.5, task.getActualHours());

        task.addWorkTime(3.5);
        assertEquals(8.0, task.getActualHours());
    }

    @org.junit.jupiter.api.Test
    void testProjectInitialization() {
        Project project = new Project("New Project");

        assertEquals("New Project", project.getName());
        assertTrue(project.getTasks().isEmpty());
        assertTrue(project.getWorkers().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testAddTask() {
        Project project = new Project("New Project");
        Task task = new Task("Task 1", 5);

        project.addTask(task);
        List<Task> tasks = project.getTasks();

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @org.junit.jupiter.api.Test
    void testAssignWorker() {
        Project project = new Project("New Project");
        Worker worker = new Worker("John Doe", 1.5);

        project.assignWorker(worker);
        List<Worker> workers = project.getWorkers();

        assertEquals(1, workers.size());
        assertEquals(worker, workers.get(0));
    }

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
    void testMakeWorkCycle_ProgressUpdate() {
        Project project = new Project("Test Project");
        Task task = new Task("Task 1", 8);
        project.addTask(task);

        Worker worker = new Worker("Worker 1", 1.0);
        project.assignWorker(worker);

        Main.makeWorkCycle(List.of(project));

        assertTrue(task.getProgress() > 0, "Task progress should be updated.");
    }

    @org.junit.jupiter.api.Test
    void testMakeWorkCycle_TaskCompletion() {
        Project project = new Project("Test Project");
        Task task = new Task("Task 1", 8);
        project.addTask(task);

        Worker worker = new Worker("Worker 1", 1.0);
        project.assignWorker(worker);

        for (int i = 0; i < 30; i++) {
            Main.makeWorkCycle(List.of(project));
        }
        assertTrue(task.isCompleted(), "Task should be marked as completed.");
    }


    @org.junit.jupiter.api.Test
    void testCalculateAverageEstimatedHours() {
        Project project = new Project("Test Project");
        Task task1 = new Task("Task 1", 5);
        Task task2 = new Task("Task 2", 10);
        project.addTask(task1);
        project.addTask(task2);

        Analyzer analyzer = new Analyzer();
        double average = analyzer.calculateAverageEstimatedHours(project);

        assertEquals(7.5, average);
    }

    @org.junit.jupiter.api.Test
    void testCalculateAverageEstimatedHours_EmptyTasks() {
        Project project = new Project("Test Project");

        Analyzer analyzer = new Analyzer();
        double average = analyzer.calculateAverageEstimatedHours(project);

        assertEquals(0, average);
    }

    @org.junit.jupiter.api.Test
    void testCalculateEfficiency() {
        Project project = new Project("Test Project");
        Task task1 = new Task("Task 1", 5);
        task1.addWorkTime(4);
        Task task2 = new Task("Task 2", 10);
        task2.addWorkTime(8);
        project.addTask(task1);
        project.addTask(task2);

        Analyzer analyzer = new Analyzer();
        double efficiency = analyzer.calculateEfficiency(project);

        assertEquals(0.8, efficiency);
    }

    @org.junit.jupiter.api.Test
    void testCalculateEfficiency_EmptyTasks() {
        Project project = new Project("Test Project");

        Analyzer analyzer = new Analyzer();
        double efficiency = analyzer.calculateEfficiency(project);

        assertEquals(0, efficiency);
    }

}

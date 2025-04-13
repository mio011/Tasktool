import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @Test
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

    @Test
    void testCalculateAverageEstimatedHours_EmptyTasks() {
        Project project = new Project("Test Project");

        Analyzer analyzer = new Analyzer();
        double average = analyzer.calculateAverageEstimatedHours(project);

        assertEquals(0, average);
    }

    @Test
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

    @Test
    void testCalculateEfficiency_EmptyTasks() {
        Project project = new Project("Test Project");

        Analyzer analyzer = new Analyzer();
        double efficiency = analyzer.calculateEfficiency(project);

        assertEquals(0, efficiency);
    }
}
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskInitialization() {
        Task task = new Task("Test Task", 10);
        assertEquals("Test Task", task.getTitle());
        assertEquals(10, task.getEstimatedHours());
        assertEquals(0, task.getActualHours());
        assertEquals(0, task.getProgress());
        assertFalse(task.isCompleted());
    }

    @Test
    public void testAddProgress() {
        Task task = new Task("Test Task", 10);

        task.addProgress(5);
        assertEquals(5, task.getProgress());
        assertFalse(task.isCompleted());

        task.addProgress(5);
        assertEquals(10, task.getProgress());
        assertTrue(task.isCompleted());
    }

    @Test
    public void testAddWorkTime() {
        Task task = new Task("Test Task", 10);

        task.addWorkTime(4.5);
        assertEquals(4.5, task.getActualHours());

        task.addWorkTime(3.5);
        assertEquals(8.0, task.getActualHours());
    }
}
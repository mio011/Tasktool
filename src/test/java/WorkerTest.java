import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    @Test
    void testWorkerInitialization() {
        Worker worker = new Worker("John Doe", 1.5);

        assertEquals("John Doe", worker.getName());
        assertEquals(1.5, worker.getWorkSpeed());
    }

    @Test
    void testSetWorkSpeed() {
        Worker worker = new Worker("John Doe", 1.5);

        worker.setWorkSpeed(2.0);
        assertEquals(2.0, worker.getWorkSpeed());
    }
}
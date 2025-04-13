import java.util.List;

public interface IProject {
    void addTask(Task task);
    void assignWorker(Worker worker);
    List<Task> getTasks();
    List<Worker> getWorkers();
    String getName();
}
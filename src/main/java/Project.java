import java.util.ArrayList;
import java.util.List;

public class Project implements IProject {
    private String name;
    private List<Task> tasks = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void assignWorker(Worker worker) {
        workers.add(worker);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public String getName() {
        return name;
    }
}

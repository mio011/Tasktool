import java.util.List;

public class Manager {

    public void assignTasksToProject(Project project, List<Task> tasks) {
        for(Task task : tasks) {
            project.addTask(task);
        }
    }

    public void assignWorkersToProject(Project project, List<Worker> workers) {
        for (Worker worker : workers) {
            project.assignWorker(worker);
        }
    }
}

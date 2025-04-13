import java.util.List;

public class Analyzer {

    public double calculateAverageEstimatedHours(Project project) {
        List<Task> tasks = project.getTasks();
        if (tasks.isEmpty()) return 0;

        int total = tasks.stream().mapToInt(Task::getEstimatedHours).sum();
        return (double) total / tasks.size();
    }

    public double calculateEfficiency(Project project) {
        List<Task> tasks = project.getTasks();
        if (tasks.isEmpty()) return 0;

        int estimated = tasks.stream().mapToInt(Task::getEstimatedHours).sum();
        double actual = tasks.stream().mapToDouble(Task::getActualHours).sum();
        return estimated == 0 ? 0 : actual / estimated;
    }
}

public class ProgressCalculator {
    public void addProgress(Task task, double hours) {
        task.addProgress(hours);
    }

    public boolean isTaskCompleted(Task task) {
        return task.getEstimatedHours() <= task.getProgress();
    }
}
public class Task implements ITask {
    private final String title;
    private final int estimatedHours;
    private double actualHours;
    private double progress = 0;

    public Task(String title, int estimatedHours) {
        this.title = title;
        this.estimatedHours = estimatedHours;
        this.actualHours = 0;
    }

    public String getTitle() {
        return title;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public double getActualHours() {
        return actualHours;
    }
    public void addWorkTime(double hours) {
        this.actualHours += hours;
    }
    public void addProgress(double hours) {
        progress += hours;
    }
    public boolean isCompleted() {
        return estimatedHours <= progress;
    }
    public double getProgress() {
        return progress;
    }
}

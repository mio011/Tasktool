public interface ITask {
    String getTitle();
    int getEstimatedHours();
    double getActualHours();
    void addWorkTime(double hours);
    void addProgress(double hours);
    boolean isCompleted();
    double getProgress();
}
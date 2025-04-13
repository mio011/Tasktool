public class Worker implements IWorker {
    private String name;
    private double workSpeed;

    public Worker(String name, double workSpeed) {
        this.name = name;
        this.workSpeed = workSpeed;
    }

    public String getName() {
        return name;
    }

    public double getWorkSpeed() {
        return workSpeed;
    }

    public void setWorkSpeed(double workSpeed) {
        this.workSpeed = workSpeed;
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String path = "src\\main\\resources\\workers";
        List<Worker> workers = readWorkersFromFile(path);
        List<Project> projects = List.of(new Project("Website Relaunch"));

        Manager manager = new Manager();
        manager.assignTasksToProject(projects.getFirst(), List.of(new Task("Design Startseite", 8), new Task("Implementierung Backend", 16), new Task("Testing", 6)));
        manager.assignWorkersToProject(projects.getFirst(), workers);

        makeWorkCycle(projects);

        Analyzer analyzer = new Analyzer();
        for (Project project : projects) {
            System.out.println(analyzer.calculateAverageEstimatedHours(project));
            System.out.println(analyzer.calculateEfficiency(project));
        }
    }

    public static List<Worker> readWorkersFromFile(String filepath) {
        List<Worker> workers = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double workspeed = Double.parseDouble(parts[1].trim());
                    workers.add(new Worker(name, workspeed));
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
        return workers;
    }

    public static void makeWorkCycle(List<Project> projects) {
        Random random = new Random();

        for (int day = 1; day <= 30; day++) {
            System.out.println("Day " + day);

            for (Project project : projects) {
                for (Worker worker : project.getWorkers()) {

                    boolean isSick = getRandomInt() < 2; // 10% Chance, krank
                    if (isSick) {
                        System.out.println(worker.getName() + " is sick today.");
                        continue;
                    }

                    // Worker arbeitet 8h pro Tag
                    double availableTime = 8.0;

                    // Arbeite durch die Tasks im Projekt
                    for (Task task : project.getTasks()) {
                        if (task.isCompleted()) continue;

                        double workSpeed = worker.getWorkSpeed();
                        double effectiveWork = availableTime / workSpeed;

                        double remaining = task.getEstimatedHours() - task.getProgress();

                        if (effectiveWork >= remaining) {
                            task.addProgress(remaining);
                            task.addWorkTime(remaining / workSpeed);
                            availableTime -= remaining / workSpeed;
                            System.out.println(worker.getName() + " completed task: " + task.getTitle());
                        } else {
                            task.addProgress(remaining);
                            task.addWorkTime(effectiveWork);
                            System.out.println(worker.getName() + " worked " + availableTime + "h on " + task.getTitle());
                            break; // Worker ist für heute fertig
                        }

                        if (availableTime <= 0) break;
                    }
                }
            }
        }
    }

    public static int getRandomInt() {
        Random random = new Random();
        return random.nextInt(20);
    }
}
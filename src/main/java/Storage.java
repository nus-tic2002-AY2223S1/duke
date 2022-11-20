import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Storage.
 */
public class Storage {

    private static final String filename = "tasks.txt";

    /**
     * Save tasks.
     *
     * @param tasks the tasks
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            String str;
            for (Task task : tasks) {
                str = "";
                if (task instanceof Todo) {
                    str += "T @@ ";
                } else if (task instanceof Deadline) {
                    str += "D @@ ";
                } else {
                    str += "E @@ ";
                }

                if (task.isDone) {
                    str += "0 @@ ";
                } else {
                    str += "1 @@ ";
                }

                str += task.getDescription();

                if (task instanceof Deadline) {
                    str += " @@ " + ((Deadline) task).getEndDate();
                } else if (task instanceof Event) {
                    str += " @@ " + ((Event) task).getEventDate();
                }

                str += "\n";
                fileWriter.write(str);
            }

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
        }
    }

    /**
     * Load tasks array list.
     *
     * @return the array list
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(new File(filename));

            while (fileReader.hasNextLine()) {
                String[] task = fileReader.nextLine().split(" @@ ");
                if (task[0].equals("T")) {
                    tasks.add(new Todo(task[2], task[1].equals("0")));
                } else if (task[0].equals("D")) {
                    tasks.add(new Deadline(task[2], task[1].equals("0"), LocalDate.parse(task[3])));
                } else if (task[0].equals("E")) {
                    tasks.add(new Event(task[2], task[1].equals("0"), task[3]));
                }
            }

        } catch (FileNotFoundException e) {
        }
        return tasks;
    }
}

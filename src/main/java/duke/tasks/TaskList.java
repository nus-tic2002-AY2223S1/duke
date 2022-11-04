package duke.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import duke.utils.Encoder;

/**
 * Main class to process restoring of Tasks
 */
public class TaskList {
    private final ArrayList<Task> arrayList;
    private String lastSaveTimeStamp;
    private String lastUserName;

    /**
     * Initializes the state of Tasks from file
     *
     * @param s Scanner object from file read
     */
    public TaskList(Scanner s) {
        Task[] arr = {};
        this.arrayList = new ArrayList<>(Arrays.asList(arr));

        if (s.hasNext()) {
            lastUserName = s.nextLine();
        }

        if (s.hasNext()) {
            lastSaveTimeStamp = s.nextLine();
        }

        while (s.hasNext()) {
            String inputLine = s.nextLine();
            String[] parsed = Encoder.decode(inputLine).split(",");

            switch (parsed[0]) {
            case "E":
                arrayList.add(new Event(parsed[2].trim(), new long[]{
                        Long.parseLong(parsed[3]),
                        Long.parseLong(parsed[4])}));
                break;
            case "T":
                arrayList.add(new Todo(parsed[2].trim(), Long.parseLong(parsed[3])));
                break;
            case "D":
                arrayList.add(new Deadline(parsed[2].trim(), Long.parseLong(parsed[3])));
                break;
            default:
                break;
            }
            if (Objects.equals(parsed[1], "1")) {
                arrayList.get(arrayList.size() - 1).markTask();
            }
        }
    }

    /**
     * Initializes an empty list of Task
     */
    public TaskList() {
        Task[] arr = {};
        this.arrayList = new ArrayList<>(Arrays.asList(arr));
    }

    public void addTask(Task t) {
        this.arrayList.add(t);
    }

    public ArrayList<Task> getList() {
        return this.arrayList;
    }

    public long getLastSaveTimeStamp() {
        return this.lastSaveTimeStamp == null ? 0 : Long.parseLong(this.lastSaveTimeStamp);
    }

    public String[] getLastInfo() {
        return this.lastSaveTimeStamp == null ? null : new String[]{this.lastUserName, this.lastSaveTimeStamp};
    }

    public void injectLastInfo(String s) {
        this.lastSaveTimeStamp = s;
    }

    public void injectLastUserName(String s) {
        this.lastUserName = s;
    }
}

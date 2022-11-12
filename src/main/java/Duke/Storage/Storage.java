package Duke.Storage;

import Duke.Tasks.Deadlines;
import Duke.Tasks.Events;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Represents the file used to store task data. */
public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String STORAGE_FILEPATH = "C:/Users/User/Documents/duke/data/duke.txt";
    protected static String filePath;

    /**
     *  Constructor of Storage
     *
     * @param filePath is the filepath to save or load data from
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     *  Save the task list to storage file
     *
     * @param taskList to be saved to the storage file
     */
    public void saveList(ArrayList<Task> taskList) {
        try {
            File directory = new File(filePath.substring(0,filePath.lastIndexOf("/")+1));
            assert (directory.exists());

            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList) {
                if (task instanceof Events) {
                    writer.write("Event | " + task.getStatus()
                            + " | " + task.getDescriptionOnly()
                            + " | " + ((Events) task).getDueDate()
                            + " | " + task.getTagging() + "\n");
                } else if (task instanceof Todo) {
                    writer.write("Todo | " + task.getStatus()
                            + " | " + task.getDescriptionOnly()
                            + " | " + task.getTagging() + "\n");
                } else if (task instanceof Deadlines) {
                    writer.write("Deadline | " + task.getStatus()
                            + " | " + task.getDescriptionOnly()
                            + " | " + ((Deadlines) task).getDueDate()
                            + " | " + task.getTagging() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed writing to duke file: " + e.getMessage());
        }
    }

    /**
     * Loads the task list from this storage file, and then returns it.
     * Returns an empty task list if the file does not exist, or is not a regular file.
     *
     * @return a task list with the loaded data from filepath
     */
    public ArrayList<Task> loadTasks() throws IOException {
        if (filePath.isEmpty()){
            filePath = STORAGE_FILEPATH;
        }
        Task currTask = null;
        ArrayList<Task> listOfTask = new ArrayList<>();
        File f = new File(filePath);
        File directory = new File(f.getAbsolutePath().substring(0,f.getAbsolutePath().lastIndexOf("\\")+1));

        if(!directory.exists()){
            if(!directory.mkdir()){
                throw new IOException("Parent directory does not exist: " + directory.getAbsolutePath());
            }
        }

        if(!f.exists() || !f.getPath().contains(".txt")) {
            return listOfTask;
        }
        Scanner myReader = new Scanner(f);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] arguments = line.split("\\s\\|");
            trimArray(arguments);
            switch (arguments[0]) {
                case "Event":
                    currTask = new Events(arguments[2], arguments[3]);
                    if (arguments.length > 4){
                        currTask.addTagging(arguments[4]);
                    }
                    break;
                case "Deadline":
                    currTask = new Deadlines(arguments[2], arguments[3]);
                    if (arguments.length > 4){
                        currTask.addTagging(arguments[4]);
                    }
                    break;
                case "Todo":
                    currTask = new Todo(arguments[2]);
                    if(arguments.length > 3){
                        currTask.addTagging(arguments[3]);
                    }
            }
            if (arguments[1].equals("false")) {
                currTask.unmarkTask();
            } else {
                currTask.markTask();
            }
            listOfTask.add(currTask);
        }
        myReader.close();
        return listOfTask;
    }

    /**
     * To trim all String element in a String array
     *
     * @param arguments is the String array to be trimmed
     */
    private void trimArray(String[] arguments){
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = arguments[i].trim();
        }
    }
}
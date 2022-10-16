package nus.duke.storage;

import nus.duke.frontend.Ui;
import nus.duke.task.*;
import nus.duke.tasklist.*;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Storage {
    private static String filePath;
    private static TaskList tasks;
    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {

        File file = new File(this.filePath);
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String data = s.nextLine();
                tasks.addTask(data);
            }
            return tasks.getTaskList();
        } catch (FileNotFoundException fnfe){
            throw new FileNotFoundException();
        }
    }

    public static boolean createHardDiskFile(String filePath) {
        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred: Java's IOException");
            e.printStackTrace();
        }
        return true;
    }

    public void saveTasks(){
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
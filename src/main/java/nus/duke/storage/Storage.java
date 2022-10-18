package nus.duke.storage;

import nus.duke.exceptions.EmptyTaskException;
import nus.duke.exceptions.WrongInputSyntaxException;
import nus.duke.task.*;
import nus.duke.tasklist.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.nio.file.Paths;
import java.nio.file.Files;
import nus.duke.parser.*;

public class Storage {
    private static String filePath;
    private static TaskList hardDiskTaskList;
    private static Parser parser;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        hardDiskTaskList = new TaskList();
        try {
            //check if file exist first
            File file = new File("/Users/rebecca/Desktop/Duke/data/DukeTasks.txt");
            Scanner s = new Scanner(file);
            String line;
            String command;
            int count = 1;
            while(s.hasNext()){
                line = s.nextLine();
                command = parser.parse(line);
                hardDiskTaskList.processTasks(command, line);
                hardDiskTaskList.processIsDone(count, line);
                count++;
            }
        } catch (IOException ioe){
            System.out.println("do nth");
        } catch (WrongInputSyntaxException wise){
            System.out.println("wrong input");
        } catch (EmptyTaskException ete){
            System.out.println("empty task");
        }
        return hardDiskTaskList.getTaskList();
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

    public void saveTasks(TaskList taskList){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/rebecca/Desktop/Duke/data/DukeTasks.txt"));
            for (int i = 0; i < taskList.getTotalTasks(); i++) {
                String str = taskList.getTaskList().get(i).getTask() + "[" + taskList.getTaskList().get(i).getIsDone() + "]" + "\n";
                writer.write(str);
            }
            writer.close();
            System.out.println("Your tasks are saved.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
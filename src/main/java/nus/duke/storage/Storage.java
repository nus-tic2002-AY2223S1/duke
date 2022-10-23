package nus.duke.storage;

import nus.duke.task.*;
import nus.duke.tasklist.*;
import nus.duke.parser.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;  // Needed to handle IOException thrown by FileWriter
import java.io.BufferedWriter;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

public class Storage {
    private static String filePath;
    private static TaskList hardDiskTaskList;
    private static Parser parser;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public String getFilePath(){
        return this.filePath;
    }

    public static boolean createHardDiskFile(String filePath) {
        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("OPPS!!! IO Exception");
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Task> load() throws IOException {
        hardDiskTaskList = new TaskList();
        try {
            String filePath = getFilePath();
            java.nio.file.Path path = java.nio.file.Paths.get(filePath);
            boolean directoryExists = java.nio.file.Files.exists(path);
            if (directoryExists){
                File file = new File(filePath);
                Scanner s = new Scanner(file);
                String line;
                String command;
                for(int count = 1; s.hasNext(); count++){
                    line = s.nextLine();
                    command = parser.parse(line);
                    hardDiskTaskList.processTasks(command, line);
                    hardDiskTaskList.processIsDone(count, line);
                }
            } else {
                createHardDiskFile(filePath);
            }
        } catch (IOException ioe){
            System.out.println("OPPS!!! IO Exception");
        }
        return hardDiskTaskList.getTaskList();
    }

    public void saveTasks(TaskList taskList){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()));
            for (int i = 0; i < taskList.getTotalTasks(); i++) {
                String markedStatus = taskList.getTaskList().get(i).getIsDone();
                if (markedStatus.equals("true")){
                    markedStatus = "T";
                } else {
                    markedStatus = "F";
                }
                String str = taskList.getTaskList().get(i).getTask() + "[" + markedStatus + "]" + "\n";
                writer.write(str);
            }
            writer.close();
            System.out.println("Your tasks are saved.");
        } catch (IOException e) {
            System.out.println("OPPS!!! IO Exception");
        }
    }
}
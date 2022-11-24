package Storage;

import Entity.Task;
import exception.UnknownException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    static String projectDirectory = System.getProperty("user.dir");
    private static final String DIRECTORY_PATH = "/data";
    private static final String FILE_NAME = "/duke.txt";
    private String filePath;

    public Storage(String filepath){
        if(filepath==null){
            this.filePath =projectDirectory + DIRECTORY_PATH + FILE_NAME;
        }else{
            this.filePath = filepath;
        }
    }

    public void writeToFile(String formattedInput) {
        File f = new File(filePath);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(formattedInput);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new UnknownException("error when write into file");
        }
    }

    public void deleteFile(){
        File f = new File(filePath);
        f.delete();
    }


    public void persist(TaskList taskList) {
        StringBuilder temp = new StringBuilder();
        for (Task t : taskList.getTaskList()) {
            temp.append(t.toDisk()).append("\n");
        }
        writeToFile(temp.toString());
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskDetails = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            //todo convert string to Task
            String[] args = s.nextLine().split("\\|");
            if (args.length == 3) {
                String type = args[0];
                boolean isDone = Boolean.parseBoolean(args[1]);
                String description = args[2];
                taskDetails.add(new Task(description, type, isDone));
            }
        }
        return taskDetails;
    }



}

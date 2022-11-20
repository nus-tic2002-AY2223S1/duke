package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void importTasks() {
        //if dir exists open it else create it
        Path path = Paths.get("data"+ File.separatorChar+"saveFile.txt");
        Path path2 = Paths.get("data"+ File.separatorChar+"archiveFile.txt");
        boolean directoryExists = Files.exists(path);
        if (!directoryExists) {
            try{
                Files.createDirectories(Paths.get("data"));
                Files.createFile(path);
                Files.createFile(path2);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
        boolean fileExists = java.nio.file.Files.exists(path);
        if (!fileExists) {
            System.out.println("File Dont Exist for Some Reason");
        }
        try{
            Scanner scanner = new Scanner(path.toFile());
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine().replaceAll("\\s+","");
                try {
                    importRow(nextLine);
                }catch (Exception e) {
                    System.out.println(e.getMessage() + " <---- THIS WAS FOUND IN FILE. WEIRD?!?");
                }

            }
            scanner.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void  importRow(String row)throws Exception{
        String[] info = row.split("\\|",100);
        Task task;
        boolean done = info[1].trim().equals("1");
        switch(info[0]){
            case "T":
                task = new Todo(info[2]);
                break;
            case "D":
                task = new Deadline(info[2],info[3]);
                break;
            case "E":
                task = new Event(info[2],info[3]);
                break;
            default :
                throw new Exception("File had someweird stuff");
        }
        if (done){task.markAsDone();}
        Tasklist.add(task);
    }
    public static void  saveToFile() {
        Path path = Paths.get("data"+ File.separatorChar+"saveFile.txt");
        try {
            FileWriter writer = new FileWriter(path.toFile());
            for (int i = 0; i < Tasklist.size(); i++) {
                writer.write(Tasklist.tasks.get(i).toFile());
            }
            writer.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void archive() {
        Path path = Paths.get("data"+ File.separatorChar+"archiveFile.txt");
        boolean fileExists = java.nio.file.Files.exists(path);
        if (!fileExists) {
            System.out.println("File Dont Exist for Some Reason");
            return;
        }
        try {
            FileWriter writer = new FileWriter(path.toFile());
            for (int i = 0; i < Tasklist.size(); i++) {
                writer.write(Tasklist.tasks.get(i).toFile());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Tasklist.tasks = new ArrayList<>(100);
    }
}




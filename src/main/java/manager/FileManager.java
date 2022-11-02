package manager;

import entity.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileManager {

    private static FileManager instance;
    private static final String DIRECTORY_PATH = "/data";
    private static final String FILE_NAME = "duke.txt";
    private static String projectDirectory;
 
    private FileManager() {
    }
    public static void newInstance() {
        instance = new FileManager();
        projectDirectory = System.getProperty("user.dir");
    }

    public static FileManager getInstance() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }

    private File createFileWithDir() throws IOException {
        File dir = new File(projectDirectory + DIRECTORY_PATH);

        if (!dir.exists()) {
            Files.createDirectories(Path.of(dir.getPath()));
        }

        File file = new File(projectDirectory + DIRECTORY_PATH + "/" + FILE_NAME);
        if (!file.exists()) {
            Files.createFile(Path.of(file.getPath()));
        }
        return file;
    }

    public void writeToFile(String formattedTasks) throws IOException {

        File file = createFileWithDir();

        FileWriter fw = new FileWriter(file);
        fw.write(formattedTasks);
        fw.flush();
        fw.close();
    }

    public ArrayList<String> readFromFile() throws IOException {
        ArrayList<String> taskDetails = new ArrayList<>();
        File file = createFileWithDir();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            taskDetails.add(s.nextLine());
        }
        return taskDetails;
    }

}

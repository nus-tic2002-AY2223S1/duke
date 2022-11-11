package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static FileManager instance;
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_NAME = "duke.txt";
    private static String projectDirectory;

    public static void newInstance(){
        instance = new FileManager();
        projectDirectory = System.getProperty("user.dir");
    }
    public static FileManager getInstance(){
        if(instance == null){
            newInstance();
        }
        return instance;
    }
    private File createDirFile() throws IOException{
        File dir = new File(projectDirectory + DIRECTORY_PATH);
        if(!dir.exists()){
            Files.createDirectories(Path.of(dir.getPath()));
        }
        File file = new File(projectDirectory + DIRECTORY_PATH + "/" + FILE_NAME);
        if (!file.exists()){
            Files.createFile(Path.of(file.getPath()));
        }
        return file;
    }

//    public static void writeToFile(String filePath, String textToAdd) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(textToAdd);
//        fw.flush();
//        fw.close();
//    }
//    public ArrayList<String> readFromFile() throws IOException {
//        ArrayList<String> taskList = new ArrayList<>();
//        File file = createDirFile();
//        Scanner s = new Scanner(file);
//        while (s.hasNext()) {
//            taskList.add(s.nextLine());
//        }
//        return taskList;
//    }

}
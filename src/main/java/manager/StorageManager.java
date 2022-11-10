package manager;

import exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager {
    
    private static StorageManager instance;
    private static final String DIRECTORY_PATH = "/data";
    private static final String FILE_NAME = "duke.txt";
    private static String projectDirectory;
    
    /**
     * Create a new instance of StorageManager class
     */
    private static void newInstance() {
        instance = new StorageManager();
        projectDirectory = System.getProperty("user.dir");
    }
    
    /**
     * Return the current StorageManager instance
     * If not exist, create a new instance and return
     *
     * @return current StorageManager instance
     */
    public static StorageManager getInstance() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }
    
    /**
     * Create file under given directory
     * Create directory if not exist
     *
     * @return Created file with given name under given directory
     * @throws IOException
     */
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
    
    /**
     * Write updated task list information into a file and save it locally
     *
     * @param formattedTasks task list details
     * @throws IOException
     */
    public void writeToFile(String formattedTasks) throws IOException {
        assert formattedTasks != null : "String should not be null.";
        
        File file = createFileWithDir();
        
        FileWriter fw = new FileWriter(file);
        fw.write(formattedTasks);
        fw.flush();
        fw.close();
    }
    
    /**
     * Read from saved file and return task information
     *
     * @return last saved task information list
     */
    public ArrayList<String> readFromFile() {
        ArrayList<String> taskDetails = new ArrayList<>();
        File file = null;
        Scanner s = null;
        try {
            file = createFileWithDir();
            s = new Scanner(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNext()) {
            taskDetails.add(s.nextLine());
        }
        return taskDetails;
    }
    
}

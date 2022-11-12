package storage;

import engine.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static formatting.Helper.DUKEDIRECTORY;
import static formatting.Helper.DUKEFILEPATH;

public class Storage {

    private String fileName;

    private List<String> newFileList;

    private Storage(){newFileList = new ArrayList<String>();}

    private static Storage instance = null;

    public static Storage getInstance(){
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void createFile() throws Exception {
        try {
            File dir = new File(DUKEFILEPATH);
            if (!dir.exists()){ //check if directory exists
                dir.mkdirs(); //function returns true if directory is created else returns false.
            }

            String fileNameTxt = fileName.concat(".txt");
            File fileObj = new File(dir, fileNameTxt);
                if (fileObj.createNewFile()){ //Returns true if abstract file path does not exist, then creates the file. Returns false if filename already exists.
                    System.out.println(fileObj + " was created!");
                }
                else {
                    System.out.println(fileName + ".txt already exists on" + DUKEFILEPATH + "\n Please type a different filename, or type \"/e\" to go back to the main menu." );
                }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void populateFile() {
        //check if directory exists
        Path directory = DUKEDIRECTORY();
        boolean directoryExists = Files.exists(directory);

        if (directoryExists) {
            TaskList t = TaskList.getInstance();

            int taskListSize = t.getTLSize();
            for (int i = 0; i < taskListSize; i++) {
                newFileList.add(t.getTodoListRow(i));
            }

            try{
                Files.write(directory,newFileList);
                System.out.println(fileName+".txt has been populated!");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(fileName+".txt failed to populate. Please ensure that the DUKEFILEPATH and DUKEDIRECTORY() has the same file path (Helper class)");
    }

    public boolean checkExistence(){
        Path directory = DUKEDIRECTORY();
        return Files.exists(directory);
    }

    public static void validateStringFilenameUsingIO(String filename) throws IOException {
        String validateFileName = DUKEFILEPATH.concat(filename);
        File file = new File(validateFileName);
        boolean created = false;
        try {
            created = file.createNewFile();
        } catch (Exception e) {
            System.out.println("You tried to create a file with restricted characters! Please try again, or type \"/e\" to go back to the main menu." );
        } finally {
            if (created) {
                file.delete();
            }
        }
    }
}

import java.io.File;  // Import the File class
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
            String path = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "dukeFile";
            File dir = new File(path);
            if (!dir.exists()){ //check if directory exists
                dir.mkdirs(); //function returns true if directory is created else returns false.
            }
            String fileNameTxt = fileName.concat(".txt");
            File fileObj = new File(dir, fileNameTxt);
                if (fileObj.createNewFile()){ //Returns true if abstract file path does not exist, then creates the file. Returns false if filename already exists.
                    System.out.println(fileObj + " was created!");
                }
                else {
                    System.out.println(fileName + ".txt already exists on" + path + "\n Please type a different filename, or type \"/e\" to stop saving." );
                }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void populateFile() throws Exception{
        //check if directory exists
        String fileNameTxt = fileName.concat(".txt");
        Path directory = Paths.get(System.getProperty("user.home"), "Desktop", "dukeFile",fileNameTxt);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkExistence(){
        String fileNameTxt = fileName.concat(".txt");
        Path directory = Paths.get(System.getProperty("user.home"), "Desktop", "dukeFile", fileNameTxt);
        return Files.exists(directory);
    }
}

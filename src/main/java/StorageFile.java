import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import exceptions.InvalidStorageFilePathException;
public class StorageFile {
//    public static boolean isValidPath(String path) {
//        try {
//            Paths.get(path);
//        } catch (InvalidPathException | NullPointerException ex) {
//            return false;
//        }
//        return true;
//    }

    public static String homeDirectory(){
        String homeDirectory = System.getProperty("user.home");
        return homeDirectory;
    }
    private static void writeToFile(String filePath, List<Task> taskList) throws IOException {
        // write to file
        FileWriter fw = new FileWriter(filePath);
        for (Task arr : taskList) {
            fw.write(arr.toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) throws InvalidStorageFilePathException, IOException {
        // create file to file path assuming all users are storing the duke in the user home directory
        File file = new File(homeDirectory(), "/duke/data/duke.txt");
        File home = new File(homeDirectory());
        if (!home.exists()) {
            throw new InvalidStorageFilePathException();
        }
        else {
            try {
                // write to file
                writeToFile(String.valueOf(file), Duke.taskList);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    static void mainCaller() throws InvalidStorageFilePathException, IOException {
        StorageFile.main(null);
    }
}


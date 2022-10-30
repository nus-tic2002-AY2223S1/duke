import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StorageFile {
//    public static boolean isValidPath(String path) {
//        try {
//            Paths.get(path);
//        } catch (InvalidPathException | NullPointerException ex) {
//            return false;
//        }
//        return true;
//    }

    private static void writeToFile(String filePath, List<Task> taskList) throws IOException {
        // write to file
        FileWriter fw = new FileWriter(filePath);
        for (Task arr : taskList) {
            fw.write(arr.toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        // get user's home directory
        String homeDirectory = System.getProperty("user.home");

        // create file to file path assuming all users are storing the duke in the user home directory
        File file = new File(homeDirectory, "/duke/data/duke.txt");
        try {
            // write to file
            writeToFile(String.valueOf(file), Duke.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    static void mainCaller() {
        StorageFile.main(null);
    }
}


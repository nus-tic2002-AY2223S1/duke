import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    //constructor
    Storage(){

    }
    public Storage(String filePath) {
        new File(filePath); //create a File for the given File path
    }

    public static String load() throws IOException {
        Path path = Paths.get("../TIC2002/tasks.txt"); //hardcord the path

        String read = Files.readAllLines(path).get(0); //read the file

        return read; //return as String
    }
}

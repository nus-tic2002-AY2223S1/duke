import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
    private static File f;
    //constructor
    public Storage(String filePath) {
        f = new File(filePath); //create a File for the given File path
    }

    public static File load() throws DukeException, FileNotFoundException {
        return f;
    }
}

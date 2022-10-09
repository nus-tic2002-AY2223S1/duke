import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
    private static File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public static File load() throws DukeException, FileNotFoundException {
        return file;
    }


}

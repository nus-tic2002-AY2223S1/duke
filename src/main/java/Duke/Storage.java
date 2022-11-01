package Duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    protected String path;
    public Storage(String path) {
        this.path = path;
    }

    public Scanner load() throws FileNotFoundException {
        return new Scanner(new File(this.path));
    }
}

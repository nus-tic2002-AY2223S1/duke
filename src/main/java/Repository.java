import java.io.File;
import java.io.IOException;

public class Repository {
    
    // properties
    private final String repoDirectory;
    private final String repoFileName;

    // constructors
    public Repository (String directory, String fileName) { // constructor for main to pass in the directory parameter
        this.repoDirectory = directory;
        this.repoFileName = fileName;
    }

    public void open() throws IOException {
        File f = new File(repoDirectory + repoFileName);
        if (f.createNewFile()) {
            System.out.printf("The %s file has been created\n" , repoFileName);
            System.out.println();
        } else {
            System.out.printf("Opening %s file for persistence\n" , repoFileName);
            System.out.println();
        }
    }
}

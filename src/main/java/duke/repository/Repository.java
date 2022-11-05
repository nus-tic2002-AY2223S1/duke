import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulate the methods required to access a file.
 */
public class Repository {
    private final String repoDirectory;
    private final String repoFileName;
    List<Task> repoList = new ArrayList<>();

    public Repository (String directory, String fileName) {
        this.repoDirectory = directory;
        this.repoFileName = fileName;
    }

    public void loadDirectory() throws IOException {
        File dir = new File(repoDirectory);
        if (dir.mkdir()) {
            System.out.printf("The %s directory has been created,\n" , repoDirectory);
        }
        System.out.printf("Using %s directory,\n" , repoDirectory);
    }

    /**
     * Atomically creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist.
     * @throws IOException If an I/O error occurred.
     */
    public void loadFile() throws IOException {
        File f = new File(repoDirectory + repoFileName);
        if (f.createNewFile()) {
            System.out.printf("The %s file has been created.\n" , repoFileName);
            System.out.println();
        } else {
            System.out.printf("Opening %s file.\n" , repoFileName);
            System.out.println();
        }
    }

    /**
     * Returns the path of the file.
     * @return file path.
     */
    public String getFileDirectory() {
        return this.repoDirectory + this.repoFileName;
    }

    /**
     * Returns an array list of Task objects.
     * @return array list.
     * @throws IOException If an I/O error occurred.
     */
    public List<Task> readFile() throws IOException {
        FileReader r = new FileReader(this.repoDirectory + this.repoFileName);
        BufferedReader br = new BufferedReader(r);
        while (true) {
            String contentLine = br.readLine();
            if (contentLine == null)
                break;
            String[] stringParser = contentLine.split(" ");
            if (stringParser[1].contains("T")) {
                Task todo = new Todo(contentLine.substring(10));
                if (stringParser[1].contains("X")) {
                    todo.setDone(true);
                }
                repoList.add(todo);
            }
            if (stringParser[1].contains("D")) {
                Task deadline = new Deadline(contentLine.substring(10));
                if (stringParser[1].contains("X")) {
                    deadline.setDone(true);
                }
                repoList.add(deadline);
            }
            if (stringParser[1].contains("E")) {
                Task event = new Event(contentLine.substring(10));
                if (stringParser[1].contains("X")) {
                    event.setDone(true);
                }
                repoList.add(event);
            }
        }
        br.close();
        return repoList;
    }
}

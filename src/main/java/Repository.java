import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    
    // properties
    private final String repoDirectory;
    private final String repoFileName;
    List<Task> repoList = new ArrayList<>();

    // constructors
    public Repository (String directory, String fileName) { // constructor for main to pass in the directory parameter
        this.repoDirectory = directory;
        this.repoFileName = fileName;
    }

    public void loadFile() throws IOException {
        File f = new File(repoDirectory + repoFileName);
        if (f.createNewFile()) {
            System.out.printf("The %s file has been created\n" , repoFileName);
            System.out.println();
        } else {
            System.out.printf("Opening %s file for persistence\n" , repoFileName);
            System.out.println();
        }
    }

    public String getFileDirectory() {
        return this.repoDirectory + this.repoFileName; // getter method to get the directory, so that it can be used in other classes like Session
    }

    public List<Task> readFile() throws IOException {
        FileReader r = new FileReader(this.repoDirectory + this.repoFileName); // instantiate new FileReader
        BufferedReader br = new BufferedReader(r); // instantiate new BufferedReader
        while (true) {
            String contentLine = br.readLine(); // store the read line into a string
            if (contentLine == null) // check if it is null and exit the loop, otherwise there will be a nullPointerError
                break;
            String[] stringParser = contentLine.split(" ");
            // if it is a to-do task and if it is marked or unmarked
            if (stringParser[1].contains("T")) {
                Task todo = new Todo(contentLine.substring(10));
                if (stringParser[1].contains("X")) {
                    todo.setDone(true);
                }
                repoList.add(todo);
            }
            // if it is a deadline task and if it is marked or unmarked
            if (stringParser[1].contains("D")) {
                Task deadline = new Deadline(contentLine.substring(10));
                if (stringParser[1].contains("X")) {
                    deadline.setDone(true);
                }
                repoList.add(deadline);
            }
            // if it is an event task and if it is marked or unmarked
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

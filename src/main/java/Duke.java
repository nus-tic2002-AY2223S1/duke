import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        String directory = "./data/";
        String fileName = "myList.txt";
        Repository repo = new Repository(directory, fileName);
        Session tracking = new Session(repo);
        tracking.start();
    }
}

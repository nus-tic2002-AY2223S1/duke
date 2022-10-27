import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        //String directory = "../../../data/";
        String directory = "./data/";
        String fileName = "tasklist.txt";
        Repository repo = new Repository(directory, fileName);
        Session tracking = new Session(repo);
        tracking.start();
    }
}

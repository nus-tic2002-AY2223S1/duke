package duke;

import duke.repository.Repository;
import duke.ui.Parser;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        String directory = "./data/";
        String fileName = "myList.txt";
        Repository repo = new Repository(directory, fileName);
        Parser parser = new Parser(repo);
        parser.start();
    }
}

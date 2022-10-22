package seedu.nus-duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "tasklist.txt";

    public final Path path;

    public Storage(){
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath){
        path = Paths.get(filePath);

    }

}
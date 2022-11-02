package duke.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    protected String path;

    /**
     * Storage Constructor.
     * Initialize storage file path.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the file content configured by constructor.
     *
     * @return Scanner Object of file
     * @throws FileNotFoundException If file is not found at the path.
     */
    public Scanner load() throws FileNotFoundException {
        return new Scanner(new File(this.path));
    }

    /**
     * Storage Constructor.
     * Initialize storage file path.
     *
     * @return The initialized storage file path.
     */
    public String getPath() {
        return this.path;
    }
}

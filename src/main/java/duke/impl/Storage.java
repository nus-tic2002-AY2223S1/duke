package duke.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Storage class to read data from file.
 */
public class Storage {
    protected String path;

    /**
     * Storage Constructor.
     * Initialize storage file path.
     *
     * @param path File path to initialize
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

    /**
     * Helper function to clear task cache. For UT usage.
     */
    public static void deleteCache() {
        File myObj = new File("data/save/output");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}

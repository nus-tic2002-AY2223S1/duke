package data;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface that can be implemented to have multiple data source
 */
public interface FileInterface {
    /**
     * Returns the arraylist of data File from a file
     * There will be at least 1 default file
     *
     * @return all the files
     */
    public ArrayList<FileInfo> getAllFile();

    /**
     * Sets specific file to an active
     * if the file couldn't be found, nothing will be return
     *
     * @return boolean if its true means set active successful and if its false means alias is wrong
     * @param alias the alias name of the file
     */
    public boolean setActive(String alias) throws IOException;
    /**
     * gets current active file
     *
     * @return File the active file
     */
    public FileInfo getActiveFile() throws IOException;
    /**
     * adds a new custom file from user
     *
     * @return boolean true for add success and false fo add fail
     * @param alias the new alias
     */
    public boolean addNewFile(String alias) throws IOException;
}

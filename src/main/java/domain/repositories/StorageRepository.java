package domain.repositories;

import application.helpers.MessageConstants;
import application.helpers.StorageConstants;
import domain.aggregates.tracker.Task;
import domain.exceptions.DukeFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorageRepository implements IStorageRepository{
    public StorageRepository(){

    }

    /**
     * Initialise a file pointing to specified file path and is writable & readable.
     * If file does not exist, initialised file will be saved and adds default header.
     *
     * @return File.
     * @throws DukeFileException if unable to save changes to file.
     */
    public File initialise() throws DukeFileException {
        File file = new File(StorageConstants.FILE_PATH);
        file.setWritable(true);
        file.setReadable(true);
        if(!file.exists()) {
            try {
                file.createNewFile();
                write(file, StorageConstants.HEADER);
            } catch (IOException ex){
                throw new DukeFileException(MessageConstants.TASK_GET_ERROR);
            }
        }
        return file;
    }

    /**
     * Appends new row to the file.
     *
     * @param file File.
     * @param row String.
     * @throws DukeFileException if unable to save changes to file.
     */
    public void write(File file, String row) throws DukeFileException {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(row + "\n");
            writer.close();
        } catch (IOException ex){
            throw new DukeFileException(MessageConstants.FILE_WRITE_ERROR);
        }
    }

    /**
     * Creates a new file with the latest tasks and Replaces previous file with this.
     *
     * @param tasks ArrayList<Task>.
     * @throws DukeFileException if unable to save changes to file or file.
     */
    public void override(ArrayList<Task> tasks) throws DukeFileException {
        try {
            FileWriter writer = new FileWriter(StorageConstants.FILE_PATH);
            writer.append(StorageConstants.HEADER + "\n");
            for (Task task : tasks) {
                writer.append(task.toString() + "\n");
            }
            writer.flush();
        } catch (IOException ex){
            throw new DukeFileException(MessageConstants.FILE_OVERWRITE_ERROR);
        }
    }
}

package domain.aggregates.storage;

import domain.aggregates.tracker.Task;
import domain.exceptions.DukeFileException;
import domain.repositories.IStorageRepository;
import domain.repositories.StorageRepository;

import java.io.File;
import java.util.ArrayList;

public class Storage {
    /**
     * Properties
     */
    private File file;
    private final IStorageRepository _storageRepository;

    /**
     * Initialises Storage to read and write.
     *
     * @throws DukeFileException if unable to retrieve or create file.
     */
    public Storage() throws DukeFileException {
        _storageRepository = new StorageRepository();
        this.file = _storageRepository.initialise();
    }

    /**
     * Adds new record to file.
     *
     * @param row String.
     * @throws DukeFileException if unable to save changes to local file.
     */
    public void saveItem(String row) throws DukeFileException {
        _storageRepository.write(file, row);
    }

    /**
     * Creates a new file with the latest items and overrides the previous file.
     *
     * @param items ArrayList<Task>.
     * @throws DukeFileException if unable to save changes to local file.
     */
    public void override(ArrayList<Task> items) throws DukeFileException {
        _storageRepository.override(items);
    }
}

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
     * Storage has file to read and write to
     * Storage default constructor
     * Requires StorageRepository
     */
    public Storage() throws DukeFileException {
        _storageRepository = new StorageRepository();
        this.file = _storageRepository.init();
    }

    /**
     * Add new record to file
     */
    public void saveItem(String s) throws DukeFileException {
        _storageRepository.write(file, s);
    }

    /**
     * Create a new file with the latest items and override the previous file
     */
    public void override(ArrayList<Task> items) throws DukeFileException {
        _storageRepository.override(items);
    }
}

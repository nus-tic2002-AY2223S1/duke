package domain.aggregates.storage;

import domain.aggregates.tracker.Task;
import domain.exceptions.DukeFileException;
import domain.repositories.IStorageRepository;
import domain.repositories.StorageRepository;

import java.io.File;
import java.util.ArrayList;

public class Storage {
    public File file;
    private final IStorageRepository _storageRepository;

    public Storage() throws DukeFileException {
        _storageRepository = new StorageRepository();
        this.file = _storageRepository.init();
    }

    public void saveItem(String s) throws DukeFileException {
        _storageRepository.write(file, s);
    }

    public void override(ArrayList<Task> items) throws DukeFileException {
        _storageRepository.override(items);
    }
}

package Domain.Aggregates.Storage;

import Domain.Aggregates.Tracker.Task;
import Domain.Exceptions.DukeFileException;
import Domain.Repositories.IStorageRepository;
import Domain.Repositories.StorageRepository;

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

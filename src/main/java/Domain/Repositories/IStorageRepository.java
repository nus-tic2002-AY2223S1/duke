package Domain.Repositories;

import Domain.Aggregates.Tracker.Task;
import Domain.Exceptions.DukeFileException;

import java.io.File;
import java.util.ArrayList;

public interface IStorageRepository {
    public File init() throws DukeFileException;

    public void write(File file, String row) throws DukeFileException;

    public void override(ArrayList<Task> tasks) throws DukeFileException;
}
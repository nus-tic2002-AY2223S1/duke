package domain.repositories;

import domain.aggregates.tracker.Task;
import domain.exceptions.DukeFileException;

import java.io.File;
import java.util.ArrayList;

public interface IStorageRepository {
    public File initialise() throws DukeFileException;

    public void write(File file, String row) throws DukeFileException;

    public void override(ArrayList<Task> tasks) throws DukeFileException;
}
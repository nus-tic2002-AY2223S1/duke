package Domain.Repositories;

import Domain.Aggregates.Tracker.Task;

import java.io.File;
import java.util.ArrayList;

public interface IStorageRepository {
    public File init();

    public void write(File file, String row);

    public void override(ArrayList<Task> tasks);

    public ArrayList<Task> convertToTaskList();
}
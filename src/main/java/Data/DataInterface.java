package Data;
import Tasks.TaskInterface;
import Tasks.UnsupportedTaskType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DataInterface {
    public ArrayList<TaskInterface> loadData() throws FileNotFoundException, UnsupportedTaskType;
    public void update(TaskInterface task) throws IOException, UnsupportedTaskType, IndexOutOfBoundsException;
    public void delete(TaskInterface task) throws IOException, IndexOutOfBoundsException;
    public void add(TaskInterface task) throws IOException;
}

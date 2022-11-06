package data;
import task.TaskInterface;
import exception.UnsupportedTaskType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface that can be implemented to store data and retrieve data
 */
public interface DataInterface {
    /**
     * Returns the arraylist of TaskInterface from any database or file
     * If the database or file is empty return empty
     *
     * @return all the tasks
     * @throws FileNotFoundException couldnt find the file
     * @throws UnsupportedTaskType unsupported task type being read from the database
     */
    public ArrayList<TaskInterface> loadData() throws FileNotFoundException, UnsupportedTaskType;
    /**
     * Update the specific task from the database or file
     * if the task is not in database, nothing is happened
     *
     * @return void
     * @param task the updated task object
     * @throws IOException couldnt read the file
     * @throws UnsupportedTaskType unsupported task type being read from the database
     * @throws IndexOutOfBoundsException couldnt find the line in the file
     */
    public void update(TaskInterface task) throws IOException, UnsupportedTaskType, IndexOutOfBoundsException;
    /**
     * delete the specific task from the database or file
     * if the task is not in database, nothing is happened
     *
     * @return void
     * @param task the deleted task object
     * @throws IOException couldnt read the file
     * @throws UnsupportedTaskType unsupported task type being read from the database
     */
    public void delete(TaskInterface task) throws IOException, IndexOutOfBoundsException;
    /**
     * add a new task into the database or file
     *
     * @return void
     * @param task the new task object
     * @throws IOException couldnt read the file
     */
    public void add(TaskInterface task) throws IOException;

    /**
     * add a way to change to other existing files
     *
     * @return void
     * @throws IOException couldnt read the file
     */
    public void changeFile(String path);
}

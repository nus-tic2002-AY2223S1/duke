package UI;

import CustomException.UnsupportedTaskType;
import Tasks.TaskInterface;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The UI interface that will show all the ui based on the logic
 */
public interface UIInterface {
    /**
     * This will get called when the logic successfully add a new task
     * If the task is not add successfuly this function will not get called
     *
     * @return void
     */
    public void addSuccess(TaskInterface task);
    /**
     * This will get called when the logic asks for show all tasks
     * If there is no task, the lists will be empty
     *
     * @return void
     * @param lists the list of tasks
     */
    public void showList(ArrayList<TaskInterface> lists);
    /**
     * This will get called when mark or unmark successfully
     * If the task is not mark or unmark successfuly this function will not get called
     *
     * @return void
     * @param task the newly mark or unmark task
     */
    public void markSuccess(TaskInterface task);
    /**
     * This will get called when the logic is failed to mark an unmark
     * If the task is mark successfully this function will not get called
     *
     * @return void
     * @param task the task that has failed to be marked
     */
    public void markFailed(TaskInterface task);
    /**
     * This will get called when the logic successfully delete a new task
     * If the task is not delete successfuly this function will not get called
     *
     * @return void
     * @param task the task that has been deleted
     */
    public void deleteSuccess(TaskInterface task);
    /**
     * get called when the keyword is not supported
     *
     * @return void
     */
    public void unsupportedTaskType();
    /**
     * get called when any retrieve or delete happen on non existing task
     *
     * @return void
     */
    public void indexOutOFBound();
    /**
     * get called when the format for specific keyword is empty
     *
     * @return void
     * @param text the entered command
     */
    public void unsupportedFormat(String text);
    /**
     * get called when the format for specific keyword is wrong format
     *
     * @return void
     * @param text the entered command
     */
    public void invalidFormat(String text);
    /**
     * get called when need to show goobye text
     *
     * @return void
     */
    public void goodbye();
    /**
     * get called when need to show the size of task in the system
     *
     * @return void
     */
    public void displaySize(int size);
    /**
     * get called when the logic determines the error text
     *
     * @return void
     * @param text the error message from logic
     */
    public void customError(String text);
}

package ui;

import data.FileInfo;
import task.TaskInterface;

import java.util.ArrayList;

/**
 * The UI interface that will show all the ui based on the logic
 */
public interface UIInterface {
    /**
     * Starts the ui to get user input
     *
     * @return void
     */
    public void start();
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

    /**
     * get called when unexpected exception happen
     *
     * @return void
     */
    public void unexpectedError();

    /**
     * get called when successfully create new file to save
     *
     * @return void
     */
    public void addFileSuccess(String text);
    /**
     * get called when fail to create new file to save due to the alias is exist
     *
     * @return void
     */
    public void addFileFailed(String text);

    /**
     * get called when need to show all the storage files
     *
     * @return void
     */
    public void showFiles(ArrayList<FileInfo> files);

    /**
     * get called when successfully set active an alias
     *
     * @return void
     */
    public void setActiveSuccess(String alias);

    /**
     * shows the current active file alias
     *
     * @return void
     */
    public void getActiveFile(String alias);

    /**
     * shows the invalid and valid format for the command that user has entered
     *
     * @return void
     * @param valid is how the valid command should required
     * @param invalid is the user entered invalid command
     */
    public void invalidCommandFormat(String valid, String invalid);
    /**
     * shows the filtered tasks by keyword
     * If there is no task, the lists will be empty
     *
     * @return void
     * @param lists the list of tasks
     */
    public void showFilteredList(ArrayList<TaskInterface> lists);
}

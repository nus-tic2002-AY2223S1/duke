package Logic;

import CustomException.UnsupportedTaskType;
import Tasks.TaskInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class that can be extend to handle all the bot logic
 */
public abstract class BotUseCase {
    public BotCallback delegate;
    /**
     * get called when add keyword is process successfully
     * if the task is not add successful this will not get called
     *
     * @return void
     * @param task the newly added task conform to TaskInterface
     */
    public void addSuccess(TaskInterface task) {};
    /**
     * get called when show list keyword is process successfully
     *
     * @return void
     * @param lists all the tasks that have been added
     */
    public void showList(ArrayList<TaskInterface> lists){};
    /**
     * get called when mark or unmark keyword is process successfully
     *
     * @return void
     * @param task the newly mark or unmark task
     */
    public void markSuccess(TaskInterface task){};
    /**
     * get called when mark or unmark keyword is process unsuccessfully
     *
     * @return void
     * @param task the failed mark or unmark task
     */
    public void markFailed(TaskInterface task){};
    /**
     * get called when delete keyword is process unsuccessfully
     *
     * @return void
     * @param task the deleted task
     */
    public void deleteSuccess(TaskInterface task){};
    /**
     * get called when the keyword is not supported
     *
     * @return void
     */
    public void unsupportedTaskType(){};
    /**
     * get called when any retrieve or delete happen on non existing task
     *
     * @return void
     */
    public void indexOutOFBound(){};
    /**
     * get called when the format for specific keyword is empty
     *
     * @return void
     * @param text the entered command
     */
    public void unsupportedFormat(String text){};
    /**
     * get called when the format for specific keyword is wrong format
     *
     * @return void
     * @param text the entered command
     */
    public void invalidFormat(String text){};
    /**
     * get called when the logic determines the error text
     *
     * @return void
     * @param text the error message from logic
     */
    public void customError(String text){};
    /**
     * get called when bye command is called
     *
     * @return void
     */
    public void goodbye(){};
    /**
     * get called when need to load data from storage
     *
     * @return void
     */
    public void loadData(){};

}

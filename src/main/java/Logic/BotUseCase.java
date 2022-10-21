package Logic;

import Tasks.TaskInterface;

import java.util.ArrayList;

public abstract class BotUseCase {
    public BotCallback delegate;
    public void addSuccess(TaskInterface task) {};
    public void showList(ArrayList<TaskInterface> lists){};
    public void markSuccess(TaskInterface task){};
    public void markFailed(TaskInterface task){};
    public void deleteSuccess(TaskInterface task){};
    public void unsupportedTaskType(){};
    public void indexOutOFBound(){};
    public void unsupportedFormat(String text){};
    public void goodbye(){};
    public void loadData(){};

}

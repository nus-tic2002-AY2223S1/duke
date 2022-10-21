package Logic;

import Tasks.TaskInterface;

import java.util.ArrayList;

public interface BotCallback {
    public void loadData(ArrayList<TaskInterface> lists);
    public int getSize();
}

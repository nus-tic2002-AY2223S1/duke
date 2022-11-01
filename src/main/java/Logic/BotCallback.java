package Logic;

import Tasks.TaskInterface;

import java.util.ArrayList;

/**
 * Callback to retrieve data from logic
 */
public interface BotCallback {
    public void loadData(ArrayList<TaskInterface> lists);
    public int getSize();
}

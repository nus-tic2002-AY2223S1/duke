package common.util;

import model.Chat;
import model.Task;

public class TaskUtil {
    /**
     * checkDuplicatedTask returns whether a task is duplicated or not in the current task list
     *
     * @return {boolean}
     */
    public static boolean checkDuplicatedTask(Chat chat){
        for (Task task : chat.getTaskList()) {
            if (chat.getInput().contains(task.getDescription())) {
                return true;
            }
        }
        return false;
    }
}

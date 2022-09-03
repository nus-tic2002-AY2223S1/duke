package utils;

import domain.Task;

import java.util.List;
import java.util.Optional;

import static domain.Mouth.speak;
import static utils.ErrorMessages.MARK_UNMARK_TASK_ERR_MSG;

public class TaskUtil {
    public static Optional<Task> getTaskFromUserInput(List<Task> taskList, String userInput) {
        try {
            int num = Integer.parseInt(userInput.replaceAll("[^0-9]", ""))-1;
            return Optional.of(taskList.get(num));
        } catch (Exception e) {
            speak(MARK_UNMARK_TASK_ERR_MSG);
            return Optional.empty();
        }
    }
}

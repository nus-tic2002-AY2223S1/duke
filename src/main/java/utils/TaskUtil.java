package utils;

import domain.task.Task;

import java.util.List;
import java.util.Optional;

import static domain.Mouth.speak;
import static utils.ErrorMessages.MARK_UNMARK_NUMBER_OUT_OF_RANGE_ERR_MSG;
import static utils.ErrorMessages.MARK_UNMARK_TASK_ERR_MSG;

public class TaskUtil {
    public static Optional<Task> getTaskFromTaskList(List<Task> taskList, String userInput) {
        try {
            int num = Integer.parseInt(userInput.replaceAll("[^0-9]", ""))-1;
            return Optional.of(taskList.get(num));
        } catch (IndexOutOfBoundsException i) {
            speak(MARK_UNMARK_NUMBER_OUT_OF_RANGE_ERR_MSG);
            return Optional.empty();
        } catch (Exception e) {
            speak(MARK_UNMARK_TASK_ERR_MSG);
            return Optional.empty();
        }
    }

    public static String getFirstWord(String sentence) {
        try {
            String[] arr = sentence.split(" ", 2);
            return arr[0];
        } catch (Exception e) {
            return "";
        }
    }

    public static String removeFirstWord(String sentence) {
        try {
            String[] arr = sentence.split(" ", 2);
            return arr[1];
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isBlank();
    }
}

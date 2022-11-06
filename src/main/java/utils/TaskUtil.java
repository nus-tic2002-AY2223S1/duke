package utils;

import domain.TaskList;
import domain.task.Task;
import exceptions.InvalidIntegerException;

import java.util.Optional;

import static utils.ErrorMessages.INVALID_INTEGER_ERR_MSG;
import static utils.ErrorMessages.MARK_UNMARK_TASK_ERR_MSG;
import static utils.ErrorMessages.NUMBER_OUT_OF_RANGE_ERR_MSG;
import static utils.Mouth.speak;

public class TaskUtil {
    public static Optional<Task> getTaskFromTaskList(TaskList taskList, String userInput) {
        try {
            int num = getTaskIndexFromUserInput(userInput);
            return Optional.of(taskList.getTask(num));
        } catch (IndexOutOfBoundsException i) {
            speak(NUMBER_OUT_OF_RANGE_ERR_MSG);
        } catch (InvalidIntegerException e) {
            speak(INVALID_INTEGER_ERR_MSG);
        } catch (Exception e) {
            speak(MARK_UNMARK_TASK_ERR_MSG);
        }
        return Optional.empty();
    }

    public static int getTaskIndexFromUserInput(String userInput) throws InvalidIntegerException {
        try {
            return Integer.parseInt(userInput.replaceAll("[^0-9]", ""))-1;
        } catch (Exception e) {
            throw new InvalidIntegerException();
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

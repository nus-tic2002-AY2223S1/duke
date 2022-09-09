package utils;

import domain.task.Deadline;
import domain.task.Event;
import domain.task.Task;
import domain.task.Todo;
import exceptions.EmptyDescriptionException;

import java.util.Optional;

import static utils.ErrorMessages.CREATE_DEADLINE_ERR_MSG;
import static utils.ErrorMessages.CREATE_DEADLINE_NO_BY_ERR_MSG;
import static utils.ErrorMessages.CREATE_EVENT_ERR_MSG;
import static utils.ErrorMessages.CREATE_EVENT_NO_AT_ERR_MSG;
import static utils.ErrorMessages.CREATE_TASK_ERR_MSG;
import static utils.ErrorMessages.CREATE_TODO_ERR_MSG;
import static utils.ErrorMessages.NO_DESCRIPTION_ERR_MSG;
import static utils.Mouth.speak;
import static utils.TaskUtil.removeFirstWord;

public class Converter {

    public static Optional<Task> getTaskFromUserInput(String userInput) {
        try {
            return Optional.of(new Task(userInput));
        } catch (EmptyDescriptionException e) {
            speak(NO_DESCRIPTION_ERR_MSG);
            return Optional.empty();
        } catch (Exception e) {
            speak(CREATE_TASK_ERR_MSG);
            return Optional.empty();
        }
    }

    public static Optional<Todo> getTodoFromUserInput(String userInput) {
        String description = removeFirstWord(userInput);
        try {
            return Optional.of(new Todo(description));
        } catch (EmptyDescriptionException e) {
            speak(NO_DESCRIPTION_ERR_MSG);
            return Optional.empty();
        } catch (Exception e) {
            speak(CREATE_TODO_ERR_MSG);
            return Optional.empty();
        }

    }

    public static Optional<Deadline> getDeadlineFromUserInput(String userInput) {
        userInput = removeFirstWord(userInput);
        if (!userInput.contains("/by")) {
            speak(CREATE_DEADLINE_NO_BY_ERR_MSG);
            return Optional.empty();
        }
        try {
            String[] deadlineInputs = userInput.split(" /by ", 2);
            return Optional.of(new Deadline(deadlineInputs[0], deadlineInputs[1]));
        } catch (EmptyDescriptionException e) {
            speak(NO_DESCRIPTION_ERR_MSG);
            return Optional.empty();
        } catch (Exception e) {
            speak(CREATE_DEADLINE_ERR_MSG);
            return Optional.empty();
        }
    }

    public static Optional<Event> getEventFromUserInput(String userInput) {
        userInput = removeFirstWord(userInput);
        if (!userInput.contains("/at")) {
            speak(CREATE_EVENT_NO_AT_ERR_MSG);
            return Optional.empty();
        }
        try {
            String[] deadlineInputs = userInput.split(" /at ");
            return Optional.of(new Event(deadlineInputs[0], deadlineInputs[1]));
        } catch (EmptyDescriptionException e) {
            speak(NO_DESCRIPTION_ERR_MSG);
            return Optional.empty();
        } catch (Exception e) {
            speak(CREATE_EVENT_ERR_MSG);
            return Optional.empty();
        }
    }
}

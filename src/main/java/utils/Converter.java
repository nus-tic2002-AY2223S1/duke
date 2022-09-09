package utils;

import domain.task.Deadline;
import domain.task.Event;
import domain.task.Todo;
import exceptions.EmptyDescriptionException;
import exceptions.MissingSlashCommandDetailsException;
import exceptions.MissingSlashCommandException;
import exceptions.MoreThanOneSlashCommandException;

import java.util.Optional;

import static utils.CommonStrings.AT;
import static utils.CommonStrings.BY;
import static utils.ErrorMessages.CREATE_DEADLINE_ERR_MSG;
import static utils.ErrorMessages.CREATE_DEADLINE_MORE_THAN_ONE_BY_ERR_MSG;
import static utils.ErrorMessages.CREATE_DEADLINE_NO_BY_DETAILS_ERR_MSG;
import static utils.ErrorMessages.CREATE_DEADLINE_NO_BY_ERR_MSG;
import static utils.ErrorMessages.CREATE_EVENT_ERR_MSG;
import static utils.ErrorMessages.CREATE_EVENT_MORE_THAN_ONE_AT_ERR_MSG;
import static utils.ErrorMessages.CREATE_EVENT_NO_AT_DETAILS_ERR_MSG;
import static utils.ErrorMessages.CREATE_EVENT_NO_AT_ERR_MSG;
import static utils.ErrorMessages.CREATE_TODO_ERR_MSG;
import static utils.ErrorMessages.CREATE_TODO_NO_DESCRIPTION_ERR_MSG;
import static utils.Mouth.speak;
import static utils.TaskUtil.isNullOrEmpty;
import static utils.TaskUtil.removeFirstWord;


public class Converter {

    public static Optional<Todo> getTodoFromUserInput(String userInput) {
        String description = removeFirstWord(userInput);
        try {
            if (isNullOrEmpty(description)) throw new EmptyDescriptionException();
            return Optional.of(new Todo(description));
        } catch (EmptyDescriptionException e) {
            speak(CREATE_TODO_NO_DESCRIPTION_ERR_MSG);
        } catch (Exception e) {
            speak(CREATE_TODO_ERR_MSG);
        }
        return Optional.empty();

    }

    public static Optional<Deadline> getDeadlineFromUserInput(String userInput) {
        userInput = removeFirstWord(userInput);
        try {
            String[] deadlineInputs = splitStrings(userInput, BY);
            return Optional.of(new Deadline(deadlineInputs[0], deadlineInputs[1]));
        } catch (MissingSlashCommandException e) {
            speak(CREATE_DEADLINE_NO_BY_ERR_MSG);
        } catch (MoreThanOneSlashCommandException e) {
            speak(CREATE_DEADLINE_MORE_THAN_ONE_BY_ERR_MSG);
        } catch (MissingSlashCommandDetailsException | ArrayIndexOutOfBoundsException e) {
            speak(CREATE_DEADLINE_NO_BY_DETAILS_ERR_MSG);
        } catch (Exception e) {
            speak(CREATE_DEADLINE_ERR_MSG);
        }
        return Optional.empty();
    }

    public static Optional<Event> getEventFromUserInput(String userInput) {
        userInput = removeFirstWord(userInput);
        try {
            String[] deadlineInputs = splitStrings(userInput, AT);
            return Optional.of(new Event(deadlineInputs[0], deadlineInputs[1]));
        } catch (MissingSlashCommandException e) {
            speak(CREATE_EVENT_NO_AT_ERR_MSG);
        } catch (MoreThanOneSlashCommandException e) {
            speak(CREATE_EVENT_MORE_THAN_ONE_AT_ERR_MSG);
        } catch (MissingSlashCommandDetailsException | ArrayIndexOutOfBoundsException e) {
            speak(CREATE_EVENT_NO_AT_DETAILS_ERR_MSG);
        } catch (Exception e) {
            speak(CREATE_EVENT_ERR_MSG);
        }
        return Optional.empty();
    }

    private static String[] splitStrings(String sentence, String splitter)
            throws MissingSlashCommandException, MoreThanOneSlashCommandException, MissingSlashCommandDetailsException {

        if (!sentence.contains(splitter)) throw new MissingSlashCommandException();
        try {
            String[] op = sentence.split(splitter);
            if (op.length > 2) throw new MoreThanOneSlashCommandException();
            if (isNullOrEmpty(op[0]) || isNullOrEmpty(op[1])) throw new MissingSlashCommandDetailsException();
            return op;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingSlashCommandDetailsException();
        }
    }
}

package nus.duke.parser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nus.duke.frontend.*;
import nus.duke.exceptions.*;
import nus.duke.enumerations.*;
import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents a parser that parses data.
 * Parser deals with making sense of the user command.
 */
public class Parser {
	private static Ui ui;

	/**
	 * Returns the command specified in a user input.
	 *
	 * @param userInput The task keyed in by the user
	 * @return The command specified in the task. For example, in "MARK 1", the command returned is "MARK".
	 */
	public static String getCommand(String userInput) {
		int idx = userInput.indexOf(SPACE);
		if (idx == -1){
			return userInput;
		} else {
			String command = userInput.substring(0, idx);
			return command;
		}
	}

	/**
	 * Returns true if the command specified is a valid command, and false if otherwise.
	 *
	 * @param command The command that was specified in a task.
	 * @return true (i.e. it is a valid command) or false (i.e. it is not a valid command)
	 */
	public static boolean isValidCommand(String command) {
		if ((command.equals(LegalCommandEnumerations.MARK.toString())) || (command.equals(LegalCommandEnumerations.UNMARK.toString())) ||
				(command.equals(LegalCommandEnumerations.DELETE.toString())) || (command.equals(LegalCommandEnumerations.VIEW.toString())) ||
				(command.equals(LegalCommandEnumerations.REMINDERS.toString())) || (command.equals(LegalCommandEnumerations.FILTER.toString())) ||
				(command.equals(LegalCommandEnumerations.EXIT.toString())) || (command.equals(LegalCommandEnumerations.TODO.toString())) ||
				(command.equals(LegalCommandEnumerations.DEADLINE.toString())) || (command.equals(LegalCommandEnumerations.EVENT.toString()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if the task was empty, and false if otherwise.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return true (i.e. it is an empty task) or false (i.e. it is not an empty task)
	 */
	public static boolean isEmptyTask(String userInput) {
		int idx = userInput.indexOf(SPACE);
		String str = userInput.substring(idx, userInput.length());
		if (str.isBlank()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if a date was supplied for a deadline or event task, and false if otherwise.
	 * For example, "DEADLINE finish assignment /by 01/12/2022" returns true.
	 * For example, "DEADLINE finish assignment /by " returns false.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return true (i.e. date was supplied) or false (i.e. date was not supplied)
	 */
	public static boolean hasDate(String userInput) {
		int start = 0;
		if (userInput.contains(BY)) {
			start = userInput.indexOf(BY) + 3;
		} else if (userInput.contains(ON)) {
			start = userInput.indexOf(ON) + 3;
		}

		int end = userInput.indexOf("[");
		String dateInString = userInput.substring(start, userInput.length());

		if ((end == -1) && dateInString.isBlank()) {
			return false;
		} else if ((end == -1) && dateInString.isBlank() == false) {
			dateInString = userInput.substring(start + 1, userInput.length());
		} else if (end != -1) {
			dateInString = userInput.substring(start, end);
		}

		String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
		Pattern regexPattern = Pattern.compile(regex);
		Matcher matcher = regexPattern.matcher(dateInString);
		return matcher.matches();
	}

	/**
	 * Returns true if a one-word user input is either VIEW, EXIT or REMINDERS
	 * For example, "VIEW" returns true.
	 * For example, "TODO" returns false since TODO cannot be empty.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return true (i.e. the one-word input is valid) or false (i.e. the one-word input is not valid)
	 */
	public static boolean isValidOneWordCommand(String userInput) {
		if (userInput.equals(LegalCommandEnumerations.VIEW.toString()) ||
				userInput.equals(LegalCommandEnumerations.EXIT.toString()) ||
				userInput.equals(LegalCommandEnumerations.REMINDERS.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if a user input had specified a command that needed additional inputs like date and venue information.
	 * For example, "VIEW" returns false.
	 * For example, "TODO" returns true since TODO cannot be empty.
	 *
	 * @param command The command that the user had specified in the task.
	 * @return true (i.e. the one-word input is valid) or false (i.e. the one-word input is not valid)
	 */
	public static boolean isValidCommandThatNeedsInput(String command) {
		if (command.equals(LegalCommandEnumerations.TODO.toString()) ||
				command.equals(LegalCommandEnumerations.DEADLINE.toString()) ||
				command.equals(LegalCommandEnumerations.EVENT.toString()) ||
				command.equals(LegalCommandEnumerations.MARK.toString()) ||
				command.equals(LegalCommandEnumerations.UNMARK.toString()) ||
				command.equals(LegalCommandEnumerations.FILTER.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if a user input has input errors, and false is otherwise.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return true (i.e. input has errors) or false (i.e. input does not have errors)
	 * @throws MissingKeywordException when a user does not follow the correct syntax when adding deadlines or events.
	 * @throws EmptyTaskException when a task is empty.
	 * @throws InvalidCommandException when a user does not enter a command from the command menu i.e. it is invalid.
	 * @throws MissingDateException when a user does not supply a date for a deadline or event task.
	 */
	public static boolean hasInputErrors(String userInput) throws MissingKeywordException, EmptyTaskException, InvalidCommandException, MissingDateException {
		String trimmedInput = userInput.trim();

		if (isValidOneWordCommand(trimmedInput)) {
			return false;
		} else if (isValidCommandThatNeedsInput(trimmedInput)) {
			throw new EmptyTaskException();
		}

		String command = getCommand(trimmedInput);
		if (isValidCommand(command) == false) {
			throw new InvalidCommandException();
		}

		if (isValidCommand(command) == false && isEmptyTask(userInput) == true) {
			throw new InvalidCommandException();
		}

		if ((isValidCommand(command) == true) && (isEmptyTask(userInput) == true)) {
			throw new EmptyTaskException();
		}

		if (command.equals(LegalCommandEnumerations.DEADLINE.toString()) && (userInput.contains(BY) == false)) {
			throw new MissingKeywordException();
		}

		if (command.equals(LegalCommandEnumerations.EVENT.toString()) && (userInput.contains(AT) == false) && (userInput.contains(ON) == false)) {
			throw new MissingKeywordException();
		}

		if (command.equals(LegalCommandEnumerations.EVENT.toString()) && (userInput.contains(AT) == false) && (userInput.contains(ON) == true)) {
			throw new MissingKeywordException();
		}

		if (command.equals(LegalCommandEnumerations.EVENT.toString()) && (userInput.contains(AT) == true) && (userInput.contains(ON) == false)) {
			throw new MissingKeywordException();
		}

		if (command.equals(LegalCommandEnumerations.DEADLINE.toString()) && (userInput.contains(BY) == true) && hasDate(userInput) == false) {
			throw new MissingDateException();
		}

		if (command.equals(LegalCommandEnumerations.EVENT.toString()) && (userInput.contains(AT) == true) && (userInput.contains(ON) == true) && hasDate(userInput) == false) {
			throw new MissingDateException();
		}
		return false;
	}

	/**
	 * Parses each tasks and returns the command specified in the task after parsing.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return The command specified in the task.
	 */
	public static boolean parse(String userInput) {
		boolean hasError;
		Scanner s;
		try {
			hasError = hasInputErrors(userInput);
		} catch (EmptyTaskException ete) {
			System.out.println(TASK_CANNOT_BE_EMPTY_ERROR_MESSAGE);
			hasError = true;
		} catch (InvalidCommandException ice) {
			System.out.println(INVALID_COMMAND_ERROR_MESSAGE);
			ui.printCommandMenu();
			hasError = true;
		} catch (MissingKeywordException mke) {
			System.out.println(MISSING_KEYWORD_ERROR_MESSAGE);
			hasError = true;
		} catch (MissingDateException mde) {
			System.out.println(MISSING_DATE_ERROR_MESSAGE);
			hasError = true;
		}

		if (hasError) {
			return false; // i.e. there are input errors
		} else {
			return true; // i.e. there are no input errors.
		}
	}
}

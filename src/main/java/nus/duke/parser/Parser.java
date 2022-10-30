package nus.duke.parser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nus.duke.frontend.*;
import nus.duke.tasklist.*;
import nus.duke.exceptions.*;

import static nus.duke.frontend.CommonPrintStatements.*;

import nus.duke.enumerations.*;

public class Parser {
	private static Ui ui;

	public static String getCommand(String userInput) {
		if (userInput.length() == 4) {
			return userInput.substring(0, 4);
		} else {
			int idx = userInput.indexOf(SPACE);
			String command = userInput.substring(0, idx);
			return command;
		}
	}

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

	public static boolean isEmptyTask(String userInput) {
		int idx = userInput.indexOf(SPACE);
		String str = userInput.substring(idx, userInput.length());
		if (str.isBlank()) {
			return true;
		} else {
			return false;
		}
	}

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

	public static boolean isValidOneWordCommand(String command) {
		if (command.equals(LegalCommandEnumerations.VIEW.toString()) ||
				command.equals(LegalCommandEnumerations.EXIT.toString()) ||
				command.equals(LegalCommandEnumerations.REMINDERS.toString())) {
			return true;
		} else {
			return false;
		}
	}

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

	public static String parse(String userInput) {
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
			Ui ui = new Ui();
			s = new Scanner(System.in);
			System.out.println(ASK_FOR_USER_INPUT_AFTER_ERROR_MESSAGE_WAS_DISPLAYED_MESSAGE);
			String newUserInput = ui.getUserInput(s);
			return newUserInput;
		} else {
			return getCommand(userInput);
		}
	}
}

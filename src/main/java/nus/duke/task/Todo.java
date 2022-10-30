package nus.duke.task;

import nus.duke.enumerations.LegalTaskEnumerations;

import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents a todo task
 */
public class Todo extends Task {

	public Todo(String userInput) {
		super(userInput);
	}

	/**
	 * Returns the task type
	 * "T" for TODO.
	 * "D" for DEADLINE.
	 * "E" for EVENT.
	 *
	 * @return the task type in string.
	 */
	@Override
	public String getTaskType() {
		return LegalTaskEnumerations.T.toString();
	}

	@Override
	public String getTaskDetails() {
		return EMPTY_STRING;
	}

	@Override
	public String getDescription(String userInput) {
		return EMPTY_STRING;
	}

	@Override
	public String getDateInStr(String userInput) {
		return EMPTY_STRING;
	}
}

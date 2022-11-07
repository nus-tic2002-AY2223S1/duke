package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import nus.duke.enumerations.*;

import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
	protected LocalDate localDate;

	/**
	 * Constructor.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return nothing. This is a constructor.
	 */
	public Deadline(String userInput) {
		super(userInput);
		String description = this.getDescription(userInput);
		super.setDescription(description);
		this.localDate = processDate(userInput);
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
		return LegalTaskEnumerations.D.toString();
	}

	/**
	 * Returns the deadline's due date in a meaningful format.
	 *
	 * @return the deadline's due date.
	 */
	@Override
	public String getTaskDetails() {
		String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
		date = "(by: " + date + ")";
		return date;
	}

	/**
	 * Returns the task description.
	 *
	 * @return the task description.
	 */
	@Override
	public String getDescription(String userInput) {
		int end = userInput.indexOf(BY);
		return userInput.substring(0, end);
	}

	/**
	 * Returns the deadline's due date in string.
	 *
	 * @return the deadline's due date in string.
	 */
	@Override
	public String getDateInStr(String userInput) {
		int start = userInput.indexOf(BY) + 4;
		if (userInput.indexOf(MARKED_AS_TRUE) == -1 || userInput.indexOf(MARKED_AS_FALSE) == -1) {
			return userInput.substring(start, userInput.length()).trim();
		} else {
			return userInput.substring(start, userInput.length() - 3).trim();
		}
	}

	/**
	 * Returns the date of the calling object.
	 *
	 * @return A date in LocalDate type.
	 */
	@Override
	public LocalDate getDate() {
		return this.localDate;
	}
}
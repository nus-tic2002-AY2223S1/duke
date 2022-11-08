package nus.duke.task;

import nus.duke.enumerations.LegalTaskEnumerations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents a todo task
 */
public class Todo extends Task {
	protected LocalDate localDate;

	/**
	 * Constructor.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return nothing. This is a constructor.
	 * Take note that the assumption for all TODOs is that the last date to complete the task is the last day of the year.
	 */
	public Todo(String userInput) {
		super(userInput);
		LocalDate now = LocalDate.now(); // e.g 2022-11-23
		LocalDate lastDay = now.with(lastDayOfYear()); // 2022-12-31
		this.localDate = lastDay.plusYears(100);
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

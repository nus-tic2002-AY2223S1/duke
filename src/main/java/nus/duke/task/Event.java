package nus.duke.task;

import nus.duke.enumerations.LegalTaskEnumerations;
import static nus.duke.frontend.CommonPrintStatements.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task
 */
public class Event extends Task {
	protected String at; // /at a specific place
	protected LocalDate localDate; // /on a specific date

	/**
	 * Constructor.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return nothing. This is a constructor.
	 */
	public Event(String userInput) {
		super(userInput);
		String description = this.getDescription(userInput);
		super.setDescription(description);
		this.at = getVenue(userInput);
		this.localDate = this.processDate(userInput);
	}

	/**
	 * Returns the venue of the event.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return the venue of the event.
	 */
	public String getVenue(String userInput) {
		int start = userInput.indexOf(AT) + 4;
		int end = userInput.indexOf(ON);
		return userInput.substring(start, end).trim();
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
		return LegalTaskEnumerations.E.toString();
	}

	/**
	 * Returns the event's date and venue in a meaningful format.
	 *
	 * @return the event's date and venue.
	 */
	@Override
	public String getTaskDetails() {
		String venue = "(Venue: " + this.at + ")";
		String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
		date = "(Time: " + date + ")";
		return venue + date;
	}

	/**
	 * Returns the event description.
	 *
	 * @return the event description.
	 */
	@Override
	public String getDescription(String userInput) {
		int end = userInput.indexOf(AT);
		return userInput.substring(0, end);
	}

	/**
	 * Returns the event's date in string.
	 *
	 * @return the event's date in string.
	 */
	@Override
	public String getDateInStr(String userInput) {
		int start = userInput.indexOf(ON) + 4;
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

package duke.impl;

import java.text.ParseException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateProcessor;

/**
 * Main class to generate text responses
 */
public class UiEn extends Ui {
    protected String format = "%s";

    /**
     * Enum of messages
     */
    public enum UiMessage {
        GENERIC(""),
        GENERIC_FORMATTED("%s"),
        INFO_WELCOME("Hello from Duke. What can I do for you?"),
        INFO_WELCOME_EXISTING("Hello again, %s! Welcome back. What can I do for you?"),
        INFO_LAST_SAVED("[Last Modified on %s]"),
        INFO_GOODBYE("Bye. Hope to see you again soon!"),
        ERROR_COMMAND_UNKNOWN("I'm sorry, but I don't know what that means :( "
                + "\nSpecify a Todo / Deadline / Event. "
                + "\n\t \u27a4 Todo <Task Name>"),
        ERROR_PROCESS_ACTION("The selection to %s cannot be empty."),
        ERROR_PROCESS_COMMAND("The description of %s cannot be empty."),
        ERROR_FIND_DATE("The date to search cannot be empty."),
        ERROR_FIND_TASK("The keyword to search cannot be empty."),
        ERROR_VIEW_SCHEDULE("The date to search cannot be empty."),
        ERROR_RESTORE("The file selection to restore cannot be empty."),
        ERROR_ARCHIVE("The file selection to archive cannot be empty.");

        public final String text;

        UiMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    public UiEn(LocaleRegion l) {
    }

    public UiEn() {
    }

    @Override
    public String sendConfirmedOutput(StringBuilder message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED.getText(), String.valueOf(message));
    }

    @Override
    public String sendGenericPlain(String message) {
        return sendPlain(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericInfo(String message) {
        return sendInfo(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericWarning(String message) {
        return sendWarning(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericFatal(String message) {
        return sendFatal(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericConfirmation(String message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendProcessActionError(String message) {
        return sendFatal(UiMessage.ERROR_PROCESS_ACTION.getText(), message);
    }

    @Override
    public String sendProcessCommandError(String message) {
        return sendFatal(UiMessage.ERROR_PROCESS_COMMAND.getText(), message);
    }

    @Override
    public String sendCommandUnknownError() {
        return sendFatal(UiMessage.ERROR_COMMAND_UNKNOWN.getText(), "");
    }

    @Override
    public String sendProcessFindDateError() {
        return sendFatal(UiMessage.ERROR_FIND_DATE.getText(), "");
    }

    @Override
    public String sendProcessFindTaskError() {
        return sendFatal(UiMessage.ERROR_FIND_TASK.getText(), "");
    }

    @Override
    public String sendProcessViewScheduleError() {
        return sendFatal(UiMessage.ERROR_VIEW_SCHEDULE.getText(), "");
    }

    @Override
    public String sendProcessRestoreError() {
        return sendFatal(UiMessage.ERROR_RESTORE.getText(), "");
    }

    @Override
    public String sendProcessArchiveError() {
        return sendFatal(UiMessage.ERROR_ARCHIVE.getText(), "");
    }

    /**
     * Generate welcome message
     * If saved tasks are restored, display all tasks
     * Else only display welcome message
     *
     * @param t TaskList loaded from file
     * @return Welcome message text
     */
    @Override
    public String[] sendWelcomeMessage(TaskList t, boolean shouldList) {
        if (t.getLastInfo() != null) {
            if (!shouldList) {
                return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), t.getLastInfo()[0])};
            }
            return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), t.getLastInfo()[0]),
                    printList(t.getList(), true),
                    sendPlain(UiMessage.INFO_LAST_SAVED.getText(),
                            DateProcessor.unixToStringEn(Long.parseLong(t.getLastInfo()[1])))};
        } else {
            return new String[]{sendInfo(UiMessage.INFO_WELCOME.getText(), "")};
        }
    }

    public String sendGoodbyeMessage() {
        return sendInfo(UiMessage.INFO_GOODBYE.getText(), "");
    }

    /**
     * Display message after adding new tasks
     *
     * @param task Description of task added
     * @param size Current number of tasks
     * @return Message
     */
    @Override
    public String printNewTasks(String task, int size) {
        StringBuilder s = new StringBuilder();
        s.append("Got it. I've added this task:\n\t")
                .append(task)
                .append("\n\tNow you have ")
                .append(size)
                .append(size > 1 ? " tasks " : " task ")
                .append("in the list.");
        return sendConfirmedOutput(s);
    }

    /**
     * Display message after removing task by index
     *
     * @param task Description of task removed
     * @param size Current number of tasks
     * @return Message
     */
    @Override
    public String printTaskRemovedByIndex(String task, int size) {
        StringBuilder s = new StringBuilder();
        s.append("Noted. I've removed this task:\n\t")
                .append(task)
                .append("\n\tNow you have ")
                .append(size)
                .append(size > 1 ? " tasks " : " task ")
                .append("in the list.");
        return sendConfirmedOutput(s);
    }

    /**
     * Display message after marking task as completed
     *
     * @param task   Description of task removed
     * @param isMark Mark state of task
     * @return Message
     */
    @Override
    public String printMarkTask(String task, Boolean isMark) {
        StringBuilder s = new StringBuilder();
        s.append(isMark ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n")
                .append(task);
        return sendConfirmedOutput(s);
    }

    /**
     * Display all tasks
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @return List of tasks
     */
    @Override
    public String printList(ArrayList<Task> tasks, Boolean withIndex) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("You have no task.");
        } else {
            s.append("Here are the task(s) in your list:\n");
            buildList(tasks, withIndex, s);
        }
        return sendConfirmedOutput(s);
    }

    /**
     * Display all tasks selected by date
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @param date      Selected date
     * @return List of tasks
     */
    @Override
    public String printSelectedList(ArrayList<Task> tasks, Boolean withIndex, String date) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("You have no task scheduled on ")
                    .append(date);
        } else {
            s.append("Here are the task(s) scheduled on this day:\n");
            buildList(tasks, withIndex, s);
        }
        return sendConfirmedOutput(s);
    }

    /**
     * Display all tasks selected by keyword
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @param keyword   Selected keyword
     * @return List of tasks
     */
    @Override
    public String printFoundList(ArrayList<Task> tasks, Boolean withIndex, String keyword) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("You have no task with keyword '")
                    .append(keyword)
                    .append("'");
        } else {
            s.append("Here are the task(s) that contains '")
                    .append(keyword)
                    .append("':\n");
            buildList(tasks, withIndex, s);
        }
        return sendConfirmedOutput(s);
    }

    @Override
    public String printInvalidDateFormat() {
        return sendGenericWarning("Invalid date format. Date time has to be dd/mm/yyyy.");
    }

    @Override
    public String printInvalidMonthFormat() {
        return sendGenericWarning("Invalid month format. Month has to be between 01 ~ 12.");
    }

    @Override
    public String printInvalidYearFormat() {
        return sendGenericWarning("Invalid year format. Year has to be yyyy.");
    }

    @Override
    public String printInvalidDateTimeFormat() {
        return sendGenericWarning("Invalid date/time format. Date time has to be dd/mm/yyyy HHmm.");
    }

    @Override
    public String printInvalidTimeFormat() {
        return sendGenericWarning("Invalid time format. Time has to be 0000 ~ 2359.");
    }

    @Override
    public String printInconsistentTimeRangeFormat() {
        return sendGenericWarning("Invalid time range. Range start and end should be consistent. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    @Override
    public String printInvalidTimeRangeFormat() {
        return sendGenericWarning("Invalid format. "
                + "Range has to be \n\tdd/mm/yyyy  "
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n"
                + "\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    @Override
    public String printUnspecifiedTimeRangeFormat() {
        return sendGenericWarning("Specify a range. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    @Override
    public String printInvalidTDateSeparatorFormat() {
        return sendGenericWarning("Date range should be separated by '-'");
    }

    @Override
    public String printParseExceptionMessage(ParseException e) {
        return sendGenericFatal("I could not recognise this date. " + e.getMessage());
    }

    @Override
    public String getEventLabel() {
        return "[Event]";
    }

    @Override
    public String getDeadlineLabel() {
        return "[Deadline]";
    }

    @Override
    public String getTodoLabel() {
        return "[Todo]";
    }

    @Override
    public String getEventHeader() {
        return "At: ";
    }

    @Override
    public String getHeader() {
        return "By: ";
    }
}

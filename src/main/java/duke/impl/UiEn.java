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
        INFO_HELP("I can understand the commands below:\n\n"
                + "list: List your current tasks.\n"
                + "todo <name>: Add a Todo task.\n"
                + "event <name>: Add an event.\n"
                + "deadline <name>: Add a deadline.\n"
                + "mark <index>: Mark a task as done.\n"
                + "unmark <index>: Unmark a task.\n"
                + "delete <index>: Delete a task.\n"
                + "day <dd/mm/yyyy>: Find all tasks schedule on this day.\n"
                + "find <keyword>: Find all tasks that contains this keyword.\n"
                + "archive: Archive the current chat and start afresh.\n"
                + "restore: View the list of available archives to restore.\n"
                + "restore <index>: Select record to be restored.\n"),
        ERROR_COMMAND_UNKNOWN("I'm sorry, but I don't know what that means :( "
                + "\nSpecify a Todo / Deadline / Event. "
                + "\n\t \u27a4 Todo <Task Name>"),
        ERROR_PROCESS_ACTION("The selection to %s cannot be empty."),
        ERROR_PROCESS_COMMAND("The description of %s cannot be empty."),
        ERROR_FIND_DATE_INPUT("The date to search cannot be empty.\n\t \u27a4 day <date>"),
        ERROR_FIND_TASK_INPUT("The keyword to search cannot be empty.\n\t \u27a4 find <keyword>"),
        INFO_ARCHIVE("Successfully archived records."),
        ERROR_ARCHIVE_SELECTION("The file selection to archive cannot be empty."),
        ERROR_ARCHIVE("Failed to archive records."),
        ERROR_ARCHIVE_NO_FILE_FOUND("You have no archived records yet."),
        INFO_RESTORE("Successfully restored record."),
        ERROR_RESTORE("Failed to retrieve archive records."),
        ERROR_RESTORE_SELECTION("The file selection to restore cannot be empty.\n\t \u27a4 restore <index>"),
        ERROR_RESTORE_NO_RECORD("Type restore to check the list of archives first!"),
        INFO_LIST_FILES_HEADER("Select from the files below by entering restore <index>.\n"),
        INFO_LIST_FILES_FOOTER("Chat will be refreshed after restoring."),
        ERROR_GET_INDEX("This is not a valid index. Choose from the  %s tasks."),
        ERROR_GET_ARCHIVE_INDEX("This is not a valid index. Choose from the  %s records."),
        ERROR_IS_INTEGER("Enter the numeric item index."),
        ERROR_UNKNOWN_COMMAND("Unknown Command."),
        INFO_ADD_TASK("Okay, I have added the following task:\n\t%s\n\tYou now have %s."),
        INFO_REMOVE_TASK("Okay, I have removed the following task:\n\t%s\n\tYou now have %s."),
        INFO_MARK_TASK("Okay! I have marked the following task as completed:\n%s"),
        INFO_UNMARK_TASK("Okay, I have marked the following task as incomplete:\n%s"),
        INFO_PRINT_TASK("Here are your task(s):\n%s"),
        INFO_PRINT_NO_TASK("You do not have any tasks at the moment."),
        INFO_PRINT_DAY_TASK("These are your tasks on this day:\n%s"),
        INFO_PRINT_NO_DAY_TASK("You do not have any tasks on %s."),
        INFO_PRINT_FIND_TASK("These are your tasks that contains '%s':\n%s"),
        INFO_PRINT_NO_FIND_TASK("You do not have any tasks that contains '%s'."),
        ERROR_INVALID_DATE_FORMAT("Invalid date format. Date time has to be dd/mm/yyyy."),
        ERROR_INVALID_MONTH_FORMAT("Invalid month format. Month has to be between 01 ~ 12."),
        ERROR_INVALID_YEAR_FORMAT("Invalid year format. Year has to be yyyy."),
        ERROR_INVALID_DATE_TIME_FORMAT("Invalid date/time format. Date time has to be dd/mm/yyyy HHmm."),
        ERROR_INVALID_TIME_FORMAT("Invalid time format. Time has to be 0000 ~ 2359."),
        ERROR_INCONSISTENT_TIME_RANGE_FORMAT("Invalid time range. Range start and end should be consistent. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm."),
        ERROR_INVALID_TIME_RANGE_FORMAT("Invalid format. "
                + "Range has to be \n\tdd/mm/yyyy  "
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n"
                + "\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm."),
        ERROR_UNSPECIFIED_TIME_RANGE_FORMAT("Specify a range. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm."),
        ERROR_INVALID_DATE_SEPARATOR_FORMAT("Date range should be separated by '-'"),
        ERROR_PARSE_EXCEPTION("I could not recognise this date. %s"),
        ERROR_DATE_END_BEFORE_START_ERROR("End is earlier than start. Time travel is not allowed."),
        ERROR_DATE_START_EQUALS_END_ERROR("Start and end are the same."),

        ERROR_TOO_MANY_TIME_RANGES_ERROR("You can only specify one range.");

        public final String text;

        UiMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    /**
     * Enum of task labels.
     */
    public enum UiLabel {
        LABEL_DEADLINE("[Deadline]"),
        LABEL_EVENT("[Event]"),
        LABEL_TODO("[Todo]"),
        LABEL_EVENT_HEADER("At: "),
        LABEL_HEADER("By: ");

        public final String text;

        UiLabel(String text) {
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
    public String sendConfirmedOutput(String message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED.getText(), message);
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
        return sendFatal(UiMessage.ERROR_FIND_DATE_INPUT.getText(), "");
    }

    @Override
    public String sendProcessFindTaskError() {
        return sendFatal(UiMessage.ERROR_FIND_TASK_INPUT.getText(), "");
    }

    @Override
    public String sendProcessRestoreError() {
        return sendFatal(UiMessage.ERROR_RESTORE_SELECTION.getText(), "");
    }

    @Override
    public String printNoArchiveFileFoundError() {
        return sendFatal(UiMessage.ERROR_ARCHIVE_NO_FILE_FOUND.getText(), "");
    }

    @Override
    public String sendProcessArchiveError() {
        return sendFatal(UiMessage.ERROR_ARCHIVE_SELECTION.getText(), "");
    }

    @Override
    public String printProcessArchiveMessage() {
        return sendGenericConfirmation(UiMessage.INFO_ARCHIVE.getText());
    }

    @Override
    public String printProcessArchiveFailureMessage() {
        return sendGenericFatal(UiMessage.ERROR_ARCHIVE.getText());
    }

    @Override
    public String printProcessRestoreMessage() {
        return sendGenericConfirmation(UiMessage.INFO_RESTORE.getText());
    }

    @Override
    public String printProcessRestoreErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_RESTORE.getText());
    }

    @Override
    public String printProcessRestoreNoRecordMessage(String e) {
        return sendGenericWarning(UiMessage.ERROR_RESTORE_NO_RECORD.getText());
    }

    @Override
    public String printListFilesHeaderMessage() {
        return sendGenericInfo(UiMessage.INFO_LIST_FILES_HEADER.getText());
    }

    @Override
    public String printListFilesFooterMessage() {
        return sendGenericInfo(UiMessage.INFO_LIST_FILES_FOOTER.getText());
    }

    @Override
    public String printGetIndexErrorMessage(int size) {
        return sendGenericWarning(String.format(UiMessage.ERROR_GET_INDEX.getText(), size));
    }

    @Override
    public String printGetArchiveIndexErrorMessage(int size) {
        return sendGenericWarning(String.format(UiMessage.ERROR_GET_ARCHIVE_INDEX.getText(), size));

    }

    @Override
    public String printIsIntegerErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_IS_INTEGER.getText());
    }

    @Override
    public String printUnknownCommandErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_UNKNOWN_COMMAND.getText());
    }

    /**
     * Generate a welcome message upon chat initialization.
     * The tasks argument is mandatory, but the underlying list is not required to be populated.
     * The shouldList argument is mandatory.
     * <p>
     * If tasks contains a list of tasks, and shouldList is true, the following will be displayed:
     * - Welcome greeting message with username
     * - List of tasks
     * - Last modified date and time of task list
     * If tasks contains no task, the following will be displayed:
     * - Welcome greeting message with username
     * <p>
     * Message language is dependent on the Ui Locale Region.
     *
     * @param tasks      TaskList loaded from file or an empty list.
     * @param shouldList Toggle setting of List on Launch.
     * @return Welcome message text.
     */
    @Override
    public String[] sendWelcomeMessage(TaskList tasks, boolean shouldList) {
        if (tasks.getLastInfo() != null) {
            if (!shouldList) {
                return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), tasks.getLastInfo()[0])};
            }
            return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), tasks.getLastInfo()[0]),
                    printList(tasks.getList(), true),
                    sendPlain(UiMessage.INFO_LAST_SAVED.getText(),
                            DateProcessor.unixToStringEn(Long.parseLong(tasks.getLastInfo()[1])))};
        } else {
            return new String[]{sendInfo(UiMessage.INFO_WELCOME.getText(), "")};
        }
    }

    @Override
    public String sendHelpMessage() {
        return sendInfo(UiMessage.INFO_HELP.getText(), "");
    }

    @Override
    public String sendGoodbyeMessage() {
        return sendInfo(UiMessage.INFO_GOODBYE.getText(), "");
    }

    @Override
    public String printNewTasks(String task, int size) {
        return sendConfirmedOutput(
                String.format(
                        UiMessage.INFO_ADD_TASK.getText(), task, size < 2 ? size + " task" : size + " tasks"));
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
        return sendConfirmedOutput(
                String.format(
                        UiMessage.INFO_REMOVE_TASK.getText(), task, size < 2 ? size + " task" : size + " tasks"));
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
        return (isMark
                ? sendConfirmedOutput(String.format(UiMessage.INFO_MARK_TASK.getText(), task))
                : sendConfirmedOutput(String.format(UiMessage.INFO_UNMARK_TASK.getText(), task)));
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
        if (tasks.size() == 0) {
            return sendConfirmedOutput(UiMessage.INFO_PRINT_NO_TASK.getText());
        }
        return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_TASK.getText(), buildList(tasks, withIndex)));
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
        if (tasks.size() == 0) {
            return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_NO_DAY_TASK.getText(), date));
        }
        return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_DAY_TASK.getText(), buildList(tasks, withIndex)));
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
        if (tasks.size() == 0) {
            return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_NO_FIND_TASK.getText(), keyword));
        }
        return sendConfirmedOutput(
                String.format(
                        UiMessage.INFO_PRINT_FIND_TASK.getText(), keyword, buildList(tasks, withIndex)));
    }

    @Override
    public String printInvalidDateFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_DATE_FORMAT.getText());
    }

    @Override
    public String printInvalidMonthFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_MONTH_FORMAT.getText());
    }

    @Override
    public String printInvalidYearFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_YEAR_FORMAT.getText());
    }

    @Override
    public String printInvalidDateTimeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_DATE_TIME_FORMAT.getText());
    }

    @Override
    public String printInvalidTimeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_TIME_FORMAT.getText());
    }

    @Override
    public String printInconsistentTimeRangeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INCONSISTENT_TIME_RANGE_FORMAT.getText());
    }

    @Override
    public String printInvalidTimeRangeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_TIME_RANGE_FORMAT.getText());
    }

    @Override
    public String printUnspecifiedTimeRangeFormat() {
        return sendGenericWarning(UiMessage.ERROR_UNSPECIFIED_TIME_RANGE_FORMAT.getText());
    }

    @Override
    public String printTooManyTimeRangesFormat() {
        return sendGenericWarning(UiMessage.ERROR_TOO_MANY_TIME_RANGES_ERROR.getText());
    }

    @Override
    public String printInvalidTDateSeparatorFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_DATE_SEPARATOR_FORMAT.getText());
    }

    @Override
    public String printParseExceptionMessage(ParseException e) {
        return sendGenericFatal(String.format(UiMessage.ERROR_PARSE_EXCEPTION.getText(), e.getMessage()));
    }

    @Override
    public String printDateStartBeforeEndError() {
        return sendGenericWarning(UiMessage.ERROR_DATE_END_BEFORE_START_ERROR.getText());
    }

    @Override
    public String printDateStartEqualsEndError() {
        return sendGenericWarning(UiMessage.ERROR_DATE_START_EQUALS_END_ERROR.getText());
    }

    @Override
    public String getEventLabel() {
        return UiLabel.LABEL_EVENT.getText();
    }

    @Override
    public String getDeadlineLabel() {
        return UiLabel.LABEL_DEADLINE.getText();
    }

    @Override
    public String getTodoLabel() {
        return UiLabel.LABEL_TODO.getText();
    }

    @Override
    public String getEventHeader() {
        return UiLabel.LABEL_EVENT_HEADER.getText();
    }

    @Override
    public String getHeader() {
        return UiLabel.LABEL_HEADER.getText();
    }
}

package duke.impl;

import java.text.ParseException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateProcessor;

/**
 * Main class to generate text responses
 */
public abstract class Ui {
    /**
     * Enum of locales
     */
    public enum LocaleRegion {
        EN, CN
    }

    /**
     * Enum of icon types
     */
    public enum UiIcon {
        /**
         * Icon of FATAL messages
         */
        FATAL("\u2716"),
        /**
         * Icon of WARNING messages
         */
        WARNING("\u0021"),
        /**
         * Icon of INFO messages
         */
        INFO("\u00bb"),
        /**
         * Icon of CONFIRMATION messages
         */
        CONFIRMATION("\u2714");

        public final String icon;

        UiIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon() {
            return this.icon;
        }
    }

    /**
     * Enum of Messages
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
        ERROR_FIND_DATE_INPUT("The date to search cannot be empty.\n\t \u27a4 day <date>"),
        ERROR_FIND_TASK_INPUT("The keyword to search cannot be empty.\n\t \u27a4 find <keyword>"),
        INFO_ARCHIVE("Successfully archived records."),
        ERROR_ARCHIVE_SELECTION("The file selection to archive cannot be empty."),
        ERROR_ARCHIVE("Failed to archive records."),
        INFO_RESTORE("Successfully restored record."),
        ERROR_RESTORE("Failed to retrieve archive records."),
        ERROR_RESTORE_SELECTION("The file selection to restore cannot be empty.\n\t \u27a4 restore <index>"),
        ERROR_RESTORE_NO_RECORD("Failed to restore records."),
        INFO_LIST_FILES_HEADER("Select from the files below by entering restore <index>."),
        INFO_LIST_FILES_FOOTER("Chat will be refreshed after restoring."),
        ERROR_GET_INDEX("This is not a valid index. Choose from the  %s tasks."),
        ERROR_GET_ARCHIVE_INDEX("This is not a valid index. Choose from the  %s records."),
        ERROR_IS_INTEGER("Enter the numeric item index."),
        ERROR_UNKNOWN_COMMAND("Unknown Command.");

        public final String text;

        UiMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    protected String format = "%s";

    public Ui(LocaleRegion l) {
    }

    public Ui() {
    }


    public String sendConfirmedOutput(StringBuilder message) {
        return sendConfirmation(UiEn.UiMessage.GENERIC_FORMATTED.getText(), String.valueOf(message));
    }

    public String sendGenericPlain(String message) {
        return sendPlain(UiEn.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericInfo(String message) {
        return sendInfo(UiEn.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericWarning(String message) {
        return sendWarning(UiEn.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericFatal(String message) {
        return sendFatal(UiEn.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericConfirmation(String message) {
        return sendConfirmation(UiEn.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendProcessActionError(String message) {
        return sendFatal(UiEn.UiMessage.ERROR_PROCESS_ACTION.getText(), message);
    }


    public String sendProcessCommandError(String message) {
        return sendFatal(UiEn.UiMessage.ERROR_PROCESS_COMMAND.getText(), message);
    }

    protected String sendPlain(String s, String m) {
        return String.format("%s%n", String.format(s, m));
    }

    protected String sendWarning(String s, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.WARNING.getIcon(), String.format(s, m)));
    }

    protected String sendInfo(String s, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.INFO.getIcon(), String.format(s, m)));
    }

    protected String sendFatal(String s, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.FATAL.getIcon(), String.format(s, m)));
    }

    protected String sendConfirmation(String s, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.CONFIRMATION.getIcon(), String.format(s, m)));
    }

    public String sendCommandUnknownError() {
        return sendFatal(UiMessage.ERROR_COMMAND_UNKNOWN.getText(), "");
    }

    public String sendProcessFindDateError() {
        return sendFatal(UiMessage.ERROR_FIND_DATE_INPUT.getText(), "");
    }

    public String sendProcessFindTaskError() {
        return sendFatal(UiMessage.ERROR_FIND_TASK_INPUT.getText(), "");
    }

    public String sendProcessRestoreError() {
        return sendFatal(UiMessage.ERROR_RESTORE_SELECTION.getText(), "");
    }

    public String sendProcessArchiveError() {
        return sendFatal(UiMessage.ERROR_ARCHIVE_SELECTION.getText(), "");
    }

    public String printProcessArchiveMessage() {
        return sendGenericConfirmation(UiMessage.INFO_ARCHIVE.getText());
    }

    public String printProcessArchiveFailureMessage() {
        return sendGenericFatal(UiMessage.ERROR_ARCHIVE.getText());
    }

    public String printProcessRestoreMessage() {
        return sendGenericConfirmation(UiMessage.INFO_RESTORE.getText());
    }

    public String printProcessRestoreErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_RESTORE.getText());
    }

    public String printProcessRestoreNoRecordMessage() {
        return sendGenericWarning(UiMessage.ERROR_RESTORE_NO_RECORD.getText());
    }

    public String printListFilesHeaderMessage() {
        return sendGenericInfo(UiMessage.INFO_LIST_FILES_HEADER.getText());
    }

    public String printListFilesFooterMessage() {
        return sendGenericInfo(UiMessage.INFO_LIST_FILES_FOOTER.getText());
    }

    public String printGetIndexErrorMessage(int size) {
        return sendGenericWarning(String.format(UiMessage.ERROR_GET_INDEX.getText(), size));
    }

    public String printGetArchiveIndexErrorMessage(int size) {
        return sendGenericWarning(String.format(UiMessage.ERROR_GET_ARCHIVE_INDEX.getText(), size));
    }

    public String printIsIntegerErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_IS_INTEGER.getText());
    }

    public String printUnknownCommandErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_UNKNOWN_COMMAND.getText());
    }

    /**
     * Generate welcome message
     * If saved tasks are restored, display all tasks
     * Else only display welcome message
     *
     * @param t TaskList loaded from file
     * @return Welcome message text
     */
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

    public abstract String printNewTasks(String task, int size);

    public abstract String printTaskRemovedByIndex(String task, int size);

    public abstract String printMarkTask(String task, Boolean isMark);

    /**
     * Display all tasks
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @return List of tasks
     */
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

    void buildList(ArrayList<Task> tasks, Boolean withIndex, StringBuilder s) {
        for (int i = 0; i < tasks.size(); i++) {
            String suffix = "";
            if (i != tasks.size() - 1) {
                suffix = "\n";
            }
            if (withIndex) {
                s.append(i + 1).append(".");
            }
            s.append(tasks.get(i).toString())
                    .append(suffix);
        }
    }

    /**
     * Display task list after performing keyword search.
     *
     * @param tasks     List of tasks found
     * @param withIndex Whether to display index
     * @param keyword   Keyword used in search
     * @return Message of search result
     */
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

    /**
     * Display task list after performing date search.
     *
     * @param tasks     List of tasks on this day
     * @param withIndex Whether to display index
     * @param date      Date used in search
     * @return Message of search result
     */
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

    public String printInvalidDateFormat() {
        return sendGenericWarning("Invalid date format. Date time has to be dd/mm/yyyy.");
    }

    public String printInvalidMonthFormat() {
        return sendGenericWarning("Invalid month format. Month has to be between 01 ~ 12.");
    }

    public String printInvalidYearFormat() {
        return sendGenericWarning("Invalid year format. Year has to be yyyy.");
    }

    public String printInvalidDateTimeFormat() {
        return sendGenericWarning("Invalid date/time format. Date time has to be dd/mm/yyyy HHmm.");
    }

    public String printInvalidTimeFormat() {
        return sendGenericWarning("Invalid time format. Time has to be 0000 ~ 2359.");
    }

    /**
     * Print inconsistent time range format error message.
     *
     * @return Error message
     */
    public String printInconsistentTimeRangeFormat() {
        return sendGenericWarning("Invalid time range. Range start and end should be consistent. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    /**
     * Print invalid time range format error message.
     *
     * @return Error message
     */
    public String printInvalidTimeRangeFormat() {
        return sendGenericWarning("Invalid format. "
                + "Range has to be \n\tdd/mm/yyyy  "
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n"
                + "\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    /**
     * Print unspecified time range format error message.
     *
     * @return Error message
     */
    public String printUnspecifiedTimeRangeFormat() {
        return sendGenericWarning("Specify a range. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    /**
     * Print invalid date separator format error message.
     *
     * @return Error message
     */
    public String printInvalidTDateSeparatorFormat() {
        return sendGenericWarning("Date range should be separated by '-'");
    }

    /**
     * Print parse exception error message.
     *
     * @return Error message
     */
    public String printParseExceptionMessage(ParseException e) {
        return sendGenericFatal("I could not recognise this date. " + e.getMessage());
    }

    public String getEventLabel() {
        return "[E]";
    }

    public String getDeadlineLabel() {
        return "[D]";
    }

    public String getTodoLabel() {
        return "[T]";
    }

    public String getEventHeader() {
        return "At: ";
    }

    public String getHeader() {
        return "By: ";
    }
}

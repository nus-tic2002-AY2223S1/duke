package duke.impl;

import java.text.ParseException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;

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

    protected String format = "%s";

    public Ui(LocaleRegion l) {
    }

    public Ui() {
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

    protected String buildList(ArrayList<Task> tasks, Boolean withIndex) {
        StringBuilder s = new StringBuilder();
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
        return String.valueOf(s);
    }

    public abstract String sendConfirmedOutput(String message);

    public abstract String sendGenericPlain(String message);

    public abstract String sendGenericInfo(String message);

    public abstract String sendGenericWarning(String message);

    public abstract String sendGenericFatal(String message);

    public abstract String sendGenericConfirmation(String message);

    public abstract String sendProcessActionError(String message);

    public abstract String sendProcessCommandError(String message);

    public abstract String sendCommandUnknownError();

    public abstract String sendProcessFindDateError();

    public abstract String sendProcessFindTaskError();

    public abstract String sendProcessRestoreError();

    public abstract String printNoArchiveFileFoundError();

    public abstract String sendProcessArchiveError();

    public abstract String printProcessArchiveMessage();

    public abstract String printProcessArchiveFailureMessage();

    public abstract String printProcessRestoreMessage();

    public abstract String printProcessRestoreErrorMessage();

    public abstract String printProcessRestoreNoRecordMessage(String e);

    public abstract String printListFilesHeaderMessage();

    public abstract String printListFilesFooterMessage();

    public abstract String printGetIndexErrorMessage(int size);

    public abstract String printGetArchiveIndexErrorMessage(int size);

    public abstract String printIsIntegerErrorMessage();

    public abstract String printUnknownCommandErrorMessage();

    public abstract String[] sendWelcomeMessage(TaskList t, boolean shouldList);

    public abstract String sendHelpMessage();

    public abstract String sendGoodbyeMessage();

    public abstract String printNewTasks(String task, int size);

    public abstract String printTaskRemovedByIndex(String task, int size);

    public abstract String printMarkTask(String task, Boolean isMark);

    public abstract String printList(ArrayList<Task> tasks, Boolean withIndex);

    public abstract String printSelectedList(ArrayList<Task> tasks, Boolean withIndex, String date);

    public abstract String printFoundList(ArrayList<Task> tasks, Boolean withIndex, String keyword);

    public abstract String printInvalidDateFormat();

    public abstract String printInvalidMonthFormat();

    public abstract String printInvalidYearFormat();

    public abstract String printInvalidDateTimeFormat();

    public abstract String printInvalidTimeFormat();

    public abstract String printInconsistentTimeRangeFormat();

    public abstract String printInvalidTimeRangeFormat();

    public abstract String printUnspecifiedTimeRangeFormat();

    public abstract String printTooManyTimeRangesFormat();

    public abstract String printInvalidTDateSeparatorFormat();

    public abstract String printParseExceptionMessage(ParseException e);

    public abstract String printDateStartBeforeEndError();

    public abstract String printDateStartEqualsEndError();

    public abstract String getEventLabel();

    public abstract String getDeadlineLabel();

    public abstract String getTodoLabel();

    public abstract String getEventHeader();

    public abstract String getHeader();
}

package duke.impl;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateProcessor;

/**
 * Main class to generate text responses
 */
public abstract class Ui {
    private LocaleRegion locale;
    private DateProcessor d;

    public abstract String printSelectedList(ArrayList<Task> tasks, Boolean withIndex, String date);

    public abstract String printFoundList(ArrayList<Task> tasks, Boolean withIndex, String keyword);

    /**
     * Enum of locales
     */
    public enum LocaleRegion {
        EN, CN;
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

        private UiIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon() {
            return this.icon;
        }
    }

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

    protected String format = "%s";

    public Ui(LocaleRegion l) {
        this.locale = l;
        d = new DateProcessor();
    }

    public Ui() {
        d = new DateProcessor();
    }


    protected String sendConfirmedOutput(StringBuilder message) {
        return sendConfirmation(Ui_en.UiMessage.GENERIC_FORMATTED.getText(), String.valueOf(message));
    }

    protected String sendGenericPlain(String message) {
        return sendPlain(Ui_en.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericInfo(String message) {
        return sendInfo(Ui_en.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericWarning(String message) {
        return sendWarning(Ui_en.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericFatal(String message) {
        return sendFatal(Ui_en.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendGenericConfirmation(String message) {
        return sendConfirmation(Ui_en.UiMessage.GENERIC_FORMATTED.getText(), message);
    }


    public String sendProcessActionError(String message) {
        return sendFatal(Ui_en.UiMessage.ERROR_PROCESS_ACTION.getText(), message);
    }


    public String sendProcessCommandError(String message) {
        return sendFatal(Ui_en.UiMessage.ERROR_PROCESS_COMMAND.getText(), message);
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
        return sendFatal(UiMessage.ERROR_FIND_DATE.getText(), "");
    }

    public String sendProcessFindTaskError() {
        return sendFatal(UiMessage.ERROR_FIND_TASK.getText(), "");
    }

    public String sendProcessViewScheduleError() {
        return sendFatal(UiMessage.ERROR_VIEW_SCHEDULE.getText(), "");
    }

    public String sendProcessRestoreError() {
        return sendFatal(UiMessage.ERROR_RESTORE.getText(), "");
    }

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
    public String[] sendWelcomeMessage(TaskList t, boolean shouldList) {
        if (t.getLastInfo() != null) {
            if (!shouldList) {
                return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), t.getLastInfo()[0])};
            }
            return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), t.getLastInfo()[0]),
                    printList(t.getList(), true),
                    sendPlain(UiMessage.INFO_LAST_SAVED.getText(),
                            DateProcessor.unixToString(Long.parseLong(t.getLastInfo()[1])))};
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
}

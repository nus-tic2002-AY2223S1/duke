package duke.impl;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateProcessor;

/**
 * Main class to generate text responses
 */
public class Ui {
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

    protected String format = "%s";

    public Ui() {
    }

    private String sendPlain(UiMessage u, String m) {
        return String.format("%s%n", String.format(u.getText(), m));
    }

    private String sendWarning(UiMessage u, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.WARNING.getIcon(), String.format(u.getText(), m)));
    }

    private String sendInfo(UiMessage u, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.INFO.getIcon(), String.format(u.getText(), m)));
    }

    private String sendFatal(UiMessage u, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.FATAL.getIcon(), String.format(u.getText(), m)));
    }

    private String sendConfirmation(UiMessage u, String m) {
        return String.format(this.format,
                String.format("%s %s", UiIcon.CONFIRMATION.getIcon(), String.format(u.getText(), m)));
    }

    public String sendConfirmedOutput(StringBuilder message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED, String.valueOf(message));
    }

    public String sendGenericPlain(String message) {
        return sendPlain(UiMessage.GENERIC_FORMATTED, message);
    }

    public String sendGenericInfo(String message) {
        return sendInfo(UiMessage.GENERIC_FORMATTED, message);
    }

    public String sendGenericWarning(String message) {
        return sendWarning(UiMessage.GENERIC_FORMATTED, message);
    }

    public String sendGenericFatal(String message) {
        return sendFatal(UiMessage.GENERIC_FORMATTED, message);
    }

    public String sendGenericConfirmation(String message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED, message);
    }

    public String sendProcessActionError(String message) {
        return sendFatal(UiMessage.ERROR_PROCESS_ACTION, message);
    }

    public String sendProcessCommandError(String message) {
        return sendFatal(UiMessage.ERROR_PROCESS_COMMAND, message);
    }

    public String sendCommandUnknownError() {
        return sendFatal(UiMessage.ERROR_COMMAND_UNKNOWN, "");
    }

    public String sendProcessFindDateError() {
        return sendFatal(UiMessage.ERROR_FIND_DATE, "");
    }

    public String sendProcessFindTaskError() {
        return sendFatal(UiMessage.ERROR_FIND_TASK, "");
    }

    public String sendProcessViewScheduleError() {
        return sendFatal(UiMessage.ERROR_VIEW_SCHEDULE, "");
    }

    public String sendProcessRestoreError() {
        return sendFatal(UiMessage.ERROR_RESTORE, "");
    }

    public String sendProcessArchiveError() {
        return sendFatal(UiMessage.ERROR_ARCHIVE, "");
    }

    /**
     * Generate welcome message
     * If saved tasks are restored, display all tasks
     * Else only display welcome message
     *
     * @param t TaskList loaded from file
     * @return Welcome message text
     */
    public String[] sendWelcomeMessage(TaskList t) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        if (t.getLastInfo() != null) {
            return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING, t.getLastInfo()[0]),
                    printList(t.getList(), true),
                    sendPlain(UiMessage.INFO_LAST_SAVED,
                            DateProcessor.unixToString(Long.parseLong(t.getLastInfo()[1])))};
        } else {
            return new String[]{sendInfo(UiMessage.INFO_WELCOME, "")};
        }
    }

    public String sendGoodbyeMessage() {
        return sendInfo(UiMessage.INFO_GOODBYE, "");
    }

    /**
     * Display message after adding new tasks
     *
     * @param task Description of task added
     * @param size Current number of tasks
     * @return Message
     */
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
    public String printTaskRemovedByIndex(String task, int size) {
        StringBuilder s = new StringBuilder();
        s.append("Noted. I've removed this task:\n\t")
                .append(task)
                .append("\n\tNow you have ")
                .append(size - 1)
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

    private void buildList(ArrayList<Task> tasks, Boolean withIndex, StringBuilder s) {
        for (int i = 0; i < tasks.size(); i++) {
            String suffix = "";
            if (i != tasks.size() - 1) {
                suffix = "\n";
            }
            if (withIndex) {
                s.append(i + 1);
            }
            s.append(".")
                    .append(tasks.get(i).toString())
                    .append(suffix);
        }
    }

    /**
     * Display all tasks selected by date
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @param date      Selected date
     * @return List of tasks
     */
    public String printSelectedList(ArrayList<Task> tasks, Boolean withIndex, String date) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("You have no task scheduled on ")
                    .append(date);
        } else {
            s.append("Here are the task(s) scheduled on this day:\n    ");
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
    public String printFoundList(ArrayList<Task> tasks, Boolean withIndex, String keyword) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("You have no task with keyword '")
                    .append(keyword)
                    .append("'");
        } else {
            s.append("Here are the task(s) that contains '")
                    .append(keyword)
                    .append("':\n    ");
            buildList(tasks, withIndex, s);
        }
        return sendConfirmedOutput(s);
    }
}

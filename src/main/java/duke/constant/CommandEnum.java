package duke.constant;

import java.util.Objects;

/**
 * Enum class which store information of all possible commands in the program.
 *
 * @author Dex
 * @date 2022/08/31
 */
public enum CommandEnum {

    /**
     * Constant instance of `unknown` command.
     */
    UNKNOWN("unknown", "undefined command", "-"),

    /**
     * Constant instance of `show_command` command.
     */
    SHOW_COMMAND("show_command", "display current supported command in the program", "show_command"),

    /**
     * Constant instance of `list` command.
     */
    LIST("list", "display task list", "list"),

    /**
     * Constant instance of `mark` command.
     */
    MARK_TASK("mark", "mark task as done by given index", "mark <index>"),

    /**
     * Constant instance of `unmark` command.
     */
    UNMARK_TASK("unmark", "mark task as undone by given index", "unmark <index>"),

    /**
     * Constant instance of `delete` command.
     */
    DELETE_TASK("delete", "delete task from list, syntax", "delete <index>"),

    /**
     * Constant instance of `todo` command.
     */
    TODO("todo", "create a todo task", "todo <description>"),

    /**
     * Constant instance of `find` command.
     */
    FIND_TASK("find", "search the task by given keyword", "find <keyword>"),

    /**
     * Constant instance of `deadline` command.
     */
    DEADLINE("deadline", "create a deadline task", "deadline <description> / by <deadlineTime(yyyy-MM-dd HH:mm)>"),

    /**
     * Constant instance of `reschedule` command.
     */
    RESCHEDULE("reschedule", "reschedule the task by given index", "reschedule <index>"),

    /**
     * Constant instance of `event` command.
     */
    EVENT("event", "create a event task", "event <description> / at <startTime(yyyy-MM-dd HH:mm) & endTime(yyyy-MM-dd HH:mm)>"),

    /**
     * Constant instance of `bye` command.
     */
    EXIT("bye", "exit program", "bye");

    /**
     * Command name.
     */
    private final String name;

    /**
     * Command description.
     */
    private final String description;

    /**
     * Syntax how command is entered.
     */
    private final String syntax;

    /**
     * Multi args constructors.
     * @param name: Command name.
     * @param description: Command description.
     * @param syntax: Command syntax.
     */
    CommandEnum(String name, String description, String syntax) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
    }

    /**
     * Returns command name.
     *
     * @return Name of command.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns command description.
     *
     * @return Description of command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns command syntax.
     *
     * @return Syntax of command.
     */
    public String getSyntax() {
        return syntax;
    }

    /**
     * Returns constant instance by given command name.
     *
     * @param name: Name of command.
     * @return Constants instance of command.
     */
    public static CommandEnum getCommandByName(String name) {
        for (CommandEnum value : CommandEnum.values()) {
            if (Objects.equals(value.getName(), name)) {
                return value;
            }
        }
        return UNKNOWN;
    }
}

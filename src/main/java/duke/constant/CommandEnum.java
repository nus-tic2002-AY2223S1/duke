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
     * Unknown command constant instance.
     */
    UNKNOWN("unknown", "undefined command", "-"),

    /**
     * show_command command constant instance.
     */
    SHOW_COMMAND("show_command", "display current supported command in the program", "show_command"),

    /**
     * list command constant instance.
     */
    LIST("list", "display task list", "list"),

    /**
     * mark command constant instance.
     */
    MARK_TASK("mark", "mark task as done by given index", "mark <index>"),

    /**
     * unmark command constant instance.
     */
    UNMARK_TASK("unmark", "mark task as undone by given index", "unmark <index>"),

    /**
     * delete command constant instance.
     */
    DELETE_TASK("delete", "delete task from list, syntax", "delete <index>"),

    /**
     * todo command constant instance.
     */
    TODO("todo", "create a todo task", "todo <description>"),

    /**
     * find command constant instance.
     */
    FIND_TASK("find", "search the task by given keyword", "find <keyword>"),

    /**
     * deadline command constant instance.
     */
    DEADLINE("deadline", "create a deadline task", "deadline <description> / by <deadlineTime(yyyy-MM-dd HH:mm)>"),

    /**
     * reschedule command constant instance.
     */
    RESCHEDULE("reschedule", "reschedule the task by given index", "reschedule <index>"),

    /**
     * event command constant instance.
     */
    EVENT("event", "create a event task", "event <description> / at <startTime(yyyy-MM-dd HH:mm) & endTime(yyyy-MM-dd HH:mm)>"),

    /**
     * bye command constant instance.
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
     * Get command name.
     *
     * @return Name of command.
     */
    public String getName() {
        return name;
    }

    /**
     * Get command description.
     *
     * @return Description of command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get command syntax.
     *
     * @return Syntax of command.
     */
    public String getSyntax() {
        return syntax;
    }

    /**
     * Get constant instance by given command name.
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

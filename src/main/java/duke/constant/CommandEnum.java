package duke.constant;

import java.util.Objects;

/**
 * @description enum class which list all possible commands in the program
 * @author Dex
 * @date 2022/08/31
 */
public enum CommandEnum {

    UNKNOWN("unknown", "undefined command", "-"),
    SHOW_COMMAND("show_command", "display current supported command in the program", "show_command"),
    LIST("list", "display task list", "list"),
    MARK_TASK("mark", "mark task as done by given index", "mark <index>"),
    UNMARK_TASK("unmark", "mark task as undone by given index", "unmark <index>"),
    DELETE_TASK("delete", "delete task from list, syntax", "delete <index>"),
    TODO("todo", "create a todo task", "todo <description>"),
    FIND_TASK("find", "search the task by given keyword", "find <keyword>"),
    DEADLINE("deadline", "create a deadline task", "deadline <description> / by <deadlineTime(yyyy-MM-dd HH:mm)>"),
    RESCHEDULE("reschedule", "reschedule the task by given index", "reschedule <index>"),
    EVENT("event", "create a event task", "event <description> / at <startTime(yyyy-MM-dd HH:mm) & endTime(yyyy-MM-dd HH:mm)>"),
    EXIT("bye", "exit program", "bye");

    private final String name;

    private final String description;

    private final String syntax;

    CommandEnum(String name, String description, String syntax) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSyntax() {
        return syntax;
    }

    public static CommandEnum getCommandByName(String name) {
        for (CommandEnum value : CommandEnum.values()) {
            if (Objects.equals(value.getName(), name)) {
                return value;
            }
        }
        return UNKNOWN;
    }
}

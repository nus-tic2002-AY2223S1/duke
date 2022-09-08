package constant;

import java.util.Objects;

/**
 * @description enum class which list all possible commands in the program
 * @author Dex
 * @date 2022/08/31
 */
public enum CommandEnum {

    UNKNOWN("unknown", "-"),
    SHOW_COMMAND("show_command", "display current supported command in the program, syntax: `show_command`"),
    LIST("list", "display task list, syntax: `list`"),
    MARK_TASK("mark", "mark task as done by given index, syntax: `mark index`"),
    UNMARK_TASK("unmark", "mark task as undone by given index, syntax: `unmark index`"),
    DELETE_TASK("delete", "delete task from list, syntax: `delete index`"),
    TODO("todo", "create a todo task, syntax: `todo description`"),
    FIND_TASK("find", "search the task by given keyword, syntax: `find keyword`"),
    DEADLINE("deadline", "create a deadline task, syntax: `deadline description / by deadlineTime(yyyy-MM-dd HH:mm)`"),
    RESCHEDULE("reschedule", "reschedule the task by given index, syntax: `reschedule index`"),
    EVENT("event", "create a event task, syntax: `event description / at startTime(yyyy-MM-dd HH:mm) & endTime(yyyy-MM-dd HH:mm)`"),
    EXIT("bye", "exit program, syntax: `bye`");

    private final String name;

    private final String description;

    CommandEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

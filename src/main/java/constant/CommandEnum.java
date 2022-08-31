package constant;

import java.util.Objects;

/**
 * @description enum class which list all possible commands in the program
 * @author Dex
 * @date 2022/08/31
 */
public enum CommandEnum {

    UNKNOWN("unknown"), SHOW_COMMAND("show_command"),
    LIST("list"), MARK_TASK("mark"), UNMARK_TASK("unmark"), EXIT("bye");

    private final String name;

    CommandEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

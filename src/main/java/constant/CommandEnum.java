package constant;

import java.util.Objects;

public enum CommandEnum {


    LIST("list"), MARK_TASK("markTask"), UNMARK_TASK("unmarkTask"), EXIT("exit");

    private final String name;

    CommandEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CommandEnum getCommandByName(String name) {
        for (CommandEnum value : CommandEnum.values()) {
            if (Objects.equals(value.getName(), name)) {
                return value;
            }
        }
        return null;
    }
}

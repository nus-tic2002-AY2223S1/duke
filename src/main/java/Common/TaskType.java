package Common;

import Tasks.UnsupportedTaskType;

public enum TaskType {
    DEADLINE, EVENT, TODO;

    public static TaskType getType(String type) throws UnsupportedTaskType {
        if(type.equalsIgnoreCase("deadline")) {
            return DEADLINE;
        } else if (type.equalsIgnoreCase("event")) {
            return EVENT;
        } else if (type.equalsIgnoreCase("todo")) {
            return TODO;
        } else {
            throw new UnsupportedTaskType();
        }
    }

    public static TaskType getTypeByKey(String key) throws UnsupportedTaskType {
        if(key.equalsIgnoreCase("D")) {
            return DEADLINE;
        } else if (key.equalsIgnoreCase("E")) {
            return EVENT;
        } else if (key.equalsIgnoreCase("T")) {
            return TODO;
        } else {
            throw new UnsupportedTaskType();
        }
    }

    public String getKey() {
        switch (this) {
            case DEADLINE:
                return "D";
            case TODO:
                return "T";
            case EVENT:
                return "E";
            default:
                return "";
        }
    }
}
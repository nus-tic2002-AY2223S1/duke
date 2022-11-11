package common;
import exception.UnsupportedTaskType;

/**
 * The enum task type to represent each task that can be used
 */
public enum TaskType {
    DEADLINE, EVENT, TODO;

    /**
     * convert string to TaskType
     * if the string is not supported will throw unsupportedTaskType
     *
     * @return TaskType
     * @param type the string version of the task type
     */
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

    /**
     * convert a key to TaskType
     * if the key is not supported will throw unsupportedTaskType
     *
     * @return TaskType
     * @param key the string key version of the task type
     */
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

    /**
     * convert current TaskType to a string key
     * if the type is not supported will return empty
     *
     * @return String
     */
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
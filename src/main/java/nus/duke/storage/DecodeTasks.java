package nus.duke.storage;

/** Decodes the task loaded from the .txt file. */
public class DecodeTasks {
    /**
     * Parse the tasks from the tasks.txt and split the task into Parser processable format.
     *
     * @return Array of Strings with task type, marking status and task description.
     */
    public static String[] decodeTasks (String task){
    String[] split = task.split("]", 3);
    if (split.length == 3) {
        split[0] = split[0].substring(1);
        split[1] = split[1].substring(1);
        split[2] = split[2].trim();
    }
    return split;
}
}

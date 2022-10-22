package seedu.nusduke.storage;

public class DecodeTasks {

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

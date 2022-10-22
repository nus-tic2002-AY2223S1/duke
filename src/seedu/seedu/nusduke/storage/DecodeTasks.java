package nusduke.storage;

public class DecodeTasks {

    public static String[] decodeTasks (String task){
    String[] split = task.split("]", 3);
    split[0].replace("[", "");
    split[1].replace("[", "");
    split[2].replaceFirst(" ","");
    return split;
}
}

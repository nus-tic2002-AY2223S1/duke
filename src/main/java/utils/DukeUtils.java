package utils;

import entity.Task;

import java.util.List;

public class DukeUtils {
    public static void dukeInit() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println("Hello from bowen's\n" + logo + breakLine);
        System.out.println(tab + breakLine);
        System.out.println(tab + "Hello! I'm Duke\n" + tab + "What can I do for you?");
        System.out.println(tab + breakLine);
    }
}

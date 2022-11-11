package common;

/**
 * The enum task type to represent each supported commands other than the task itself like event
 */
public enum Keyword {
    Bye, List, Mark, Unmark, Delete, Load, Create, Files, Active, Find, None;

    /**
     * Converts string to the keyword
     * if the string is not one of the key, it will return none and add task will handle it
     *
     * @return Keyword the current enum
     * @param key the string version of the keyword
     */
    public static Keyword getKeyword(String key) {
        if(key.toLowerCase().startsWith("bye")) {
            return Bye;
        } else if (key.toLowerCase().startsWith("list")) {
            return List;
        } else if (key.toLowerCase().startsWith("mark")) {
            return Mark;
        } else if (key.toLowerCase().startsWith("unmark")) {
            return Unmark;
        } else if(key.toLowerCase().startsWith("delete")) {
            return Delete;
        } else if(key.toLowerCase().startsWith("load")) {
            return Load;
        } else if(key.toLowerCase().startsWith("create")) {
            return Create;
        } else if(key.toLowerCase().startsWith("files")) {
            return Files;
        } else if(key.toLowerCase().startsWith("active")) {
            return Active;
        } else if(key.toLowerCase().startsWith("find")) {
            return Find;
        } else {
            return None;
        }
    }

    /**
     * Checks if the format for specific key is valid
     * it will return false if it is invalid
     *
     * @return true for valid and false for invalid
     * @param line the whole line that user entered
     */
    public static boolean validateFormat(String line) {
        Keyword key = Keyword.getKeyword(line);
        String[] list = line.split(" ");
        switch (key) {
        case Bye:
        case List:
        case Files:
        case Active:
            return list.length == 1;
        case Mark:
        case Unmark:
        case Delete:
            return list.length == 2 && list[1].matches("^-?\\d+$");
        case Load:
        case Create:
        case Find:
            return list.length == 2;
        case None:
            return true;
        }
        return false;
    }

    /**
     * Returns the text of how the valid format should looks like
     * will only get called for invalid command scenario
     *
     * @return the string of how is the valid format
     * @param key the keyword that user has called
     */
    public static String validFormat(Keyword key) {
        switch (key) {
        case Bye:
        case List:
        case Files:
        case Active:
            return key.toString() + " doesn't required any parameters. e.g: " + key.toString();
        case Mark:
        case Unmark:
        case Delete:
            return key.toString() + " required 1 parameter and it must be a number. e.g: " + key.toString() + " 1";
        case Load:
        case Create:
        case Find:
            return key.toString() + " required 1 parameter and it must be a string. e.g: " + key.toString() + " house";
        case None:
            return "";
        }
        return "";
    }
}

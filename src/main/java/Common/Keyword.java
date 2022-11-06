package Common;

public enum Keyword {
    Bye, List, Mark, Unmark, Delete, Load, Create, Files, Active, None;

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
        } else {
            return None;
        }
    }

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
                return list.length == 2;
            case None:
                return true;
        }
        return false;
    }

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
                return key.toString() + " required 1 parameter and it must be a string. e.g: " + key.toString() + " house";
            case None:
                return "";
        }
        return "";
    }
}

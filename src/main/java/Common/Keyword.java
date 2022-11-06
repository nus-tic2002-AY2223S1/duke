package Common;

public enum Keyword {
    Bye, List, Mark, Unmark, Delete, Load, Create, Files, Active, None;

    public static Keyword getKeyword(String key) {
        if(key.equalsIgnoreCase("bye")) {
            return Bye;
        } else if (key.equalsIgnoreCase("list")) {
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
}

package util;

import java.util.HashSet;

public enum CommandType {
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    ADD("add"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    SORT("sort");
    
    private String cmd;
    private static HashSet<String> cmdSet = new HashSet<>();
    
    CommandType(String cmd) {
        this.cmd = cmd;
    }
    
    @Override
    public String toString() {
        return this.cmd;
    }
    
    /**
     * Checks if inputted text matches one of the CommandType value
     *
     * @param text inputted text
     * @return if inputted text is a CommandType
     */
    public static boolean contains(String text) {
        for (CommandType ct : CommandType.values()) {
            cmdSet.add(ct.toString());
        }
        return cmdSet.contains(text);
    }
    
    
}

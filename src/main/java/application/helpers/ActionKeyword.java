package application.helpers;

import domain.exceptions.DukeArgumentException;

/**
 * Represents the keywords that is entered in the UI to redirect accordingly.
 */
public enum ActionKeyword {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    DELETE("delete"),
    HI("hi"),
    HELLO("hello"),
    BYE("bye"),
    FILTER("filter"),
    SNOOZE("snooze"),
    FIND("find");

    public final String label;
    private ActionKeyword(String label) {
        this.label = label;
    }

    /**
     * Converts string label to an ActionKeyword enum.
     *
     * @param label String.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public static ActionKeyword get(String label) throws DukeArgumentException {
        try {
            return ActionKeyword.valueOf(label.toUpperCase().split(" ")[0]);
        } catch(IllegalArgumentException ex){
            throw new DukeArgumentException(MessageConstants.TASK_ARGUMENT_ERROR);
        }
    }
}

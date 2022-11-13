package duke.entity;

import duke.constant.Constant;

/**
 * Entity class to represent a todo task.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class Todo extends Task {

    /**
     * Multi args constructor.
     *
     * @param description: Command description.
     */
    public Todo(String description) {
        super(description);
        setType(Constant.Task.TYPE_TODO);
    }
}

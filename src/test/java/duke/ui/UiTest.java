package duke.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    @Test
    public void testTrueMark(){
        assertEquals("Task is already marked!\n", Ui.trueMark("mark"));
        assertEquals("Task is already unmarked!\n", Ui.trueMark("unmark"));
        assertEquals("Task is already marked!\n", Ui.trueMark("MArk"));
        assertEquals("Task is already unmarked!\n", Ui.trueMark("Unmark"));
    }

    @Test
    public void testEmptyTask(){
        assertEquals("OOPS!!! The description of a deadline cannot be empty.\n", Ui.emptyTask("deadline"));
        assertEquals("OOPS!!! The description of a deadline cannot be empty.\n", Ui.emptyTask("Deadline"));
        assertEquals("OOPS!!! The description of a event cannot be empty.\n", Ui.emptyTask("event"));
        assertEquals("OOPS!!! The description of a event cannot be empty.\n", Ui.emptyTask("EvenT"));
        assertEquals("OOPS!!! The description of a todo cannot be empty.\n", Ui.emptyTask("todo"));
        assertEquals("OOPS!!! The description of a todo cannot be empty.\n", Ui.emptyTask("TOdo"));
    }
}

package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    @Test
    public void DeleteCommandConstructorTest() {
        DeleteCommand deleteCommand = new DeleteCommand(2);
        assertEquals(2, deleteCommand.deleteIndex);
    }

    @Test
    public void DeleteCommandRemoveLastItemTest() {

        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");

        /* Initial task list */
        TaskList task = new TaskList();
        task.myTaskList.add(new ToDo("borrow books from library"));
        task.myTaskList.add(new ToDo("Go for a run"));
        /* Remove one task */
        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(task, ui, storage);

        assertEquals(1, task.myTaskList.size());
        assertEquals("borrow books from library", task.myTaskList.get(0).getDescription());
    }

    @Test
    public void DeleteCommandRemoveFirstItemTest() {

        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");

        /* Initial task list */
        TaskList task = new TaskList();
        task.myTaskList.add(new ToDo("borrow books from library"));
        task.myTaskList.add(new ToDo("Go for a run"));
        /* Remove one task */
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(task, ui, storage);

        assertEquals(1, task.myTaskList.size());
        assertEquals("Go for a run", task.myTaskList.get(0).getDescription());
    }
}

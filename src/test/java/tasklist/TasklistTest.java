package tasklist;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TasklistTest {

    public class TasklistPrintText{

    }
    Tasklist tasklist = new Tasklist();
    int listCount = 0;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public class PrintTest {
    }

    @BeforeEach
    public void setup(){
        tasklist.addDeadline("read book", "Sat Feb 02, 2019");
        System.setOut(new PrintStream(outContent));
        listCount = 1;
    }

    @DisplayName("Test printTasklist")
    @Test
    void prinTasklistTest() {
        Tasklist.printTasklist();

        assertEquals("Here are the tasks in your list: \r\n" +
                "No 1.[D][ ] read book (by: Sat Feb 02, 2019)\r\n", outContent.toString());
    }
    @DisplayName("Test printTask")
    @Test
    void printTaskTest() {
        Tasklist.printTask(0);

        assertEquals("  [D][ ] read book (by: Sat Feb 02, 2019)\r\n", outContent.toString());
    }
    @DisplayName("Test findTasklist")
    @Test
    void findTasklistTest() {
        Tasklist.findTasklist("book");

        assertEquals("Here are the matching tasks in your list: \r\n" +
                "No 1.[D][ ] read book (by: Sat Feb 02, 2019)\r\n", outContent.toString());
    }
    @DisplayName("Test dueTasklist")
    @Test
    void dueTasklistTest() {
        Tasklist.dueTasklist("Sat Feb 02, 2019");

        assertEquals("Here are the tasks in your list matching the due date: \r\n" +
                "No 1.[D][ ] read book (by: Sat Feb 02, 2019)\r\n", outContent.toString());
    }
    @DisplayName("Test printAdded")
    @Test
    void printAddedTest() {
        Tasklist.printAdded(0);

        assertEquals("Got it. I've added this task: \r\n" +
                "  [D][ ] read book (by: Sat Feb 02, 2019)\r\n" +
                "Now you have 1 tasks in the list\r\n", outContent.toString());
    }
    @DisplayName("Test printDeleted")
    @Test
    void printDeletedTest() {
        Tasklist.printDeleted(0);

        assertEquals("Noted. I've removed this task: \r\n" +
                "  [D][ ] read book (by: Sat Feb 02, 2019)\r\n" +
                "Now you have 0 tasks in the list\r\n", outContent.toString());
    }
    @DisplayName("Test printLoaded")
    @Test
    void printLoadedTest() {
        Tasklist.printLoaded(listCount - 1);

        assertEquals("No 1.[D][ ] read book (by: Sat Feb 02, 2019)\r\n", outContent.toString());
    }
    @DisplayName("Test addTodo")
    @Test
    void addTodoTest() {
        Tasklist.addTodo("read book");

        assertEquals(2, Tasklist.getListcount());
        assertEquals("read book", Tasklist.tasklist.get(1).getDescription());
        assertEquals("T", Tasklist.tasklist.get(1).getTypeTask());
        assertEquals(false, Tasklist.tasklist.get(1).isDone);
    }
    @DisplayName("Test addEvent")
    @Test
    void addEventTest() {
        Tasklist.addEvent("meeting", "Sat Feb 02, 2019 13:00");

        assertEquals(2, Tasklist.getListcount());
        assertEquals("meeting", Tasklist.tasklist.get(1).getDescription());
        assertEquals("E", Tasklist.tasklist.get(1).getTypeTask());
        assertEquals(false, Tasklist.tasklist.get(1).isDone);
        assertEquals("Sat Feb 02, 2019 13:00", Tasklist.tasklist.get(1).getAt());
    }
    @DisplayName("Test addDeadline")
    @Test
    void addDeadlineTest() {
        Tasklist.addDeadline("read book", "Sat Feb 02, 2019");

        assertEquals(2, Tasklist.getListcount());
        assertEquals("read book", Tasklist.tasklist.get(1).getDescription());
        assertEquals("D", Tasklist.tasklist.get(1).getTypeTask());
        assertEquals(false, Tasklist.tasklist.get(1).isDone);
        assertEquals("Sat Feb 02, 2019", Tasklist.tasklist.get(1).getBy());
    }
    @DisplayName("Test removeTask")
    @Test
    void removeTaskTest() {
        Tasklist.removeTask(0);

        assertEquals(0, Tasklist.getListcount());
    }
    @DisplayName("Test markDone")
    @Test
    void markDoneTest() {
        Tasklist.markDone(0);

        assertEquals(true, Tasklist.tasklist.get(0).isDone);
    }
    @DisplayName("Test unmarkDone")
    @Test
    void unmarkDoneTest() {
        Tasklist.markDone(0);
        Tasklist.unmarkDone(0);

        assertEquals(false, Tasklist.tasklist.get(0).isDone);
    }
    @DisplayName("Test postponeTask")
    @Test
    void postponeTaskTest() {
        Tasklist.postponeTask(0, "Sun Feb 03, 2019");

        assertEquals("Sun Feb 03, 2019", Tasklist.tasklist.get(0).getBy());
    }
}
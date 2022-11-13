package Duke;

import Duke.Commands.Command;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.*;
import Duke.UI.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    String filePath = "data/test.txt";
    Storage storage = new Storage(filePath);
    Ui ui = new Ui();
    TaskList taskList = new TaskList(new ArrayList<>());
    Parser p = new Parser();

    public String testCommand(String command){
        try {
            Command c = p.parseCommand(command);
            String output = c.execute(storage, taskList);
            return output;
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
        return "";
    }
    @Test
    public void testEmptyList(){
        String command = "list";
        assertEquals(testCommand(command),"Task list is empty");
    }
    @Test
    public void testTodo(){
        String command = "Todo test";
        assertEquals(testCommand(command),"Got it! I have added this task to your list:\n" +
                "  [T][ ] test \n" +
                "Now you have 1 tasks in the list.");
    }
    @Test
    public void testDeadline(){
        String command = "deadline test /by 2/12/2019 1800";
        assertEquals(testCommand(command),"Got it! I have added this task to your list:\n" +
                "  [D][ ] test  (2 Dec 2019 18:00)\n" +
                "Now you have 1 tasks in the list.");
    }
    @Test
    public void testEvent(){
        String command = "event test /at 5/10/2019 1000";
        assertEquals(testCommand(command),"Got it! I have added this task to your list:\n" +
                "  [E][ ] test  (5 Oct 2019 10:00)\n" +
                "Now you have 1 tasks in the list.");
    }
    @Test
    public void testMark(){
        testCommand("event test /at 5/10/2019 1000");
        String command = "mark 1";
        assertEquals(testCommand(command),"[E][X] test  (5 Oct 2019 10:00)");
        testCommand("event test /at 5/10/2019 1000");
        command = "unmark 1";
        assertEquals(testCommand(command),"[E][ ] test  (5 Oct 2019 10:00)");
    }
}

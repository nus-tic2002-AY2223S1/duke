package engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;

import static engine.Parser.parseFind;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    public void parseFindTest() throws IOException {

        Parser p = Parser.getInstance();
        TaskList tl = TaskList.getInstance();
        TaskList.getTaskList().add(new Todo("Attend seminar in COM2"));
        TaskList.getTaskList().add(new Todo("Have lunch at UTown"));
        TaskList.getTaskList().add(new Todo("Meet group mates in COM2"));
        p.stringToToken("find COM2");
        setUp();
        parseFind();

        String expectedSubString1 = "1.[T][ ] Attend seminar in COM2";
        String expectedSubString2 = "2.[T][ ] Meet group mates in COM2";
        String expectedSubString3 = "There are 2 tasks in the list containing COM2!";

        assertTrue(outputStreamCaptor.toString().contains(expectedSubString1));
        assertTrue(outputStreamCaptor.toString().contains(expectedSubString2));
        assertTrue(outputStreamCaptor.toString().contains(expectedSubString3));

        tearDown();
    }


}

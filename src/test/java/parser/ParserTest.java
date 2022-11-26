package parser;

import exceptions.DukeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.Parser.parse;

import tasklist.Tasklist;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    Command parse;
    Tasklist tasklist = new Tasklist();

    @DisplayName("Test list Command")
    @Test
    void listTest() {
        parse = parse("list");
        assertEquals("list", parse.getCommand());
    }
    @DisplayName("Test bye Command")
    @Test
    void byeTest() {
        parse = parse("bye");
        assertEquals("bye", parse.getCommand());
    }
    @DisplayName("Test find Command")
    @Test
    void findTest() {
        parse = parse("find book");
        assertEquals("find", parse.getCommand());
        assertEquals("book", parse.getDescription());
    }
    @DisplayName("Test due Command")
    @Test
    void dueTest() {
        parse = parse("due 2019-02-02");
        assertEquals("due", parse.getCommand());
        assertEquals("Sat Feb 02, 2019", parse.getDescription());
    }
    @DisplayName("Test todo Command")
    @Test
    void todoTest() {
        parse = parse("todo read book");
        assertEquals("todo", parse.getCommand());
        assertEquals("read book", parse.getDescription());
        assertEquals('T', parse.getType());
    }
    @DisplayName("Test event Command")
    @Test
    void eventTest() {
        parse = parse("event meeting /at 2019-02-02T12:00");
        assertEquals("event", parse.getCommand());
        assertEquals("meeting", parse.getDescription());
        assertEquals("Sat Feb 02, 2019 12:00", parse.getDateTime());
        assertEquals('E', parse.getType());
    }
    @DisplayName("Test event Command")
    @Test
    void deadlineTest() {
        parse = parse("deadline read book /by 2019-02-02");
        assertEquals("deadline", parse.getCommand());
        assertEquals("read book", parse.getDescription());
        assertEquals("Sat Feb 02, 2019", parse.getDateTime());
        assertEquals('D', parse.getType());
    }
    @DisplayName("Test mark Command")
    @Test
    void markTest() {
        tasklist.addTodo("read book");

        parse = parse("mark 1");
        assertEquals("mark", parse.getCommand());
        assertEquals(1, parse.getIndex());
    }
    @DisplayName("Test unmark Command")
    @Test
    void unmarkTest() {
        tasklist.addTodo("read book");
        tasklist.markDone(0);

        parse = parse("unmark 1");
        assertEquals("unmark", parse.getCommand());
        assertEquals(1, parse.getIndex());
    }
    @DisplayName("Test delete Command")
    @Test
    void deleteTest() {
        tasklist.addTodo("read book");

        parse = parse("delete 1");
        assertEquals("delete", parse.getCommand());
        assertEquals(1, parse.getIndex());
    }
    @DisplayName("Test postpone Command")
    @Test
    void postponeTest() {
        tasklist.addDeadline("read book", "Sat Feb 02, 2019");

        parse = parse("postpone 1 /to 2019-02-02");
        assertEquals("postpone", parse.getCommand());
        assertEquals(1, parse.getIndex());
        assertEquals("Sat Feb 02, 2019", parse.getDateTime());
    }
}
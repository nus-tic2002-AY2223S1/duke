package Interface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseListTest() {
        String[] s= {"list"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.PRINT_LIST,null );
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseMarkValidTest() {
        String[] s= {"mark","1"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.MARK_TASK,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseMarkInvalidTest() {
        String[] s= {"mark"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.MARK_TASK,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseUnmarkValidTest() {
        String[] s= {"unmark","1"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.UNMARK_TASK,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseUnmarkInvalidTest() {
        String[] s= {"unmark"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.UNMARK_TASK,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseTodoValidTest() {
        String[] s= {"todo","abc"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_TODO,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseTodoInvalidTest() {
        String[] s= {"todo"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_TODO,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseDeadlineValidTest() {
        String[] s= {"deadline","abc"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_DEADLINE,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }

    @Test
    public void parseTDeadlineInvalidTest() {
        String[] s= {"deadline"};
        Cmd actual = Parser.parse(s);
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_DEADLINE,s);
        assertEquals(expected.t,actual.t);
        assertEquals(expected.input,actual.input);
    }
}
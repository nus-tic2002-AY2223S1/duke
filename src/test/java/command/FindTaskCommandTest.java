package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTaskCommandTest {

    @Test
    public void FindTaskCommandConstructorTest() {
        FindtaskCommand findTaskCommand = new FindtaskCommand("02/04/2023 1230");
        assertEquals("02/04/2023 1230", findTaskCommand.inputDatetime);
    }

    @Test
    public void FindTaskCommandAnotherConstructorTest() {
        FindtaskCommand findTaskCommand = new FindtaskCommand("11/10/2022 1130");
        assertEquals("11/10/2022 1130", findTaskCommand.inputDatetime);
    }

    @Test
    public void FindTaskCommandLastConstructorTest() {
        FindtaskCommand findTaskCommand = new FindtaskCommand("12/10/2023 1530");
        assertEquals("12/10/2023 1530", findTaskCommand.inputDatetime);
    }
}

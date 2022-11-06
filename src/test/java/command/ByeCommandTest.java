package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ByeCommandTest {

    @Test
    public void TrueByeCommandTest() {
        boolean exitValue = new ByeCommand(true).getExitValue();
        assertEquals(true, exitValue);
    }

    @Test
    public void FalseByeCommandTest() {
        boolean exitValue = new ByeCommand(false).getExitValue();
        assertEquals(false, exitValue);
    }

    @Test
    public void AnotherFalseByeCommandTest() {
        boolean exitValue = new ByeCommand(false).getExitValue();
        assertNotEquals(true, exitValue);
    }
}

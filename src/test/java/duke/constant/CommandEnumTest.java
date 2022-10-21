package duke.constant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandEnumTest {

    @Test
    void testGetCommandByName() {
        for (CommandEnum value : CommandEnum.values()) {
            CommandEnum v = CommandEnum.getCommandByName(value.getName());
            Assertions.assertEquals(v, value);
        }
    }
}

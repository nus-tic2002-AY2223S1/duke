package manager;

import org.junit.jupiter.api.Test;
import util.ErrorMessage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yao Liang
 * @created 07/11/2022 - 2:39 pm
 * @projct Duke
 */
class CommandManagerTest {
    
    @Test
    void executeListActionTest() {
        String[] input = new String[]{"list"};
        CommandManager.getInstance().executeUserInput(input);
    }
}
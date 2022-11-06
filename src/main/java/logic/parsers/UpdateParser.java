package logic.parsers;

import common.enums.UpdateTypeEnum;
import model.Task;

public class UpdateParser {
    public UpdateParser() {}

    /**
     * Return parse update command
     *
     * @param   updateType
     * @param   description   description of user input
     * @param   task
     */
    public static void parseUpdate(UpdateTypeEnum updateType, String description, Task task) {
        switch (updateType) {
        case desc:
            task.setDescription(description);
            break;
        case date:
            task.setTime(description);
            break;
        default:
            break;
        }
    }
}

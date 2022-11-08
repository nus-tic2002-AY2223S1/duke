package logic.file;

import model.Task;

import static common.constants.CommonConstant.PIPE;
import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.ZERO_VAL;
import static common.enums.CommandTypeEnum.D;
import static common.enums.CommandTypeEnum.E;
import static common.enums.PeriodicalEnum.undefined;

public class FileEncoder {
    private FileEncoder() {}

    /**
     * Return the encoded content in duke.txt
     *
     * @param   task
     */
    public static String encodedTask(Task task) {
        StringBuilder encodedChatBuilder = new StringBuilder();

        encodedChatBuilder.append(task.getType());
        encodedChatBuilder.append(PIPE);
        encodedChatBuilder.append(task.getDone() ? INIT_INT_VAL : ZERO_VAL);
        encodedChatBuilder.append(PIPE);
        encodedChatBuilder.append(task.getDescription());

        if (task.getType() == D || task.getType() == E){
            encodedChatBuilder.append(PIPE);
            encodedChatBuilder.append(task.getTime());
        }

        if (task.getPeriodical() != undefined) {
            encodedChatBuilder.append(PIPE);
            encodedChatBuilder.append(task.getPeriodical());
        }

        return encodedChatBuilder.toString();
    }
}

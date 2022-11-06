package logic.file;

import model.Task;

import static common.constants.CommonConstant.*;
import static common.enums.TypeEnum.D;
import static common.enums.TypeEnum.E;

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

        if(task.getType() == D || task.getType() == E){
            encodedChatBuilder.append(PIPE);
            encodedChatBuilder.append(task.getTime());
        }

        return encodedChatBuilder.toString();
    }
}

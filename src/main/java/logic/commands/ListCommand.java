package logic.commands;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.MarkedTaskException;
import common.exceptions.NotExistTaskException;
import model.Chat;

import java.util.concurrent.atomic.AtomicInteger;

import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.SPACE;
import static common.constants.CommonConstant.TASK_LIST;
import static common.constants.SymbolConstant.PERIOD;
import static logic.parsers.TaskValidationParser.validateList;

public class ListCommand extends Command {
    public ListCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute prints list of item in taskList array
     *
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @return {void}
     */
    @Override
    public void execute() throws EmptyTaskListException, InvalidTaskDescriptionException {
        validateList(chat);

        System.out.println(TASK_LIST);
        AtomicInteger counter = new AtomicInteger(INIT_INT_VAL);

        chat.getTaskList().forEach(task ->
                System.out.println(counter.getAndIncrement() + PERIOD + SPACE + task)
        );
    }
}

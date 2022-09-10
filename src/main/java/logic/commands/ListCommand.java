package logic.commands;

import model.Chat;

import java.util.concurrent.atomic.AtomicInteger;

import static common.constant.CommonConstant.INIT_INT_VAL;
import static common.constant.CommonConstant.PERIOD;
import static common.constant.CommonConstant.TASK_LIST;
import static common.util.PrintUtil.printLine;

public class ListCommand extends Command {
    public ListCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute prints list of item in taskList array
     *
     * @return {void}
     */
    @Override
    public void execute() {
        printLine();

        System.out.println(TASK_LIST);
        AtomicInteger counter = new AtomicInteger(INIT_INT_VAL);
        chat.getTaskList().forEach(task -> {
            System.out.println(counter.getAndIncrement() + PERIOD + task);
        });

        printLine();
    }
}

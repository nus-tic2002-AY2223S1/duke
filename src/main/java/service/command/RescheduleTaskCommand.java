package service.command;

import constant.Constant;
import entity.Deadline;
import entity.Event;
import entity.Task;
import exception.CommandArgsException;
import form.Form;
import form.RescheduleForm;
import util.DateUtil;
import util.InputUtil;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @description singleton class
 * perform `reschedule task` operation
 * @author Dex
 * @date 2022/09/08
 */
public class RescheduleTaskCommand extends Command {

    private static final RescheduleTaskCommand command = new RescheduleTaskCommand();

    private RescheduleTaskCommand() {}

    public static RescheduleTaskCommand getInstance() {
        return command;
    }

    /**
     * @description reschedule the task by given index and new schedule
     * @author Dex
     * @date 2022/09/08
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        RescheduleForm rescheduleForm = (RescheduleForm) form;
        int taskSize = taskManager.getTaskSize();
        if (taskSize == 0) {
            System.out.println("empty task list! please add in some tasks first");
            return;
        }

        int index = rescheduleForm.getIndex();
        if (index > taskSize) {
            throw new CommandArgsException("given index is invalid, it should be less than current task size");
        }

        // decrement for accessing correct index
        index--;
        Task task = taskManager.getTaskByIndex(index);
        if (!Objects.equals(task.getType(), Constant.Task.TYPE_EVENT)
            && !Objects.equals(task.getType(), Constant.Task.TYPE_DEADLINE)) {
            System.out.println("selected task is not `event` or `deadline` task, cannot be rescheduled");
            return;
        }

        if (Objects.equals(task.getType(), Constant.Task.TYPE_EVENT)) {
            setNewScheduleForEvent(task);
        }

        if (Objects.equals(task.getType(), Constant.Task.TYPE_DEADLINE)) {
            setNewScheduleForDeadline(task);
        }

        // print message
        System.out.println("OK, I've rescheduled task as follows:");
        System.out.println(task);
    }

    private void setNewScheduleForDeadline(Task task) {
        Deadline deadline = (Deadline) task;
        System.out.print("enter the new date (yyyy-MM-dd HH:mm): ");
        String inputString = InputUtil.getInputString();
        LocalDateTime dateTime = DateUtil.parse(inputString, Constant.Time.INPUT_FORMAT);
        deadline.setBy(dateTime);
    }

    private void setNewScheduleForEvent(Task task) {
        Event event = (Event) task;
        System.out.print("enter the new start date (yyyy-MM-dd HH:mm): ");
        String startTimeStr = InputUtil.getInputString();
        LocalDateTime startTime = DateUtil.parse(startTimeStr, Constant.Time.INPUT_FORMAT);

        System.out.print("enter the new end date (yyyy-MM-dd HH:mm): ");
        String endTimeStr = InputUtil.getInputString();
        LocalDateTime endTime = DateUtil.parse(endTimeStr, Constant.Time.INPUT_FORMAT);

        event.setStartTime(startTime);
        event.setEndTime(endTime);
    }
}

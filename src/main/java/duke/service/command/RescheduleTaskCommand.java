package duke.service.command;

import duke.constant.Constant;
import duke.dto.ResponseDto;
import duke.entity.Event;
import duke.entity.Task;
import duke.exception.EmptyTaskListException;
import duke.exception.IndexOutOfBoundsException;
import duke.exception.InvalidRescheduleTaskException;
import duke.form.Form;
import duke.form.RescheduleForm;
import duke.util.InputUtil;
import duke.entity.Deadline;
import duke.pool.AsyncExecutor;
import duke.util.DateUtil;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Singleton class, perform `reschedule task` operation.
 *
 * @author Dex
 * @date 2022/09/08
 */
public class RescheduleTaskCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final RescheduleTaskCommand command = new RescheduleTaskCommand();

    private RescheduleTaskCommand() {}

    /**
     * Get single instance.
     *
     * @return Single instance of command.
     */
    public static RescheduleTaskCommand getInstance() {
        return command;
    }

    /**
     * Reschedule the task by given index and new schedule.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        RescheduleForm rescheduleForm = (RescheduleForm) form;
        int taskSize = taskManager.getTaskSize();
        if (taskSize == 0) {
            throw new EmptyTaskListException();
        }

        int index = rescheduleForm.getIndex();
        if (index > taskSize) {
            throw new IndexOutOfBoundsException();
        }

        // decrement for accessing correct index
        index--;
        Task task = taskManager.getTaskByIndex(index);
        if (!Objects.equals(task.getType(), Constant.Task.TYPE_EVENT)
            && !Objects.equals(task.getType(), Constant.Task.TYPE_DEADLINE)) {
            throw new InvalidRescheduleTaskException();
        }

        if (Objects.equals(task.getType(), Constant.Task.TYPE_EVENT)) {
            setNewScheduleForEvent(task);
        }

        if (Objects.equals(task.getType(), Constant.Task.TYPE_DEADLINE)) {
            setNewScheduleForDeadline(task);
        }

        AsyncExecutor.execute(() -> taskManager.persistTask());

        String message = String.format("%s%n%s", "OK, I've rescheduled task as follows:", task);
        return new ResponseDto<>(form.getCommand(), message);
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

package duke.service;

import duke.constant.CommandEnum;
import duke.form.DeadlineForm;
import duke.form.DeleteForm;
import duke.form.EventForm;
import duke.form.MarkingForm;
import duke.form.RescheduleForm;
import duke.form.TodoForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ParserManagerTest {

    @Test
    void testParseForm() {
        MarkingForm markForm = (MarkingForm) ParserManager.parseForm("mark 1");
        MarkingForm mockMarkForm = new MarkingForm("mark 1", CommandEnum.MARK_TASK.getName(), 1);
        Assertions.assertEquals(mockMarkForm.getMetaData(), markForm.getMetaData());
        Assertions.assertEquals(mockMarkForm.getCommand(), markForm.getCommand());
        Assertions.assertEquals(mockMarkForm.getIndex(), markForm.getIndex());

        MarkingForm unmarkForm = (MarkingForm) ParserManager.parseForm("unmark 1");
        MarkingForm mockUnmarkForm = new MarkingForm("unmark 1", CommandEnum.UNMARK_TASK.getName(), 1);
        Assertions.assertEquals(mockUnmarkForm.getMetaData(), unmarkForm.getMetaData());
        Assertions.assertEquals(mockUnmarkForm.getCommand(), unmarkForm.getCommand());
        Assertions.assertEquals(mockUnmarkForm.getIndex(), unmarkForm.getIndex());

        DeleteForm deleteForm = (DeleteForm) ParserManager.parseForm("delete 1");
        DeleteForm mockDeleteForm = new DeleteForm("delete 1", CommandEnum.DELETE_TASK.getName(), 1);
        Assertions.assertEquals(mockDeleteForm.getMetaData(), deleteForm.getMetaData());
        Assertions.assertEquals(mockDeleteForm.getCommand(), deleteForm.getCommand());
        Assertions.assertEquals(mockDeleteForm.getIndex(), deleteForm.getIndex());

        TodoForm todoForm = (TodoForm) ParserManager.parseForm("todo task");
        TodoForm mockTodoForm = new TodoForm("todo task", CommandEnum.TODO.getName(), "task");
        Assertions.assertEquals(mockTodoForm.getMetaData(), todoForm.getMetaData());
        Assertions.assertEquals(mockTodoForm.getCommand(), todoForm.getCommand());
        Assertions.assertEquals(mockTodoForm.getDescription(), todoForm.getDescription());

        DeadlineForm deadlineForm = (DeadlineForm) ParserManager.parseForm("deadline task / by 2000-01-01 00:00");
        DeadlineForm mockDeadlineForm = new DeadlineForm("deadline task / by 2000-01-01 00:00", CommandEnum.DEADLINE.getName(), "task");
        mockDeadlineForm.setBy(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        Assertions.assertEquals(mockDeadlineForm.getMetaData(), deadlineForm.getMetaData());
        Assertions.assertEquals(mockDeadlineForm.getCommand(), deadlineForm.getCommand());
        Assertions.assertEquals(mockDeadlineForm.getDescription(), deadlineForm.getDescription());
        Assertions.assertEquals(mockDeadlineForm.getBy(), deadlineForm.getBy());

        EventForm eventForm = (EventForm) ParserManager.parseForm("event task / at 2000-01-01 00:00 & 2000-01-01 23:59");
        EventForm mockEventForm = new EventForm("event task / at 2000-01-01 00:00 & 2000-01-01 23:59", CommandEnum.EVENT.getName(), "task");
        mockEventForm.setStartTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        mockEventForm.setEndTime(LocalDateTime.of(2000, 1, 1, 23, 59, 0));
        Assertions.assertEquals(mockEventForm.getMetaData(), eventForm.getMetaData());
        Assertions.assertEquals(mockEventForm.getCommand(), eventForm.getCommand());
        Assertions.assertEquals(mockEventForm.getDescription(), eventForm.getDescription());
        Assertions.assertEquals(mockEventForm.getStartTime(), eventForm.getStartTime());
        Assertions.assertEquals(mockEventForm.getEndTime(), eventForm.getEndTime());

        RescheduleForm rescheduleForm = (RescheduleForm) ParserManager.parseForm("reschedule 1");
        RescheduleForm mockRescheduleForm = new RescheduleForm("reschedule 1", CommandEnum.RESCHEDULE.getName(), 1);
        Assertions.assertEquals(mockRescheduleForm.getMetaData(), rescheduleForm.getMetaData());
        Assertions.assertEquals(mockRescheduleForm.getCommand(), rescheduleForm.getCommand());
        Assertions.assertEquals(mockRescheduleForm.getIndex(), rescheduleForm.getIndex());
    }
}

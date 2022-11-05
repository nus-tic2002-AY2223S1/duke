package impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.impl.Ui;
import duke.impl.UiEn;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateProcessor;

public class UiEnTest {
    private static Ui u = new UiEn();

    @Test
    public void sendConfirmedOutputTest() {
        StringBuilder msg = new StringBuilder();
        msg.append("MsgL1").append("MsgL2");
        String expected = "✔ MsgL1MsgL2";
        String actual = u.sendConfirmedOutput(msg);
        assertEquals(expected, actual);
    }

    @Test
    public void sendGenericPlainTest() {
        String s = "Message";
        String expected = "Message\n";
        String actual = u.sendGenericPlain(s);
        assertEquals(expected, actual);
    }

    @Test
    public void sendGenericInfoTest() {
        String s = "Message";
        String expected = "» Message";
        String actual = u.sendGenericInfo(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendGenericWarningTest() {
        String s = "Message";
        String expected = "! Message";
        String actual = u.sendGenericWarning(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendGenericFatalTest() {
        String s = "Message";
        String expected = "✖ Message";
        String actual = u.sendGenericFatal(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendGenericConfirmationTest() {
        String s = "Message";
        String expected = "✔ Message";
        String actual = u.sendGenericConfirmation(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessActionErrorTest() {
        String s = "Error";
        String expected = "✖ The selection to Error cannot be empty.";
        String actual = u.sendProcessActionError(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessCommandErrorTest() {
        String s = "Error";
        String expected = "✖ The description of Error cannot be empty.";
        String actual = u.sendProcessCommandError(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendCommandUnknownErrorTest() {
        String expected = "✖ I'm sorry, but I don't know what that means :( "
                + "\nSpecify a Todo / Deadline / Event. "
                + "\n\t ➤ Todo <Task Name>";
        String actual = u.sendCommandUnknownError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessFindDateErrorTest() {
        String expected = "✖ The date to search cannot be empty.";
        String actual = u.sendProcessFindDateError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessFindTaskErrorTest() {
        String expected = "✖ The keyword to search cannot be empty.";
        String actual = u.sendProcessFindTaskError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessViewScheduleErrorTest() {
        String expected = "✖ The date to search cannot be empty.";
        String actual = u.sendProcessViewScheduleError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessRestoreErrorTest() {
        String expected = "✖ The file selection to restore cannot be empty.";
        String actual = u.sendProcessRestoreError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessArchiveErrorTest() {
        String expected = "✖ The file selection to archive cannot be empty.";
        String actual = u.sendProcessArchiveError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendWelcomeMessageNewUserTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        String expected = "» Hello from Duke. What can I do for you?";
        String[] actual = u.sendWelcomeMessage(tl, true);
        assertEquals(1, actual.length);
        assertEquals(expected, actual[0]);
    }

    @Test
    public void sendWelcomeMessageReturningUserTest() {
        DateProcessor d = new DateProcessor(Ui.LocaleRegion.EN);
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        long now = DateProcessor.getTimeNow();
        String timeStamp = DateProcessor.getTimeNowString();
        String dateString = d.unixToString(now);
        tl.injectLastInfo(timeStamp);
        tl.injectLastUserName("Name");
        Ui u = new UiEn();
        String expected1 = "» Hello again, Name! Welcome back. What can I do for you?";
        String expected2 = "✔ Here are the task(s) in your list:\n1.[   ] Task1";
        String expected3 = String.format("[Last Modified on %s]\n", dateString);

        String[] actual = u.sendWelcomeMessage(tl, true);
        assertEquals(3, actual.length);
        assertEquals(expected1, actual[0]);
        assertEquals(expected2, actual[1]);
        assertEquals(expected3, actual[2]);
    }

    @Test
    public void sendGoodbyeMessageTest() {
        String expected = "» Bye. Hope to see you again soon!";
        String actual = u.sendGoodbyeMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void printNewTasksTest() {
        String taskName = "Task1";
        String expected = "✔ Got it. I've added this task:\n\t"
                + taskName + "\n\tNow you have 1 task in the list.";
        String actual = u.printNewTasks(taskName, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void printTaskRemovedByIndexTest() {
        String taskName = "Task1";
        String expected = "✔ Noted. I've removed this task:\n\t"
                + taskName + "\n\tNow you have 0 task in the list.";
        String actual = u.printTaskRemovedByIndex(taskName, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void printMarkTaskTest() {
        String taskName = "Task1";
        String expected = "✔ Nice! I've marked this task as done:\n" + taskName;
        String actual = u.printMarkTask(taskName, true);
        assertEquals(expected, actual);
    }

    @Test
    public void printListWithoutTaskTest() {
        TaskList tl = new TaskList();
        String expected = "✔ You have no task.";
        String actual = u.printList(tl.getList(), true);
        assertEquals(expected, actual);
    }

    @Test
    public void printListWithIndexTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        Task t1 = new Task("Task2");
        tl.addTask(t1);
        String expected = "✔ Here are the task(s) in your list:\n1.[   ] Task1\n2.[   ] Task2";
        String actual = u.printList(tl.getList(), true);
        assertEquals(expected, actual);
    }

    @Test
    public void printListNoIndexTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        Task t1 = new Task("Task2");
        tl.addTask(t1);
        String expected = "✔ Here are the task(s) in your list:\n[   ] Task1\n[   ] Task2";
        String actual = u.printList(tl.getList(), false);
        assertEquals(expected, actual);
    }

    @Test
    public void printSelectedListWithTaskWithIndexTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        Task t1 = new Task("Task2");
        tl.addTask(t1);
        String expected = "✔ Here are the task(s) scheduled on this day:\n1.[   ] Task1\n2.[   ] Task2";
        String actual = u.printSelectedList(tl.getList(), true, "1/1/1970");
        assertEquals(expected, actual);
    }

    @Test
    public void printSelectedListWithTaskNoIndexTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        Task t1 = new Task("Task2");
        tl.addTask(t1);
        String expected = "✔ Here are the task(s) scheduled on this day:\n[   ] Task1\n[   ] Task2";
        String actual = u.printSelectedList(tl.getList(), false, "1/1/1970");
        assertEquals(expected, actual);
    }

    @Test
    public void printSelectedListWithoutTaskTest() {
        TaskList tl = new TaskList();
        String expected = "✔ You have no task scheduled on 1/1/1970";
        String actual = u.printSelectedList(tl.getList(), true, "1/1/1970");
        assertEquals(expected, actual);
    }

    @Test
    public void printFoundListWithTaskWithIndexTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        Task t1 = new Task("Task2");
        tl.addTask(t1);
        String expected = "✔ Here are the task(s) that contains 'keyword':\n1.[   ] Task1\n2.[   ] Task2";
        String actual = u.printFoundList(tl.getList(), true, "keyword");
        assertEquals(expected, actual);
    }

    @Test
    public void printFoundListWithTaskNoIndexTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        Task t1 = new Task("Task2");
        tl.addTask(t1);
        String expected = "✔ Here are the task(s) that contains 'keyword':\n[   ] Task1\n[   ] Task2";
        String actual = u.printFoundList(tl.getList(), false, "keyword");
        assertEquals(expected, actual);
    }

    @Test
    public void printFoundListWithoutTaskTest() {
        TaskList tl = new TaskList();
        String expected = "✔ You have no task with keyword 'keyword'";
        String actual = u.printFoundList(tl.getList(), true, "keyword");
        assertEquals(expected, actual);
    }
}

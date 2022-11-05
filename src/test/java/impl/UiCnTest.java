package impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.impl.Ui;
import duke.impl.UiCn;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateProcessor;

public class UiCnTest {
    private static Ui u = new UiCn();

    @Test
    public void sendConfirmedOutputTest() {
        StringBuilder msg = new StringBuilder();
        msg.append("MsgL1").append("MsgL2");
        String expected = "✔ MsgL1MsgL2";
        String actual = u.sendConfirmedOutput(msg);
        assertEquals(expected, actual);
    }

    @Test
    public void sendsendGenericPlainTest() {
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
        String expected = "✖ Error 选择不能为空。";
        String actual = u.sendProcessActionError(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessCommandErrorTest() {
        String s = "Error";
        String expected = "✖ Error 描述不能为空。";
        String actual = u.sendProcessCommandError(s);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendCommandUnknownErrorTest() {
        String expected = "✖ 抱歉， 我不明白你的意思 :( "
                + "\n新增一个 待办Todo / 期限Deadline / 事件Event. "
                + "\n\t ➤ Todo <Task Name>";
        String actual = u.sendCommandUnknownError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessFindDateErrorTest() {
        String expected = "✖ 日期搜索不能为空。\n\t ➤ day <日期>";
        String actual = u.sendProcessFindDateError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessFindTaskErrorTest() {
        String expected = "✖ 关键字搜索不能为空。\n\t ➤ find <关键字>";
        String actual = u.sendProcessFindTaskError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessRestoreErrorTest() {
        String expected = "✖ 恢复文件选择不能为空。\n\t ➤ restore <索引>";
        String actual = u.sendProcessRestoreError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendProcessArchiveErrorTest() {
        String expected = "✖ 归档文件不能为空。";
        String actual = u.sendProcessArchiveError();
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void sendWelcomeMessageNewUserTest() {
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        String expected = "» 你好，我是杜克。需要什么帮助吗？";
        String[] actual = u.sendWelcomeMessage(tl, true);
        assertEquals(1, actual.length);
        assertEquals(expected, actual[0]);
    }

    @Test
    public void sendWelcomeMessageReturningUserTest() {
        DateProcessor d = new DateProcessor(Ui.LocaleRegion.CN);
        TaskList tl = new TaskList();
        Task t = new Task("Task1");
        tl.addTask(t);
        long now = DateProcessor.getTimeNow();
        String timeStamp = DateProcessor.getTimeNowString();
        String dateString = d.unixToString(now);
        tl.injectLastInfo(timeStamp);
        tl.injectLastUserName("Name");
        String expected1 = "» 欢迎回来，Name！需要什么帮助吗？";
        String expected2 = "✔ 您的任务如下：\n1.[   ] Task1";
        String expected3 = String.format("[最后修改于 %s]\n", dateString);

        String[] actual = u.sendWelcomeMessage(tl, true);
        assertEquals(3, actual.length);
        assertEquals(expected1, actual[0]);
        assertEquals(expected2, actual[1]);
        assertEquals(expected3, actual[2]);
    }

    @Test
    public void sendGoodbyeMessageTest() {
        String expected = "» 再见！";
        String actual = u.sendGoodbyeMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void printNewTasksTest() {
        String taskName = "Task1";
        String expected = "✔ 好的. 我已新增以下任务：\n\t"
                + taskName + "\n\t您现在有1个任务。";
        String actual = u.printNewTasks(taskName, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void printTaskRemovedByIndexTest() {
        String taskName = "Task1";
        String expected = "✔ 好的。我已移除以下任务：\n\t"
                + taskName + "\n\t您现在有0个任务。";
        String actual = u.printTaskRemovedByIndex(taskName, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void printMarkTaskTest() {
        String taskName = "Task1";
        String expected = "✔ 好！我已标记此任务为完成：\n" + taskName;
        String actual = u.printMarkTask(taskName, true);
        assertEquals(expected, actual);
    }

    @Test
    public void printListWithoutTaskTest() {
        TaskList tl = new TaskList();
        String expected = "✔ 目前没有任务。";
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
        String expected = "✔ 您的任务如下：\n1.[   ] Task1\n2.[   ] Task2";
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
        String expected = "✔ 您的任务如下：\n[   ] Task1\n[   ] Task2";
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
        String expected = "✔ 您在这一天的任务如下：\n1.[   ] Task1\n2.[   ] Task2";
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
        String expected = "✔ 您在这一天的任务如下：\n[   ] Task1\n[   ] Task2";
        String actual = u.printSelectedList(tl.getList(), false, "1/1/1970");
        assertEquals(expected, actual);
    }

    @Test
    public void printSelectedListWithoutTaskTest() {
        TaskList tl = new TaskList();
        String expected = "✔ 您在1/1/1970这一天没有任何任务。";
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
        String expected = "✔ 包含关键词 'keyword' 的任务如下：\n1.[   ] Task1\n2.[   ] Task2";
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
        String expected = "✔ 包含关键词 'keyword' 的任务如下：\n[   ] Task1\n[   ] Task2";
        String actual = u.printFoundList(tl.getList(), false, "keyword");
        assertEquals(expected, actual);
    }

    @Test
    public void printFoundListWithoutTaskTest() {
        TaskList tl = new TaskList();
        String expected = "✔ 您没有任何包含 'keyword' 的任务。";
        String actual = u.printFoundList(tl.getList(), true, "keyword");
        assertEquals(expected, actual);
    }
}

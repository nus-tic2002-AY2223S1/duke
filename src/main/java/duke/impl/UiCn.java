package duke.impl;

import java.text.ParseException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateProcessor;

/**
 * Main class to generate text responses
 */
public class UiCn extends Ui {
    protected String format = "%s";

    /**
     * Enum of messages
     */
    public enum UiMessage {
        GENERIC(""),
        GENERIC_FORMATTED("%s"),
        INFO_WELCOME("你好，我是杜克。需要什么帮助吗？"),
        INFO_WELCOME_EXISTING("欢迎回来，%s！需要什么帮助吗？"),
        INFO_LAST_SAVED("[最后修改于 %s]"),
        INFO_GOODBYE("再见！"),
        ERROR_COMMAND_UNKNOWN("抱歉， 我不明白你的意思 :( "
                + "\n新增一个 待办Todo / 期限Deadline / 事件Event. "
                + "\n\t \u27a4 Todo <Task Name>"),
        ERROR_PROCESS_ACTION("%s 选择不能为空。"),
        ERROR_PROCESS_COMMAND("%s 描述不能为空。"),
        ERROR_FIND_DATE_INPUT("日期搜索不能为空。\n\t \u27a4 day <日期>"),
        ERROR_FIND_TASK_INPUT("关键字搜索不能为空。\n\t \u27a4 find <关键字>"),
        INFO_ARCHIVE("成功归档。"),
        ERROR_ARCHIVE_SELECTION("归档文件不能为空。"),
        ERROR_ARCHIVE("归档失败。"),
        INFO_RESTORE("成功恢复记录。"),
        ERROR_RESTORE("查找归档记录失败。"),
        ERROR_RESTORE_SELECTION("恢复文件选择不能为空。\n\t \u27a4 restore <索引>"),
        ERROR_RESTORE_NO_RECORD("恢复记录失败。"),
        INFO_LIST_FILES_HEADER("输入归档记录对应的索引。 restore <索引>"),
        INFO_LIST_FILES_FOOTER("聊天将会在恢复记录后刷新。"),
        ERROR_GET_INDEX("索引不存在。 请从%s个任务中选择。"),
        ERROR_GET_ARCHIVE_INDEX("索引不存在。 请从%s个归档中选择。"),
        ERROR_IS_INTEGER("请输入数字。"),
        ERROR_UNKNOWN_COMMAND("未知指令。");

        public final String text;

        UiMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }


    public UiCn(LocaleRegion l) {
    }

    public UiCn() {
    }

    @Override
    public String sendConfirmedOutput(StringBuilder message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED.getText(), String.valueOf(message));
    }

    @Override
    public String sendGenericPlain(String message) {
        return sendPlain(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericInfo(String message) {
        return sendInfo(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericWarning(String message) {
        return sendWarning(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericFatal(String message) {
        return sendFatal(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendGenericConfirmation(String message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED.getText(), message);
    }

    @Override
    public String sendProcessActionError(String message) {
        return sendFatal(UiMessage.ERROR_PROCESS_ACTION.getText(), message);
    }

    @Override
    public String sendProcessCommandError(String message) {
        return sendFatal(UiMessage.ERROR_PROCESS_COMMAND.getText(), message);
    }

    @Override
    public String sendCommandUnknownError() {
        return sendFatal(UiMessage.ERROR_COMMAND_UNKNOWN.getText(), "");
    }

    @Override
    public String sendProcessFindDateError() {
        return sendFatal(UiMessage.ERROR_FIND_DATE_INPUT.getText(), "");
    }

    @Override
    public String sendProcessFindTaskError() {
        return sendFatal(UiMessage.ERROR_FIND_TASK_INPUT.getText(), "");
    }

    @Override
    public String sendProcessRestoreError() {
        return sendFatal(UiMessage.ERROR_RESTORE_SELECTION.getText(), "");
    }

    @Override
    public String sendProcessArchiveError() {
        return sendFatal(UiMessage.ERROR_ARCHIVE_SELECTION.getText(), "");
    }

    @Override
    public String printProcessArchiveMessage() {
        return sendGenericConfirmation(UiMessage.INFO_ARCHIVE.getText());
    }

    @Override
    public String printProcessArchiveFailureMessage() {
        return sendGenericFatal(UiMessage.ERROR_ARCHIVE.getText());
    }

    @Override
    public String printProcessRestoreMessage() {
        return sendGenericConfirmation(UiMessage.INFO_RESTORE.getText());
    }

    @Override
    public String printProcessRestoreErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_RESTORE.getText());
    }

    @Override
    public String printProcessRestoreNoRecordMessage() {
        return sendGenericWarning(UiMessage.ERROR_RESTORE_NO_RECORD.getText());
    }

    @Override
    public String printListFilesHeaderMessage() {
        return sendGenericInfo(UiMessage.INFO_LIST_FILES_HEADER.getText());
    }

    @Override
    public String printListFilesFooterMessage() {
        return sendGenericInfo(UiMessage.INFO_LIST_FILES_FOOTER.getText());
    }

    @Override
    public String printGetIndexErrorMessage(int size) {
        return sendGenericWarning(String.format(UiMessage.ERROR_GET_INDEX.getText(), size));
    }

    @Override
    public String printGetArchiveIndexErrorMessage(int size) {
        return sendGenericWarning(String.format(UiMessage.ERROR_GET_ARCHIVE_INDEX.getText(), size));

    }

    @Override
    public String printIsIntegerErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_IS_INTEGER.getText());
    }

    @Override
    public String printUnknownCommandErrorMessage() {
        return sendGenericWarning(UiMessage.ERROR_UNKNOWN_COMMAND.getText());
    }

    /**
     * Generate welcome message
     * If saved tasks are restored, display all tasks
     * Else only display welcome message
     *
     * @param t TaskList loaded from file
     * @return Welcome message text
     */
    @Override
    public String[] sendWelcomeMessage(TaskList t, boolean shouldList) {
        if (t.getLastInfo() != null) {
            if (!shouldList) {
                return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), t.getLastInfo()[0])};
            }
            return new String[]{sendInfo(UiMessage.INFO_WELCOME_EXISTING.getText(), t.getLastInfo()[0]),
                    printList(t.getList(), true),
                    sendPlain(UiMessage.INFO_LAST_SAVED.getText(),
                            DateProcessor.unixToStringCn(Long.parseLong(t.getLastInfo()[1])))};
        } else {
            return new String[]{sendInfo(UiMessage.INFO_WELCOME.getText(), "")};
        }
    }

    public String sendGoodbyeMessage() {
        return sendInfo(UiMessage.INFO_GOODBYE.getText(), "");
    }

    /**
     * Display message after adding new tasks
     *
     * @param task Description of task added
     * @param size Current number of tasks
     * @return Message
     */
    @Override
    public String printNewTasks(String task, int size) {
        StringBuilder s = new StringBuilder();
        s.append("好的. 我已新增以下任务：\n\t")
                .append(task)
                .append("\n\t您现在有")
                .append(size)
                .append("个任务。");
        return sendConfirmedOutput(s);
    }

    /**
     * Display message after removing task by index
     *
     * @param task Description of task removed
     * @param size Current number of tasks
     * @return Message
     */
    @Override
    public String printTaskRemovedByIndex(String task, int size) {
        StringBuilder s = new StringBuilder();
        s.append("好的。我已移除以下任务：\n\t")
                .append(task)
                .append("\n\t您现在有")
                .append(size)
                .append("个任务。");
        return sendConfirmedOutput(s);
    }

    /**
     * Display message after marking task as completed
     *
     * @param task   Description of task removed
     * @param isMark Mark state of task
     * @return Message
     */
    @Override
    public String printMarkTask(String task, Boolean isMark) {
        StringBuilder s = new StringBuilder();
        s.append(isMark ? "好！我已标记此任务为完成：\n" : "好，我已标记此任务为未完成：\n")
                .append(task);
        return sendConfirmedOutput(s);
    }

    /**
     * Display all tasks
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @return List of tasks
     */
    @Override
    public String printList(ArrayList<Task> tasks, Boolean withIndex) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("目前没有任务。");
        } else {
            s.append("您的任务如下：\n");
            buildList(tasks, withIndex, s);
        }
        return sendConfirmedOutput(s);
    }

    /**
     * Display all tasks selected by date
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @param date      Selected date
     * @return List of tasks
     */
    @Override
    public String printSelectedList(ArrayList<Task> tasks, Boolean withIndex, String date) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("您在").append(date).append("这一天没有任何任务。");
        } else {
            s.append("您在这一天的任务如下：\n");
            buildList(tasks, withIndex, s);
        }
        return sendConfirmedOutput(s);
    }

    /**
     * Display all tasks selected by keyword
     *
     * @param tasks     An array of available tasks
     * @param withIndex Whether to print with index
     * @param keyword   Selected keyword
     * @return List of tasks
     */
    @Override
    public String printFoundList(ArrayList<Task> tasks, Boolean withIndex, String keyword) {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            s.append("您没有任何包含 '")
                    .append(keyword).append("' 的任务。");
        } else {
            s.append("包含关键词 '")
                    .append(keyword).append("' 的任务如下：\n");
            buildList(tasks, withIndex, s);
        }
        return sendConfirmedOutput(s);
    }


    @Override
    public String printInvalidDateFormat() {
        return sendGenericWarning("日期格式无效。 日期格式必须是 dd/mm/yyyy。");
    }

    @Override
    public String printInvalidMonthFormat() {
        return sendGenericWarning("月份无效。 月份必须是 01 ~ 12。");
    }

    @Override
    public String printInvalidYearFormat() {
        return sendGenericWarning("年份无效。 年份必须是 yyyy。");
    }

    @Override
    public String printInvalidDateTimeFormat() {
        return sendGenericWarning("日期/时间格式无效。 日期时间格式必须是 dd/mm/yyyy HHmm。");
    }

    @Override
    public String printInvalidTimeFormat() {
        return sendGenericWarning("时间格式无效。 时间格式必须是 0000 ~ 2359。");
    }

    @Override
    public String printInconsistentTimeRangeFormat() {
        return sendGenericWarning("区间格式无效。 开始和结束格式必须一致。 "
                + "区间格式必须是 \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    @Override
    public String printInvalidTimeRangeFormat() {
        return sendGenericWarning("格式无效。 "
                + "区间格式必须是 \n\tdd/mm/yyyy  "
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n"
                + "\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    @Override
    public String printUnspecifiedTimeRangeFormat() {
        return sendGenericWarning("请指定区间。 "
                + "区间格式必须是 \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    @Override
    public String printInvalidTDateSeparatorFormat() {
        return sendGenericWarning("日期区间必须使用 '-' 区隔");
    }

    @Override
    public String printParseExceptionMessage(ParseException e) {
        return sendGenericFatal("我不理解这个日期。 " + e.getMessage());
    }

    @Override
    public String getEventLabel() {
        return "「事件」";
    }

    @Override
    public String getDeadlineLabel() {
        return "「期限」";
    }


    @Override
    public String getTodoLabel() {
        return "「待办」";
    }

    @Override
    public String getEventHeader() {
        return "日期：";
    }

    @Override
    public String getHeader() {
        return "截止：";
    }

}

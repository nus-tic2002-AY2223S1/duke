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
        INFO_HELP("我能明白以下指令：\n\n"
                + "list: 列出全部任务\n"
                + "todo <描述>: 新增一个待办事项\n"
                + "event <描述>: 新增一个事件\n"
                + "deadline <描述>: 新增一个期限\n"
                + "mark <索引>: 标记任务完成\n"
                + "unmark <索引>: 标记任务未完成\n"
                + "delete <索引>: 删除任务\n"
                + "day <dd/mm/yyyy>: 查找所有在这一天的任务\n"
                + "find <关键字>: 查找所有包含此关键字的任务\n"
                + "archive: 归档和重置聊天\n"
                + "restore: 查看可恢复的归档记录\n"
                + "restore <索引>: 恢复此归档记录\n"),
        ERROR_COMMAND_UNKNOWN("抱歉， 我不明白你的意思 :( "
                + "\n新增一个 待办Todo / 期限Deadline / 事件Event. "
                + "\n\t \u27a4 Todo <Task Name>"),
        ERROR_PROCESS_ACTION("%s 选择不能为空。"),
        ERROR_PROCESS_COMMAND("%s 描述不能为空。"),
        ERROR_FIND_DATE_INPUT("日期搜索不能为空。\n\t \u27a4 day <日期>"),
        ERROR_FIND_TASK_INPUT("关键字搜索不能为空。\n\t \u27a4 find <关键字>"),
        INFO_ARCHIVE("成功归档。"),
        ERROR_ARCHIVE("归档失败。"),
        ERROR_ARCHIVE_NO_FILE_FOUND("不存在任何归档记录。"),
        ERROR_ARCHIVE_SELECTION("归档文件不能为空。"),
        INFO_RESTORE("成功恢复记录。"),
        ERROR_RESTORE("查找归档记录失败。"),
        ERROR_RESTORE_SELECTION("恢复文件选择不能为空。\n\t \u27a4 restore <索引>"),
        ERROR_RESTORE_NO_RECORD("恢复记录失败。"),
        INFO_LIST_FILES_HEADER("输入归档记录对应的索引。 restore <索引>\n"),
        INFO_LIST_FILES_FOOTER("聊天将会在恢复记录后刷新。"),
        ERROR_GET_INDEX("索引不存在。 请从%s个任务中选择。"),
        ERROR_GET_ARCHIVE_INDEX("索引不存在。 请从%s个归档中选择。"),
        ERROR_IS_INTEGER("请输入数字。"),
        ERROR_UNKNOWN_COMMAND("未知指令。"),
        INFO_ADD_TASK("好的. 我已新增以下任务：\n\t%s\n\t您现在有%s个任务。"),
        INFO_REMOVE_TASK("好的。我已移除以下任务：\n\t%s\n\t您现在有%s个任务。"),
        INFO_MARK_TASK("好！我已标记此任务为完成：\n%s"),
        INFO_UNMARK_TASK("好，我已标记此任务为未完成：\n%s"),
        INFO_PRINT_TASK("您的任务如下：\n%s"),
        INFO_PRINT_NO_TASK("目前没有任务。"),
        INFO_PRINT_DAY_TASK("您在这一天的任务如下：\n%s"),
        INFO_PRINT_NO_DAY_TASK("您在%s这一天没有任何任务。"),
        INFO_PRINT_FIND_TASK("包含关键词 '%s' 的任务如下：\n%s"),
        INFO_PRINT_NO_FIND_TASK("您没有任何包含 '%s' 的任务。"),
        ERROR_INVALID_DATE_FORMAT("日期格式无效。日期必须是 dd/mm/yyyy。"),
        ERROR_INVALID_MONTH_FORMAT("月份格式无效。 月份必须介于 1 ~ 12。"),
        ERROR_INVALID_YEAR_FORMAT("年份格式无效。年份必须是 yyyy。"),
        ERROR_INVALID_DATE_TIME_FORMAT("日期/时间格式无效。 日期时间格式必须是 dd/mm/yyyy HHmm。"),
        ERROR_INVALID_TIME_FORMAT("时间格式无效。 时间格式必须是 0000 ~ 2359。"),
        ERROR_INCONSISTENT_TIME_RANGE_FORMAT("区间格式无效。 开始和结束格式必须一致。"
                + "区间格式必须是\n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm。"),
        ERROR_INVALID_TIME_RANGE_FORMAT("格式无效。"
                + "区间格式必须是\n\tdd/mm/yyyy  "
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n"
                + "\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm。"),
        ERROR_UNSPECIFIED_TIME_RANGE_FORMAT("请指定区间。"
                + "区间格式必须是\n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm。"),
        ERROR_INVALID_DATE_SEPARATOR_FORMAT("日期区间必须使用 '-' 区隔"),
        ERROR_PARSE_EXCEPTION("我不理解这个日期。 %s"),
        ERROR_DATE_END_BEFORE_START_ERROR("结束早于开始。穿越时空不被允许。"),
        ERROR_DATE_START_EQUALS_END_ERROR("开始和结束时间相同。"),
        ERROR_TOO_MANY_TIME_RANGES_ERROR("只接受指定一个区间。");

        public final String text;

        UiMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    /**
     * Enum of task labels.
     */
    public enum UiLabel {
        LABEL_DEADLINE("「期限」"),
        LABEL_EVENT("「事件」"),
        LABEL_TODO("「待办」"),
        LABEL_EVENT_HEADER("日期："),
        LABEL_HEADER("截止：");

        public final String text;

        UiLabel(String text) {
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
    public String sendConfirmedOutput(String message) {
        return sendConfirmation(UiMessage.GENERIC_FORMATTED.getText(), message);
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
    public String printNoArchiveFileFoundError() {
        return sendFatal(UiMessage.ERROR_ARCHIVE_NO_FILE_FOUND.getText(), "");
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

    @Override
    public String sendHelpMessage() {
        return sendInfo(UiMessage.INFO_HELP.getText(), "");
    }

    @Override
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
        return sendConfirmedOutput(String.format(UiMessage.INFO_ADD_TASK.getText(), task, size));
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
        return sendConfirmedOutput(String.format(UiMessage.INFO_REMOVE_TASK.getText(), task, size));
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
        return (isMark
                ? sendConfirmedOutput(String.format(UiMessage.INFO_MARK_TASK.getText(), task))
                : sendConfirmedOutput(String.format(UiMessage.INFO_UNMARK_TASK.getText(), task)));
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
        if (tasks.size() == 0) {
            return sendConfirmedOutput(UiMessage.INFO_PRINT_NO_TASK.getText());
        }
        return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_TASK.getText(), buildList(tasks, withIndex)));
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
        if (tasks.size() == 0) {
            return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_NO_DAY_TASK.getText(), date));
        }
        return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_DAY_TASK.getText(), buildList(tasks, withIndex)));
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
        if (tasks.size() == 0) {
            return sendConfirmedOutput(String.format(UiMessage.INFO_PRINT_NO_FIND_TASK.getText(), keyword));
        }
        return sendConfirmedOutput(
                String.format(
                        UiMessage.INFO_PRINT_FIND_TASK.getText(), keyword, buildList(tasks, withIndex)));
    }

    @Override
    public String printInvalidDateFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_DATE_FORMAT.getText());
    }

    @Override
    public String printInvalidMonthFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_MONTH_FORMAT.getText());
    }

    @Override
    public String printInvalidYearFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_YEAR_FORMAT.getText());
    }

    @Override
    public String printInvalidDateTimeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_DATE_TIME_FORMAT.getText());
    }

    @Override
    public String printInvalidTimeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_TIME_FORMAT.getText());
    }

    @Override
    public String printInconsistentTimeRangeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INCONSISTENT_TIME_RANGE_FORMAT.getText());
    }

    @Override
    public String printInvalidTimeRangeFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_TIME_RANGE_FORMAT.getText());
    }

    @Override
    public String printUnspecifiedTimeRangeFormat() {
        return sendGenericWarning(UiMessage.ERROR_UNSPECIFIED_TIME_RANGE_FORMAT.getText());
    }

    @Override
    public String printTooManyTimeRangesFormat() {
        return sendGenericWarning(UiMessage.ERROR_TOO_MANY_TIME_RANGES_ERROR.getText());
    }

    @Override
    public String printInvalidTDateSeparatorFormat() {
        return sendGenericWarning(UiMessage.ERROR_INVALID_DATE_SEPARATOR_FORMAT.getText());
    }

    @Override
    public String printParseExceptionMessage(ParseException e) {
        return sendGenericFatal(String.format(UiMessage.ERROR_PARSE_EXCEPTION.getText(), e.getMessage()));
    }

    @Override
    public String printDateStartBeforeEndError() {
        return sendGenericWarning(UiMessage.ERROR_DATE_END_BEFORE_START_ERROR.getText());
    }

    @Override
    public String printDateStartEqualsEndError() {
        return sendGenericWarning(UiMessage.ERROR_DATE_START_EQUALS_END_ERROR.getText());
    }

    @Override
    public String getEventLabel() {
        return UiLabel.LABEL_EVENT.getText();
    }

    @Override
    public String getDeadlineLabel() {
        return UiLabel.LABEL_DEADLINE.getText();
    }

    @Override
    public String getTodoLabel() {
        return UiLabel.LABEL_TODO.getText();
    }

    @Override
    public String getEventHeader() {
        return UiLabel.LABEL_EVENT_HEADER.getText();
    }

    @Override
    public String getHeader() {
        return UiLabel.LABEL_HEADER.getText();
    }
}

package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Deadline;
import duke.entity.Event;
import duke.entity.Task;
import duke.entity.Todo;
import duke.exception.DukeException;
import duke.form.DeadlineForm;
import duke.form.DeleteForm;
import duke.form.EventForm;
import duke.form.Form;
import duke.form.MarkingForm;
import duke.form.TodoForm;
import duke.service.CommandManager;
import duke.service.TaskManager;
import duke.util.FileUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;

class CommandTestBase {

    protected final TaskManager taskManager = TaskManager.getInstance();

    protected static final String TEST_META_DATA_DESCRIPTION = "meta data";

    protected static final String TEST_TASK_DESCRIPTION = "test task description";

    protected static final String TEST_DATA_SET_PATH = "data/test-set.txt";

    protected static final LocalDateTime TEST_DEADLINE_BY_TIME = LocalDateTime.of(2030, 1, 1, 0, 0, 0);

    protected static final LocalDateTime TEST_EVENT_START_TIME = LocalDateTime.of(2022, 1, 1, 0, 0, 0);

    protected static final LocalDateTime TEST_EVENT_END_TIME = LocalDateTime.of(2022, 12, 31, 23, 59, 59);

    @BeforeAll
    static void setUp() {
        // init test data file location
        System.out.println("running setup ...");
        TaskManager.init(TEST_DATA_SET_PATH);
    }

    @AfterAll
    static void cleanUp() {
        // remove test data file
        System.out.println("running clean up ...");
        FileUtil.deleteFile(new File(TEST_DATA_SET_PATH));
    }

    /**
     * @description get newly added task from list
     * @author Dex
     * @date 2022/09/23
     * @param clazz: class type inherit from Task
     */
    @SuppressWarnings("unchecked")
    protected <T extends Task> T getNewlyAddedTask(Class<T> clazz) {
        return (T) taskManager.getTaskByIndex(getLastIndex());
    }

    protected int getLastIndex() {
        if (taskManager.getTaskSize() < 1) {
            throw new DukeException("current task list is empty, no last index found");
        }
        return taskManager.getTaskSize() - 1;
    }

    protected int getUserInputLastIndex() {
        return getLastIndex() + 1;
    }
}

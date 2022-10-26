package duke.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import duke.constant.Constant;
import duke.entity.Deadline;
import duke.entity.Event;
import duke.entity.Task;
import duke.entity.Todo;
import duke.util.DateUtil;
import duke.util.FileUtil;
import duke.util.StringUtil;
import duke.util.ExceptionUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class which used to manage the operation of task related entity.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class TaskManager {

    /**
     * Store task data.
     */
    private static final List<Task> taskList = new ArrayList<>();

    /**
     * Single instance.
     */
    private static final TaskManager taskManager = new TaskManager();

    /**
     * Default location to persist task data.
     */
    private static final String DEFAULT_DATA_PATH = "data/data.txt";

    /**
     * Check if the initialization is completed.
     */
    private static boolean initComplete = false;

    /**
     * Final location to persist task data.
     */
    private static String dataFilePath;

    public static TaskManager getInstance() {
        return taskManager;
    }

    private TaskManager() {}

    /**
     * Init task list from hard disk data.
     *
     * @param dataFilePath: Input persistent data location from user.
     */
    public static void init(String dataFilePath) {
        if (initComplete) {
            System.out.println("init of task manager is completed");
            return;
        }

        // sanity check
        if (dataFilePath == null) {
            System.out.printf("input data file path not found, use default data path: [%s]%n", DEFAULT_DATA_PATH);
            dataFilePath = DEFAULT_DATA_PATH;
        }

        File file = new File(dataFilePath);

        // set value of data path in global score
        TaskManager.dataFilePath = dataFilePath;

        // check source file exists, create if it does not exist
        if (!file.exists()) {
            createFile(dataFilePath);
            return;
        }

        // if source file exists, load task instance from source file
        String content = FileUtil.readFileContent(file);
        if (StringUtil.isBlank(content)) {
            return;
        }

        // parse to task instance, skip corrupted data
        List<String> list = StringUtil.stringToList(content, "\n");
        list.stream().map(TaskManager::parseToJsonObj)
                .filter(Objects::nonNull)
                .map(TaskManager::parseToTask)
                .filter(Objects::nonNull)
                .forEach(taskList::add);

        initComplete = true;
    }

    private static JSONObject parseToJsonObj(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Exception exception) {
            ExceptionUtil.getStackTraceAsString(exception);
            return null;
        }
    }

    private static Task parseToTask(JSONObject jsonObject) {
        try {
            String type = StringUtil.trim(jsonObject.getString("type"));
            switch (type) {
                case "E":
                    return parseEventTask(jsonObject);
                case "T":
                    return parseTodoTask(jsonObject);
                case "D":
                    return parseDeadlineTask(jsonObject);
                default:
                    return parseTask(jsonObject);
            }
        } catch (Exception exception) {
            ExceptionUtil.getStackTraceAsString(exception);
            return null;
        }
    }

    private static Task parseTask(JSONObject jsonObject) {
        // create task instance
        Task task = new Task(jsonObject.getString(Constant.Task.DESCRIPTION_FIELD));
        task.setDone(jsonObject.getBoolean(Constant.Task.DONE_FIELD));
        return task;
    }

    private static Task parseTodoTask(JSONObject jsonObject) {
        // create todo instance
        Todo todo = new Todo(jsonObject.getString(Constant.Task.DESCRIPTION_FIELD));
        todo.setDone(jsonObject.getBoolean(Constant.Task.DONE_FIELD));
        return todo;
    }

    private static Task parseDeadlineTask(JSONObject jsonObject) {
        // convert string to localtime
        String by = StringUtil.trim(jsonObject.getString("by"));
        LocalDateTime time = DateUtil.parse(by, Constant.Time.INPUT_FORMAT);

        // create deadline instance
        Deadline deadline = new Deadline(jsonObject.getString(Constant.Task.DESCRIPTION_FIELD));
        deadline.setBy(time);
        deadline.setDone(jsonObject.getBoolean(Constant.Task.DONE_FIELD));
        return deadline;
    }

    private static Task parseEventTask(JSONObject jsonObject) {
        // convert string to localtime
        String startTimeStr = StringUtil.trim(jsonObject.getString("startTime"));
        String endTimeStr = StringUtil.trim(jsonObject.getString("endTime"));
        LocalDateTime startTime = DateUtil.parse(startTimeStr, Constant.Time.INPUT_FORMAT);
        LocalDateTime endTime = DateUtil.parse(endTimeStr, Constant.Time.INPUT_FORMAT);

        // create event instance
        Event event = new Event(jsonObject.getString(Constant.Task.DESCRIPTION_FIELD));
        event.setDone(jsonObject.getBoolean(Constant.Task.DONE_FIELD));
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        return event;
    }

    private static void createFile(String path) {
        Path file = Paths.get(path);
        try {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write task list data into hard disk method is called in daemon thread,
     * resource modification can happen concurrently.
     */
    public synchronized void persistTask() {
        String content = taskList.stream().map(o -> JSON.toJSONStringWithDateFormat(o, Constant.Time.INPUT_FORMAT)).collect(Collectors.joining("\n"));
        if (StringUtil.isBlank(content)) {
            return;
        }
        FileUtil.writeStringToFile(new File(dataFilePath), content);
    }

    /**
     * Linear search for the description of task, check if it contains the given keyword.
     *
     * @param keyword: Keyword in the description of task.
     */
    public List<Task> findTask(String keyword) {
        return taskList.stream().filter(o -> o.getDescription().contains(keyword)).collect(Collectors.toList());
    }

    /**
     * Append new task to task list.
     *
     * @param task: Task entity.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Get task instance by given index.
     *
     * @param index: Index of task in the list.
     */
    public Task getTaskByIndex(int index) {
        return taskList.get(index);
    }

    /**
     * Remove task from list by given index.
     *
     * @param index: Index of task in the list.
     */
    public void removeTask(int index) {
        // may have performance issue
        taskList.remove(index);
    }

    /**
     * Get task size.
     */
    public int getTaskSize() {
        return taskList.size();
    }

    /**
     * Get task list instance. Make a copy of it is needed to prevent unintentional change of task entity.
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}

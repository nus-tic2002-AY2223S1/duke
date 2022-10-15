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

public class TaskManager {

    /**
     * store task data
     */
    private static final List<Task> taskList = new ArrayList<>();

    private static final TaskManager taskManager = new TaskManager();

    private static final String DEFAULT_DATA_PATH = "data/data.txt";

    private static boolean initComplete = false;

    private static String dataFilePath;

    public static TaskManager getInstance() {
        return taskManager;
    }

    private TaskManager() {}

    /**
     * @description init task list from hard disk data
     * @author Dex
     * @date 2022/08/29
     * @param dataFilePath: dest data file for storing task data
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
     * @description write task list data into hard disk
     * method is called in daemon thread, resource modification can happen concurrently
     * @author Dex
     * @date 2022/08/29
     */
    public synchronized void persistTask() {
        String content = taskList.stream().map(o -> JSON.toJSONStringWithDateFormat(o, Constant.Time.INPUT_FORMAT)).collect(Collectors.joining("\n"));
        if (StringUtil.isBlank(content)) {
            return;
        }
        FileUtil.writeStringToFile(new File(dataFilePath), content);
    }

    /**
     * @description linear search for the description of task, check if it contains the given keyword
     * @author Dex
     * @date 2022/09/07
     * @param keyword: keyword in the description of task
     */
    public List<Task> findTask(String keyword) {
        return taskList.stream().filter(o -> o.getDescription().contains(keyword)).collect(Collectors.toList());
    }

    /**
     * @description append new task to task list
     * @author Dex
     * @date 2022/08/29
     * @param task: task entity
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * @description get task instance by given index
     * @author Dex
     * @date 2022/08/31
     * @param index: index of task in the list
     */
    public Task getTaskByIndex(int index) {
        return taskList.get(index);
    }

    /**
     * @description remove task from list by given index
     * @author Dex
     * @date 2022/08/29
     * @param index: index of task in the list
     */
    public void removeTask(int index) {
        // may have performance issue
        taskList.remove(index);
    }

    /**
     * @description get task size
     * @author Dex
     * @date 2022/08/31
     */
    public int getTaskSize() {
        return taskList.size();
    }

    /**
     * @description get task list instance, direct access of task list is not recommended.
     * for iteration purpose, include getIterator method (TODO)
     * @author Dex
     * @date 2022/08/31
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}

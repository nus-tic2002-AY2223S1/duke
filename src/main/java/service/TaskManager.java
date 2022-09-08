package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.Todo;
import util.DateUtil;
import util.ExceptionUtil;
import util.FileUtil;
import util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private String SOURCE_FILE_PATH = "data/data.txt";

    public static TaskManager getInstance() {
        return taskManager;
    }

    private TaskManager() {
        initTask();
    }

    /**
     * @description init task list from hard disk data
     * @author Dex
     * @date 2022/08/29
     */
    public void initTask() {
        File file = new File(SOURCE_FILE_PATH);

        // check source file exists, create if it does not exist
        if (!file.exists()) {
            createFile(SOURCE_FILE_PATH);
            return;
        }

        // if source file exists, load task instance from source file
        String content = FileUtil.readFileContent(file);
        if (StringUtil.isBlank(content)) {
            return;
        }

        // parse to task instance, skip corrupted data
        List<String> list = StringUtil.stringToList(content, "\n");
        list.stream().map(this::parseToJsonObj)
                .filter(Objects::nonNull)
                .map(this::parseToTask)
                .filter(Objects::nonNull)
                .forEach(taskList::add);
    }

    private JSONObject parseToJsonObj(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Exception exception) {
            ExceptionUtil.getStackTraceAsString(exception);
            return null;
        }
    }

    private Task parseToTask(JSONObject jsonObject) {
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

    private Task parseTask(JSONObject jsonObject) {
        // create task instance
        Task task = new Task(jsonObject.getString(Constant.Task.DESCRIPTION_FIELD));
        task.setDone(jsonObject.getBoolean(Constant.Task.DONE_FIELD));
        return task;
    }

    private Task parseTodoTask(JSONObject jsonObject) {
        // create todo instance
        Todo todo = new Todo(jsonObject.getString(Constant.Task.DESCRIPTION_FIELD));
        todo.setDone(jsonObject.getBoolean(Constant.Task.DONE_FIELD));
        return todo;
    }

    private Task parseDeadlineTask(JSONObject jsonObject) {
        // convert string to localtime
        String by = StringUtil.trim(jsonObject.getString("by"));
        LocalDateTime time = DateUtil.parse(by, Constant.Time.INPUT_FORMAT);

        // create deadline instance
        Deadline deadline = new Deadline(jsonObject.getString(Constant.Task.DESCRIPTION_FIELD));
        deadline.setBy(time);
        deadline.setDone(jsonObject.getBoolean(Constant.Task.DONE_FIELD));
        return deadline;
    }

    private Task parseEventTask(JSONObject jsonObject) {
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

    private void createFile(String path) {
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
        FileUtil.writeStringToFile(new File(SOURCE_FILE_PATH), content);
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

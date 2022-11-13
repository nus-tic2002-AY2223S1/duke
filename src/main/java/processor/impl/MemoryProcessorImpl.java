package processor.impl;

import domain.TaskList;
import domain.task.Deadline;
import domain.task.Event;
import domain.task.Task;
import domain.task.Todo;
import exceptions.EmptyDescriptionException;
import processor.MemoryProcessor;
import utils.Mouth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import static utils.CommonStrings.PATH_FOLDER_NAME;
import static utils.CommonStrings.PATH_FROM_DISK_TO_TXT_FILE;
import static utils.CommonStrings.PIPE;

public class MemoryProcessorImpl implements MemoryProcessor {
    @Override
    public void save(TaskList taskList) {
        String rows = taskList.getTaskList().stream()
                .map(this::getRowFromTask)
                .collect(Collectors.joining("\n"));
        writeToFile(rows);
    }

    @Override
    public void load(TaskList taskList) {
        // pass the path to the file as a parameter
        File file = new File(PATH_FROM_DISK_TO_TXT_FILE);
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            Mouth.speak("Saved file not found, starting with empty task list...");
            return;
        }

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            getTaskFromRow(nextLine).ifPresent(taskList::addTask);
        }
    }

    private void writeToFile(String rows) {
        // Creating an instance of file
        Path path = Paths.get(PATH_FROM_DISK_TO_TXT_FILE);
        Path folderPath = Paths.get(PATH_FOLDER_NAME);

        // Converting string to byte array
        // using getBytes() method
        byte[] arr = rows.getBytes();

        // create directory
        try {
            Files.createDirectories(folderPath);
        } catch(IOException e) {
            // do nothing, folder is already created
        }
        // write a file
        try {
            Files.write(path, arr);
        }
        catch (IOException ex) {
            System.out.print("Some error occurred while writing into file...");
        }
    }

    private String getRowFromTask(Task task) {
        StringBuilder builder = new StringBuilder();
        if (task instanceof Deadline) {
            appendDeadlineDetails((Deadline) task, builder);
        } else if (task instanceof Event) {
            appendEventDetails((Event) task, builder);
        } else if (task instanceof Todo) {
            appendTodoDetails((Todo) task, builder);
        } else {
            return "Error...";
        }
        return builder.toString();
    }

    private Optional<Task> getTaskFromRow(String row) {
        switch (row.charAt(0)) {
            case 'T':
                return getTodoFromRow(row);
            case 'D':
                return getDeadlineFromRow(row);
            case 'E':
                return getEventFromRow(row);
            default:
                Mouth.speak("task cannot be read: " + row);
        }
        return Optional.empty();
    }

    private Optional<Task> getTodoFromRow(String row) {
        String[] taskRowDto = split(row);
        if (taskRowDto.length < 3) {
            Mouth.speak("TODO from mem is missing details: " + row);
            return Optional.empty();
        }
        try {
            Task todo = new Todo(taskRowDto[2]);
            markTaskDoneFromRow(taskRowDto[1], todo);
            return Optional.of(todo);
        } catch (EmptyDescriptionException e) {
            Mouth.speak("TODO from mem is missing description details: " + row);
            return Optional.empty();
        }
    }

    private Optional<Task> getDeadlineFromRow(String row) {
        String[] taskRowDto = split(row);
        if (taskRowDto.length < 4) {
            Mouth.speak("Deadline from mem is missing details: " + row);
            return Optional.empty();
        }
        try {
            LocalDate deadlineDate = LocalDate.parse(taskRowDto[3]);
            Task deadline = new Deadline(taskRowDto[2], deadlineDate);
            markTaskDoneFromRow(taskRowDto[1], deadline);
            return Optional.of(deadline);
        } catch (EmptyDescriptionException e) {
            Mouth.speak("Deadline from mem is missing description details: " + row);
            return Optional.empty();
        }
    }

    /**
     * gets an event object mapped into task to populate taskList
     * @param row - string taken from saved tasks
     * @return - if optional empty is returned, there is some issue with the saved task.
     */
    private Optional<Task> getEventFromRow(String row) {
        String[] taskRowDto = split(row);
        if (taskRowDto.length < 4) {
            Mouth.speak("Event from mem is missing details: " + row);
            return Optional.empty();
        }
        try {
            Task event = new Event(taskRowDto[2], taskRowDto[3]);
            markTaskDoneFromRow(taskRowDto[1], event);
            return Optional.of(event);
        } catch (EmptyDescriptionException e) {
            Mouth.speak("Event from mem is missing description details: " + row);
            return Optional.empty();
        }
    }

    private void markTaskDoneFromRow(String markDoneIndicatorFromRow, Task task) {
        if (Objects.equals(markDoneIndicatorFromRow, "1")) {
            task.markDone();
        } else if (Objects.equals(markDoneIndicatorFromRow, "0")) {
            task.markNotDone(); // actually not required but yeah just be safe
        } else {
            Mouth.speak("Task from mem is neither marked nor unmarked: " + task);
        }
    }

    private String[] split(String row) {
        return row.split(" \\| ");
    }

    private void appendDeadlineDetails(Deadline deadline, StringBuilder builder) {
        builder.append("D").append(PIPE);
        appendIsDone(deadline, builder);
        builder.append(deadline.getDescription()).append(PIPE);
        builder.append(deadline.getBy());
    }

    private void appendEventDetails(Event event, StringBuilder builder) {
        builder.append("E").append(PIPE);
        appendIsDone(event, builder);
        builder.append(event.getDescription()).append(PIPE);
        builder.append(event.getAt());
    }

    private void appendTodoDetails(Todo todo, StringBuilder builder) {
        builder.append("T").append(PIPE);
        appendIsDone(todo, builder);
        builder.append(todo.getDescription());
    }

    private void appendIsDone(Task task, StringBuilder builder) {
        if (task.isDone()) {
            builder.append("1").append(PIPE);
        } else {
            builder.append("0").append(PIPE);
        }
    }

}

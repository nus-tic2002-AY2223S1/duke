package processor;

import domain.task.Task;

import java.util.List;

public interface TaskProcessor {
    void markTask(String userInput, List<Task> taskList);

    void unmarkTask(String userInput, List<Task> taskList);

    void addTodo(String userInput, List<Task> taskList);

    void addDeadline(String userInput, List<Task> taskList);

    void addEvent(String userInput, List<Task> taskList);

    void deleteTask(String userInput, List<Task> taskList);
}

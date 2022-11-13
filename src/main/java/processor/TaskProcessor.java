package processor;

import domain.TaskList;

public interface TaskProcessor {
    void markTask(String userInput, TaskList taskList);

    void unmarkTask(String userInput, TaskList taskList);

    void addTodo(String userInput, TaskList taskList);

    void addDeadline(String userInput, TaskList taskList);

    void addEvent(String userInput, TaskList taskList);

    void deleteTask(String userInput, TaskList taskList);

    void postponeDeadline(String userInput, TaskList taskList);
}

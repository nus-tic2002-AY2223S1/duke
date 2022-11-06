package ui;

import model.Chat;
import model.Task;

public interface Ui {
    void lineSeparator();

    void prompt();

    void printBye();

    void printGreet();

    void printAddedDeletedTask(Chat chat);

    void printMarkedTask(String command, String input, Task task);

    void printMatchingTaskList();
}

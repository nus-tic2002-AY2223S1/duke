import domain.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static domain.Mouth.speak;
import static processor.impl.TaskProcessorImpl.addDeadline;
import static processor.impl.TaskProcessorImpl.addEvent;
import static processor.impl.TaskProcessorImpl.addTask;
import static processor.impl.TaskProcessorImpl.addTodo;
import static processor.impl.TaskProcessorImpl.markTask;
import static processor.impl.TaskProcessorImpl.unmarkTask;
import static utils.CommonStrings.GOODBYE;
import static utils.CommonStrings.GREETING;
import static utils.CommonStrings.LOGO;
import static utils.TaskUtil.getFirstWord;

public class Damith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.println(LOGO);
        speak(GREETING);

        while (!scanner.hasNext("bye")) {
            String userInput = scanner.nextLine();
            // edge case where many blank \n are input by user
            if (userInput.isBlank())
                continue;
            if (userInput.equals("list")) {
                // for edge case where list is the first word of the task description
                speak(taskList);
                continue;
            }
            String command = getFirstWord(userInput);
            switch (command) {
                case "unmark" -> unmarkTask(userInput, taskList);
                case "mark" -> markTask(userInput, taskList);
                case "todo" -> addTodo(userInput, taskList);
                case "deadline" -> addDeadline(userInput, taskList);
                case "event" -> addEvent(userInput, taskList);
                default -> addTask(userInput, taskList);
            }
        }
        speak(GOODBYE);

    }
}

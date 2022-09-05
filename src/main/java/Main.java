import domain.task.Task;
import processor.TaskProcessor;
import processor.impl.TaskProcessorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.CommonStrings.GOODBYE;
import static utils.CommonStrings.GREETING;
import static utils.CommonStrings.HELP;
import static utils.CommonStrings.LOGO;
import static utils.Mouth.speak;
import static utils.TaskUtil.getFirstWord;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        TaskProcessor taskProcessor = new TaskProcessorImpl();

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
                case "unmark":
                    taskProcessor.unmarkTask(userInput, taskList);
                    break;
                case "mark":
                    taskProcessor.markTask(userInput, taskList);
                    break;
                case "todo":
                    taskProcessor.addTodo(userInput, taskList);
                    break;
                case "deadline":
                    taskProcessor.addDeadline(userInput, taskList);
                    break;
                case "event":
                    taskProcessor.addEvent(userInput, taskList);
                    break;
                case "help":
                    speak(HELP);
                    break;
                default:
                    taskProcessor.addTask(userInput, taskList);
                    break;
            }
        }
        speak(GOODBYE);

    }
}

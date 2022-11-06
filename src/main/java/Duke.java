import domain.TaskList;
import processor.MemoryProcessor;
import processor.TaskProcessor;
import processor.impl.MemoryProcesserImpl;
import processor.impl.TaskProcessorImpl;

import java.util.Scanner;

import static utils.CommonStrings.GOODBYE;
import static utils.CommonStrings.GREETING;
import static utils.CommonStrings.HELP;
import static utils.CommonStrings.LOGO;
import static utils.ErrorMessages.whatAreYouDoingErrMsg;
import static utils.Mouth.speak;
import static utils.TaskUtil.getFirstWord;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        TaskProcessor taskProcessor = new TaskProcessorImpl();
        MemoryProcessor memoryProcessor = new MemoryProcesserImpl();

        System.out.println(LOGO);
        speak(GREETING);
        memoryProcessor.load(taskList);
        speak(taskList);

        while (!scanner.hasNext("bye")) {
            String userInput = scanner.nextLine();
            // edge case where many blank \n are input by user
            if (userInput.isBlank())
                continue;
            String command = getFirstWord(userInput);
            switch (command) {
                case "list":
                    speak(taskList);
                    break;
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
                case "delete":
                    taskProcessor.deleteTask(userInput, taskList);
                    break;
                case "help":
                    speak(HELP);
                    break;
                default:
                    speak(whatAreYouDoingErrMsg(userInput));
            }
        }

        // save existing tasks into hard disk
        memoryProcessor.save(taskList);

        speak(GOODBYE);

    }
}

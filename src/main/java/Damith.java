import domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static domain.Mouth.speak;
import static processor.impl.TaskProcessorImpl.*;
import static utils.CommonStrings.*;

public class Damith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.println(LOGO);
        speak(GREETING);

        while (!scanner.hasNext("bye")) {
            String userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                speak(taskList);
            } else if (userInput.contains("unmark")) {
                unmarkTask(userInput, taskList);

            } else if (userInput.contains("mark")) {
                markTask(userInput, taskList);
            }
            else {
                addTask(userInput, taskList);
            }
        }
        speak(GOODBYE);

    }
}

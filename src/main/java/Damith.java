import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.CommonStrings.FIVE_SPACE;
import static utils.CommonStrings.UNDERSCORES;

public class Damith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        String logo = "________                 .__  __  .__     \n" +
                "\\______ \\ _____    _____ |__|/  |_|  |__  \n" +
                " |    |  \\\\__  \\  /     \\|  \\   __\\  |  \\ \n" +
                " |    `   \\/ __ \\|  Y Y  \\  ||  | |   Y  \\\n" +
                "/_______  (____  /__|_|  /__||__| |___|  /\n" +
                "        \\/     \\/      \\/              \\/ ";
        System.out.println(logo);
        speak("Damith here.\nWhat's your problem?");

        while (!scanner.hasNext("bye")) {
            String userInput = scanner.nextLine();
            if ("list".equals(userInput)) {
                speak(tasks);
            } else if (userInput.contains("unmark")) {
                int num = Integer.parseInt(userInput.replaceAll("[^0-9]", ""))-1;
                tasks.get(num).markNotDone();
                speak("OK, I've marked this task as not done yet:\n" + tasks.get(num));
            } else if (userInput.contains("mark")) {
                int num = Integer.parseInt(userInput.replaceAll("[^0-9]", ""))-1;
                tasks.get(num).markDone();
                speak("Nice! I've marked this task as done:\n" + tasks.get(num));
            }
            else {
                tasks.add(new Task(userInput));
                speak("added: " + userInput);
            }
        }
        speak("zzz stop wasting my time");

    }

    public static void speak(String sentence) {
        System.out.println(UNDERSCORES);
        sentence = sentence.replace("\n", "\n     ");
        System.out.println(FIVE_SPACE + sentence);
        System.out.println(UNDERSCORES);
    }

    public static void speak(List<Task> tasks) {
        System.out.println(UNDERSCORES);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(FIVE_SPACE + tasks.get(i));
        }
        System.out.println(UNDERSCORES);
    }
}

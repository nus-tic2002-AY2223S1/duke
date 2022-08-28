import java.util.List;
import java.util.Scanner;

import static utils.CommonStrings.UNDERSCORES;

public class Damith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();

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
                speak(storage.getStorage());
            } else {
                storage.addItem(userInput);
                speak("added: " + userInput);
            }
        }
        speak("zzz stop wasting my time");

    }

    public static void speak(String sentence) {
        System.out.println(UNDERSCORES);
        sentence = sentence.replace("\n", "\n     ");
        System.out.println("     " + sentence);
        System.out.println(UNDERSCORES);
    }

    public static void speak(List<String> sentences) {
        System.out.println(UNDERSCORES);
        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            System.out.println("     " + (i+1) + ". " + sentence);
        }
        System.out.println(UNDERSCORES);
    }
}

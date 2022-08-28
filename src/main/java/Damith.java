import java.util.Scanner;

import static utils.CommonStrings.UNDERSCORES;

public class Damith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = "________                 .__  __  .__     \n" +
                "\\______ \\ _____    _____ |__|/  |_|  |__  \n" +
                " |    |  \\\\__  \\  /     \\|  \\   __\\  |  \\ \n" +
                " |    `   \\/ __ \\|  Y Y  \\  ||  | |   Y  \\\n" +
                "/_______  (____  /__|_|  /__||__| |___|  /\n" +
                "        \\/     \\/      \\/              \\/ ";
        System.out.println("Hello from\n" + logo);
        speak("Damith here.\nWhat's your problem?");

        while (!scanner.hasNext("bye")) {
            speak(scanner.nextLine());
        }
        speak("zzz stop wasting my time");

    }

    public static void speak(String sentence) {
        System.out.println(UNDERSCORES);
        sentence = sentence.replace("\n", "\n     ");
        System.out.println("     " + sentence);
        System.out.println(UNDERSCORES);
    }
}

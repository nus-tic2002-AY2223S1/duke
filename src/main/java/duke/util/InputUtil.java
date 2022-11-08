package duke.util;

import java.util.Scanner;

/**
 * Util class used to handle the input from console.
 *
 * @author Dex
 * @date 2022/10/25
 */
public class InputUtil {

    private InputUtil() {}

    /**
     * Scanner object which take console as source input.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads input from console and convert to string.
     *
     * @return Input from console.
     */
    public static String getInputString() {
        return scanner.nextLine();
    }

}

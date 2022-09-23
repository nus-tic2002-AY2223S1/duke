package ui;

import constant.Constant;
import util.ExceptionUtil;
import util.InputUtil;
import util.StringUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleUI implements UI {

    private int maxLineLength = 0;

    public ConsoleUI() {
        // compute the length line break
        // included bar length of 2 + last space
        this.maxLineLength = getGuidance().stream().map(String::length).max(Comparator.naturalOrder()).orElse(0) + 3;
    }

    @Override
    public void displayGreeting() {
        // print logo
        System.out.println(Constant.STARTING_LOGO);
        System.out.println();

        // get guidance info in list
        List<String> guidance = getGuidance();

        // print guidance in neat format
        System.out.println("Greeting from Duke! ;>");
        printGuidanceLineBreak("~", maxLineLength);
        guidance.forEach(o -> {
            String message = String.format("|%s%s|", o, getPaddingSpace(maxLineLength - o.length() - 2));
            System.out.println(message);
        });
        printGuidanceLineBreak("~", maxLineLength);
    }

    private List<String> getGuidance() {
        String guidance =
                "A quick guidance for you.\n" +
                "Enter `show_command` to check current supported command.\n" +
                "Any input which is not included in supported commands will be treated as\n" +
                "invalid command and will not be processed by the program.";
        return StringUtil.stringToList(guidance, "\n");
    }

    private void printGuidanceLineBreak(String label, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(label);
        }
        System.out.println();
    }

    private String getPaddingSpace(int length) {
        return Stream.generate(() -> " ").limit(length).collect(Collectors.joining());
    }

    @Override
    public void displayLineBreak() {
        System.out.print("+");
        for (int i = 0; i < maxLineLength - 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println();
    }

    @Override
    public void displayDukeErrorMsg(String errorMsg) {
        System.out.println(":(");
        System.out.println("cannot execute command due to error raised");
        System.out.println(errorMsg);
        displayLineBreak();
    }

    @Override
    public void displayUnknownErrorMsg(Throwable throwable) {
        System.out.println(":(");
        System.out.println("unknown error raised, please contact developer for assistance");
        // actual error message should keep internally, prevent exposure to user. (for dev purpose, do not comment out the given code)
        System.out.println(ExceptionUtil.getStackTraceAsString(throwable));
        displayLineBreak();
    }

    public String getDukeCommandInput() {
        System.out.print("~@duke >>> ");
        return InputUtil.getInputString();
    }
}

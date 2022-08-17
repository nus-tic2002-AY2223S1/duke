package exercise;


import java.util.Arrays;
import java.util.Scanner;

public class Week3 {

    public static void printPrice(String item) {
        int dividerPosition = item.indexOf("--");
        String itemName = item.substring(0, dividerPosition);
        String price = item.substring(dividerPosition + 3).replace("/", ".");
        System.out.println(itemName.trim().toUpperCase() + ": " + price);
    }

    public static void printTotalScore(String[] values) {
        int total = 0;
        for (String value : values) {
            if (value.matches("-?[0-9]+")) {
                total += Integer.valueOf(value);
            }
        }
        System.out.println(total);
    }

    public static String[] filterEmails(String[] items) {
        String[] results = new String[items.length];
        int matchCount = 0;
        for (String item : items) {
            if (item.contains("@")) {
                results[matchCount] = item;
                matchCount++;
            }
        }
        return Arrays.copyOf(results, matchCount);
    }

    public static void printItems(String[] items) {
        System.out.println(Arrays.toString(items));
    }

    public static String[] filterAmounts(String sentence) {
        String[] words = sentence.split(" ");
        String[] result = new String[words.length];
        int wordCount = 0;
        for (String word : words) {
            if (word.startsWith("$")) {
                result[wordCount] = word;
                wordCount++;
            }
        }
        return Arrays.copyOf(result, wordCount);
    }

    public static void q1() {
        printPrice("sandwich  --$4/50");
        printPrice("  soda --$10/00");
        printPrice("  fries --$0/50");
    }

    public static void q2() {
        printTotalScore(new String[]{});
        printTotalScore(new String[]{"0", "124", "-15"});
    }

    public static void q3() {
        printItems(filterEmails(new String[]{}));
        printItems(filterEmails(new String[]{"abc"}));
        printItems(filterEmails(new String[]{"adam@example.com", "aab", "john@example.com", "some@"}));
        printItems(filterEmails(new String[]{"xyz", "@bee.com", "aab"}));
    }

    public static void q4() {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.print("Your expenses while overseas?");
        line = in.nextLine();

        String[] amounts = filterAmounts(line);
        System.out.println("Expenses in overseas currency:" + Arrays.toString(amounts));

        double total = 0;
        for (String amount : amounts) {
            amount = amount.replace("$", "");
            if (amount.matches("[0-9]*\\.?[0-9]*")) {
                total += Double.valueOf(amount);
            }
        }
        System.out.println("Total in local currency: $" + String.format("%.2f", total * 1.7));
    }

    public static void main(String[] args) {
        // q1();
        // q2();
        q3();
        // q4();
    }
}

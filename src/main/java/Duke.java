import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.printf("Hello from %n%s%nWhat can I do for you?%n", logo);
        Scanner myObj = new Scanner(System.in);
        String format = "    ─────────────────────────────────────────\n    %s\n    ─────────────────────────────────────────\n";
        String[] arr = {};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arr));

        while(true){
            String input = myObj.nextLine();
            switch (input){
                case "list":
                    StringBuilder output = new StringBuilder();
                    for( int i = 0; i < arr.length; i++){
                        String suffix = "";
                        if (i != arr.length -1){
                            suffix = "\n    ";
                        }
                        output.append(i+1).append(". ").append(arr[i]).append(suffix);
                    }
                    System.out.format(format, output);
                    continue;
                case "bye":
                    System.out.format(format, "Bye. Hope to see you again soon!");
                    break;
                case "":
                    continue;
                default:
                    arrayList.add(input);
                    arr = arrayList.toArray(arr);
                    System.out.format(format, "added: "+input);
            }
        }
    }
}

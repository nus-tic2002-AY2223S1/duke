import java.util.Scanner;

public class Duke {
    public static String line = "\n----------------------------------\n";
    static private String userInput = "";
    static private Task taskList[] = new Task[100];
    static private int taskListCount = 0;

    public static void list() {
        System.out.println(line);
        for (int i = 0; i < taskListCount; i++) {
            System.out.println(i+1 + ". " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
        System.out.println(line);
    }

    public static void addList(Task task) {
        taskList[taskListCount] = task;
        taskListCount++;
        System.out.println(line + "added: " + taskList[taskListCount-1].description + line);
    }

    public static void bye() {
        System.out.println(line + "Bye. Hope to see you again soon!" + line);
    }

    public static void main(String[] args) {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠔⠋⠀⠐⠈⠉⠉⠉⠀⠀⠀⠉⠓⢄⠀⣀⠄⠚⠙⠉⠁⠀⠉⠉⠉⠁⠂⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠐⣜⣦⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⡁⠀⠀⠀⠀⠀⠀⣀⣀⣤⡤⠤⣤⣄⣀⣀⠀⠘⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣷⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣰⣥⠞⠀⠀⠀⠀⢠⡴⠟⠋⠉⠀⠀⠀⠀⠀⠉⠙⠻⢶⣼⣷⣠⣤⡴⠶⠶⠶⠶⠶⢦⣤⣤⣈⠙⣷⣤⣀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣴⢳⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣀⣈⣻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣈⠀⠀⠉⠈⠑⢄⠀⠀\n" +
                "⠀⠀⠀⠀⢀⣼⣿⡋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠶⢛⣫⣭⣭⠿⠾⠯⣭⣭⣍⣛⢦⣀⣤⣶⣞⣋⣭⢭⣭⣭⣭⣭⣟⣓⣶⠀⠑⠄\n" +
                "⠀⢀⣠⠴⡩⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⡤⠴⢞⣉⡴⠚⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⠻⡟⠛⠉⠉⠉⠀⠀⠀⠀⠀⠀⠈⠉⠉⠓⠇⠈\n" +
                "⢤⣿⣼⠂⠀⠀⣿⠀⠀⠀⠀⠀⠀⣴⡛⠛⢛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢂\n" +
                "⣿⣿⠙⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⢷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠘\n" +
                "⢹⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢤⡿⡿⠲⢤⣄⣀⣀⣀⣀⣀⣤⣴⠶⠞⢋⡿⠲⠶⠦⢤⣶⢦⣴⣶⡶⠶⠶⠞⠛⠋⣁⡀\n" +
                "⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣽⠟⠛⠲⠿⠭⠭⠭⠭⠥⠤⣴⠞⠛⠋⠀⠀⠀⠀⠀⣿⢸⡇⠀⠀⠀⢀⡤⢠⡾⠟⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⠀⠀⠀⠀⠀⠀⣀⣤⡶⢟⣫⣤⣶⣶⣶⣶⣦⣤⣤⣸⣧⢿⣤⡴⠎⠻⡗⠉⠀⠀⠀\n" +
                "⠘⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⠀⠀⠀⠀⢀⡇⣿⠀⠀⠀⢀⣾⣿⣿⣷⡾⠿⠟⠋⣉⣉⣉⠉⢻⣷⣝⠋⢹⡼⡇⠀⠀⠀⠙⢆⠀⠀⠀\n" +
                "⠀⠀⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣀⠸⢇⣟⣤⣶⣿⠿⢟⣋⣩⣤⣶⣶⣾⡿⠟⠛⠻⢿⣦⣽⣿⣷⣾⣇⣿⡀⠀⠀⠀⠨⢆⠀⠀\n" +
                "⠀⠀⠀⠸⣿⣭⣭⣶⣶⣶⣶⣶⣼⣿⣿⣿⣿⣿⡟⣛⣭⣴⣾⠿⠟⠛⠉⠁⠀⠀⠀⢀⣀⣀⣀⡉⠙⠛⠻⣿⣿⣿⡿⢿⣷⣶⣦⡾⣆⠀\n" +
                "⠀⠀⠀⠀⠙⠋⠉⣿⡇⣿⣿⣿⣿⣿⣟⣋⣁⣥⣾⡿⠛⠛⠻⠶⠶⠶⣤⣤⣄⣀⣀⣸⡿⠿⣿⡿⢿⠿⣷⣦⣉⠻⣿⣦⡴⠿⠻⣧⣾⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⡇⠙⣻⣿⣿⣿⣿⣿⣿⠛⠛⠿⣷⣶⣤⣤⣤⣀⣀⣀⣀⠈⠉⢹⣿⡙⣿⡄⠸⠀⢀⠉⠻⣷⣼⣿⣀⣠⣴⣿⢹⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢻⣇⢰⣿⣏⠙⠻⣭⣿⣿⣿⣿⣿⣿⣷⣾⣯⣭⣛⠿⣿⣙⠛⠛⠛⠻⣿⣾⣿⣷⣶⣶⣤⣀⠀⠙⢿⣯⣉⣾⡟⢸⠀\n" +
                "⠂⠀⠀⠀⠀⢀⣾⣾⣿⣿⣿⣿⣿⣶⣶⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⣿⣷⣜⢿⣿⣿⣿⣶⣾⣿⣿⣿⣿⣾⣿⣿⣿⣷⣦⣙⠿⣿⣰⡇⠀\n" +
                "⣤⣶⣶⣶⣤⣼⣿⡏⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣭⣭⣉⣉⣉⠉⠙⠛⢿⣧⡘⢿⣿⡿⠿⠿⠿⠿⠿⠿⠿⢻⡿⠿⢿⣿⣦⡈⢻⡆⠀\n" +
                "⠉⠉⠉⠉⠉⠙⠛⠁⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠙⠻⠿⠿⣷⣄⠀⠀⠹⣿⣦⠻⣷⣄⡀⠀⡠⣔⡴⠖⠉⠀⠀⠀⠀⠈⣿⠘⣷⠀\n" +
                "⢀⣴⣿⡿⢠⣶⣷⣦⠀⣼⣷⣶⡄⣾⠀⣷⠀⢸⡇⢸⣷⡄⢠⣿⡎⣿⣧⣴⣶⣾⣿⣷⣜⠿⣿⡗⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⢹⡀⣿⠀\n" +
                "⢸⣏⠀⠀⣿⡇⠀⣹⡇⣿⣷⡿⠃⣿⠀⣿⡀⣸⡇⢸⡿⣷⣼⢻⡇⢿⣿⠿⠿⠛⠛⠛⢿⣦⡈⠻⣯⣱⠀⠀⠀⠀⠀⠀⠀⢀⡾⢰⡏⠀\n" +
                "⠈⠻⠿⠗⠘⠿⠿⠟⠀⠿⠃⠀⠀⠿⠀⠛⠿⠿⠃⠸⠇⠻⠇⠸⠇⢸⣿⡄⠀⠀⠀⠀⠀⠻⣷⣦⣈⡛⡗⠒⠶⣖⣛⣛⣭⣽⡾⠟⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠈⢙⣻⣿⠿⡿⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀";

        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        System.out.println(line + "Hello! I'm Pepe\nWhat can I do for you?" + line);

        userInput = scanner.nextLine();
        while(!userInput.equalsIgnoreCase("bye")) {
            switch(userInput) {
                case "list":
                    list();
                    break;
                case "mark":
                    System.out.println("Which task would you like to mark done?");
                    String mark = scanner.nextLine();
                    int userMark = Integer.parseInt(mark);
                    taskList[userMark-1].markAsDone();
                    break;
                case "unmark":
                    System.out.println("Which task would you like to mark as not done?");
                    String unmark = scanner.nextLine();
                    int userUnmark = Integer.parseInt(unmark);
                    taskList[userUnmark-1].markAsUndone();
                    break;
                default:
                    Task t = new Task(userInput);
                    t.description = userInput;
                    addList(new Task(userInput));
            }
            userInput = scanner.nextLine();
        }
        bye();
    }
}

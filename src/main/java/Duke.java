import java.io.File;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws Exception {
        Parser p = Parser.getInstance();
        Storage s = Storage.getInstance();

        Helper.separator();
        System.out.println("Hello! I'm Nala the Annoying Cat!\n" + "What can I do for you?\n Protip: Type !help for a list of commands!");
        Helper.separator();
        while (true) {
            //tokenizer
            Helper.nalaBehaviour();
            String incomingText = userInput();
            p.stringToToken(incomingText);

            if (p.front().equalsIgnoreCase("!help")) {
                System.out.println("Here's the list of commands:\n" +
                                    "todo [description] - create a todo \n" +
                                     "event [description] /at [date or time] - create an event\n"+
                                    "deadline [description] /by [date or time] - create a deadline\n"+
                                    "mark [index] - mark a task as done\n"+
                                    "unmark [index] - mark a task as not done\n"+
                                    "delete [index] - delete a task\n"+
                                    "list - shows the current list of tasks\n"+
                                    "print - saves the tasklist to a .txt file\n"+
                                    "bye or end - say goodbye to Nala :(");
            }
            else if (p.front().equalsIgnoreCase("mark")) {
                parseMark();
            }

            else if (p.front().equalsIgnoreCase("unmark")){
                parseUnmark();
            }

            else if (p.front().equalsIgnoreCase("bye") || p.front().equalsIgnoreCase("end")) {
                parseBye();
                break;
            }

            else if (incomingText.equalsIgnoreCase("list")) {
                parseShowlist();
            }

            else if (p.front().equalsIgnoreCase("delete")){
                parseDelete();
            }

            else if (p.front().equalsIgnoreCase("print")){
                parsePrint();
            }


            else { //add new task
                parseAddTask();
            }
            p.clear();
        }
    }

    private static void parsePrint() throws Exception {
        Storage s = Storage.getInstance();
        System.out.println("Meow! I will be printing the following list:");
        parseShowlist();
        System.out.println("I will be saving the .txt file under Desktop/dukeFile, if it is not created already.\n" + "What are we naming the output file?");
        while (true){
            String userFilename = userInput();
            s.setFileName(userFilename);
            if (userFilename.equalsIgnoreCase("/e")){
                break;
            }
            if (!s.checkExistence()){
                s.createFile();
                s.populateFile();
                break;
            }
            else{
                System.out.println(userFilename + ".txt already exists on " + System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "dukeFile" + "\n Please type a different filename, or type \"/e\" to go back to the main menu!" );
            }


        }


    }

    public static void parseMark(){
        Parser p = Parser.getInstance();
        TaskList t = TaskList.getInstance();
        //mark something as done. e.g. mark 5
        //delete the "mark"
        p.next();

        //parse the index to changeToMarkAsDone
        try{
            String indexString = p.front();
            int index = Integer.parseInt(indexString);
            t.changeToMarkAsDone(index);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Meow! " + p.front() + " is not a valid integer.");
            t.showTodoList();
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Meow! " + p.front() + " is not in the list. "+ p.front() +" is not marked as done.");
            t.showTodoList();
        }
    }

    public static void parseUnmark() {
        Parser p = Parser.getInstance();
        TaskList t = TaskList.getInstance();
        //mark something as not done. e.g. unmark 5
        p.next();
        //parse the index to changeToMarkNotDone
        try{
            String indexString = p.front();
            int index = Integer.parseInt(indexString);
            t.changeToMarkNotDone(index);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Meow! " + p.front() + " is not a valid integer.");
            t.showTodoList();
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Meow! " + p.front() + " is not in the list. "+ p.front() +" is not marked as undone.");
            t.showTodoList();
        }
    }

    public static void parseBye() {
        Parser p = Parser.getInstance();
        Helper.separator();
        System.out.println("Goodbye! Hope to see you again soon!");
        Helper.separator();
        p.clear();
    }

    public static void parseShowlist() {
        Parser p = Parser.getInstance();
        TaskList t = TaskList.getInstance();
        p.next();
        t.showTodoList();
    }

    public static void parseDelete() {
        Parser p = Parser.getInstance();
        TaskList t = TaskList.getInstance();
        //delete a task. e.g. delete 5
        p.next();
        //parse the index to deleteTask
        try{
            String indexString = p.front();
            int index = Integer.parseInt(indexString);
            t.changeToMarkNotDone(index);
            t.deleteTask(index);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Meow! " + p.front() + " is not a valid integer.");
            t.showTodoList();
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Meow! " + p.front() + " is not in the list.");
            t.showTodoList();
        }
    }

    public static void parseAddTask() throws Exception {
        Parser p = Parser.getInstance();
        TaskList t = TaskList.getInstance();
        String incomingTaskName;
        String incomingType;
        String incomingDate;

        switch (p.front().toLowerCase()){
            case ("todo"):
                if (p.remainingTokens.contains("/at")){
                    Helper.nalaSyntaxError("Event");
                    break;
                }
                else if (p.remainingTokens.contains("/by")){
                    Helper.nalaSyntaxError("Deadline");
                    break;
                }
                else{
                    p.next();
                    incomingTaskName = p.tokenToString();
                    if (incomingTaskName.isBlank()){
                        Helper.nalaSyntaxError("BlankTodo");
                        break;
                    }
                }
                t.addNewTask(incomingTaskName);
                p.clear();
                break;

            case ("deadline"):
                if (p.remainingTokens.contains("/at")){
                    Helper.nalaSyntaxError("Event");
                    break;
                }
                else if (p.remainingTokens.contains("/by")){
                    incomingType = p.front();
                    p.next();
                    StringBuilder TaskNameString= new StringBuilder();
                    while (!p.front().equalsIgnoreCase("/by")){
                        TaskNameString.append(" ").append(p.front());
                        p.next();
                    }
                    p.expect("/by");
                    incomingDate = p.tokenToString();
                    incomingTaskName = TaskNameString.toString();
                    if (incomingTaskName.isBlank()){
                        Helper.nalaSyntaxError("BlankDeadline");
                        break;
                    }
                    if (incomingDate.isBlank()){
                        Helper.nalaSyntaxError("NoDate");
                        break;
                    }
                }
                else{ //no /by
                    Helper.nalaSyntaxError("DeadlineNoBy");
                    break;
                }
                t.addNewTask(incomingTaskName, incomingType, incomingDate);
                p.clear();
                break;

            case  ("event"):
                if (p.remainingTokens.contains("/by")){
                    Helper.nalaSyntaxError("Deadline");
                    break;
                }
                else if (p.remainingTokens.contains("/at")){
                    incomingType = p.front();
                    p.next();
                    StringBuilder TaskNameString= new StringBuilder();
                    while (!p.front().equalsIgnoreCase("/at")){
                        TaskNameString.append(" ").append(p.front());
                        p.next();
                    }
                    p.expect("/at");
                    incomingDate = p.tokenToString();
                    incomingTaskName = TaskNameString.toString();
                    if (incomingTaskName.isBlank()){
                        Helper.nalaSyntaxError("BlankEvent");
                        break;
                    }
                    if (incomingDate.isBlank()){
                        Helper.nalaSyntaxError("NoDate");
                        break;
                    }
                }
                else{ //no /at
                    Helper.nalaSyntaxError("EventNoAt");
                    break;
                }
                t.addNewTask(incomingTaskName, incomingType, incomingDate);
                p.clear();
                break;
            default:
                Helper.nalaConfused();

        }

    }

    public static String userInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}



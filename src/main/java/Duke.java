import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws Exception {
        Parser p = Parser.getInstance();

        Helper.separator();
        System.out.println("Hello! I'm Nala the Annoying Cat!\n" + "What can I do for you?");
        Helper.separator();
        while (true) {
            //tokenizer
            String incomingText = userInput();
            p.stringToToken(incomingText);

            if (p.front().equalsIgnoreCase("mark")) {
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

            else { //add new task
                parseAddTask();
            }
            p.clear();
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



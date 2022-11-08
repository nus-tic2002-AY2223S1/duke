import cat.Nala;
import engine.Parser;
import engine.TaskList;
import storage.Storage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static formatting.Helper.formatter;
import static formatting.Helper.separator;

public class Duke {
    public static void main(String[] args) {
        Parser p = Parser.getInstance();

        separator();
        System.out.println("Hello! I'm Nala the Annoying Cat!\n" + "What can I do for you?\nProtip: Type !help for a list of commands!");
        System.out.println("For event and deadline, please type dates and time in the dd/mm/yyyy HHmm (24 hr) format :) (e.g. 31/12/2022 2359)");
        separator();

            while (true) {
                try {
                //tokenizer
                Nala.nalaBehaviour();
                String incomingText = userInput();
                p.stringToToken(incomingText);

                if (p.front().equalsIgnoreCase("!help")) {
                    System.out.println("Here's the list of commands:\n" +
                                        "todo [description] - create a todo \n" +
                                         "event [description] /at [start date and time, dd/mm/yyyy HHmm] to [end date and time, dd/mm/yyyy HHmm] - create an event\n"+
                                        "deadline [description] /by [date and time, dd/mm/yyyy HHmm] - create a deadline\n"+
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
            } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Nala.nalaConfused();
                }
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
        separator();
        System.out.println("Goodbye! Hope to see you again soon!");
        separator();
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

        switch (p.front().toLowerCase()){
            case ("todo"):
                if (p.getTokenContains("/at")){
                    Nala.nalaSyntaxError("Event");
                    break;
                }
                else if (p.getTokenContains("/by")){
                    Nala.nalaSyntaxError("Deadline");
                    break;
                }
                else{
                    p.next();
                    incomingTaskName = p.tokenToString();
                    if (incomingTaskName.isBlank()){
                        Nala.nalaSyntaxError("BlankTodo");
                        break;
                    }
                }
                t.addNewTask(incomingTaskName);
                p.clear();
                break;

            case ("deadline"):
                String incomingDate;
                if (p.getTokenContains("/at")){
                    Nala.nalaSyntaxError("Event");
                    break;
                }
                else if (p.getTokenContains("/by")){
                    incomingType = p.front();
                    p.next();
                    StringBuilder TaskNameString= new StringBuilder();
                    if (!p.front().equalsIgnoreCase("/by")){
                        TaskNameString.append(p.front());
                        p.next();
                        while (!p.front().equalsIgnoreCase("/by")){
                            TaskNameString.append(" ").append(p.front());
                            p.next();
                        }
                    }
                    p.expect("/by");
                    incomingDate = p.tokenToString();
                    incomingTaskName = TaskNameString.toString();
                    if (incomingTaskName.isBlank()){
                        Nala.nalaSyntaxError("BlankDeadline");
                        break;
                    }
                    if (incomingDate.isBlank()){
                        Nala.nalaSyntaxError("NoDate");
                        break;
                    }
                }
                else{ //no /by
                    Nala.nalaSyntaxError("DeadlineNoBy");
                    break;
                }
                t.addNewTask(incomingTaskName, incomingType, parseDate(incomingDate));
                p.clear();
                break;

            case  ("event"):
                String incomingStartDate;
                String incomingEndDate;
                if (p.getTokenContains("/by")){
                    Nala.nalaSyntaxError("Deadline");
                    break;
                }
                else if (p.getTokenContains("/at")){
                    incomingType = p.front();
                    p.next();
                    StringBuilder TaskNameString= new StringBuilder();
                    if (!p.front().equalsIgnoreCase("/at")){
                        TaskNameString.append(p.front());
                        p.next();
                        while (!p.front().equalsIgnoreCase("/at")){
                            TaskNameString.append(" ").append(p.front());
                            p.next();
                        }
                    }
                    p.expect("/at");
                    StringBuilder DateString = new StringBuilder();
                    DateString.append(p.front());
                    p.next();
                    DateString.append(" ").append(p.front());
                    p.next();
                    incomingStartDate = DateString.toString();

                    p.expect("to");
                    StringBuilder DateString2 = new StringBuilder();
                    DateString2.append(p.front());
                    p.next();
                    DateString2.append(" ").append(p.front());
                    p.next();
                    incomingEndDate = DateString2.toString();

                    incomingTaskName = TaskNameString.toString();
                    if (incomingTaskName.isBlank()){
                        Nala.nalaSyntaxError("BlankEvent");
                        break;
                    }
                    if (incomingStartDate.isBlank() || incomingEndDate.isBlank()){
                        Nala.nalaSyntaxError("NoDate");
                        break;
                    }
                }
                else{ //no /at
                    Nala.nalaSyntaxError("EventNoAt");
                    break;
                }
                LocalDateTime startDate = parseDate(incomingStartDate);
                LocalDateTime endDate = parseDate(incomingEndDate);
                validateTwoDates(startDate,endDate);
                t.addNewTask(incomingTaskName, incomingType, startDate, endDate);
                p.clear();
                break;
            default:
                Nala.nalaConfused();

        }
    }

    public static LocalDateTime parseDate(String dateString) throws Exception {
        LocalDateTime incomingDateTime = LocalDateTime.parse(dateString, formatter);
        incomingDateTime.format(formatter);

        if (incomingDateTime.isBefore(LocalDateTime.now())) {
            throw new Exception("Meow :( Date or time has already passed. The current date and time is " + LocalDateTime.now().format(formatter));
        }

        return incomingDateTime;
    }

    public static void validateTwoDates(LocalDateTime startDate, LocalDateTime endDate) throws Exception {

        if (startDate.isAfter(endDate)) throw new Exception("Meow :( Start Date cannot be after End Date.");
        else if (startDate.isEqual(endDate)) throw new Exception("Meow :( Start Date cannot be the same as End Date.");
    }


    public static String userInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}



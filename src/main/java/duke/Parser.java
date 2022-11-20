package duke;

public class Parser {
    public static final String BYE_COMMAND  ="bye";
    public static final String EXIT_COMMAND  ="exit";
    public static final String LIST_COMMAND  ="list";
    public static final String MARK_COMMAND  ="mark";
    public static final String UNMARK_COMMAND  ="unmark";
    public static final String TODO_COMMAND ="todo";
    public static final String DEADLINE_COMMAND  ="deadline";
    public static final String EVENT_COMMAND  ="event";
    public static final String DELETE_COMMAND  ="delete";
    public static final String FIND_COMMAND  ="find";
    public static final String ARCHIVE_COMMAND  ="archive";

    public static void executeCommand( String command)
            throws UnknownCommandException, EmptyDescriptionException,MissingTimeException,InvalidDeleteOptionException{
        String[] inputs = command.split(" ");
        String[] commandArgs;
        Task t = null;
        switch(inputs[0]){
            case ARCHIVE_COMMAND:
                Storage.archive();
                Ui.listTasks();
                return;
            case FIND_COMMAND:
                if (inputs.length == 1){
                    Ui.listTasks();
                }
                Tasklist.find(inputs[1]);
                return;
            case LIST_COMMAND :
                Ui.listTasks();
                break;
            case MARK_COMMAND:
                if (inputs.length == 1){
                    System.out.println("Mark and Unmark need an index");
                    return;
                }
                Tasklist.mark(inputs[1]);
                break;
            case UNMARK_COMMAND:
                if (inputs.length == 1){
                    System.out.println("Mark and Unmark need an index");
                    return;
                }
                Tasklist.unmark(inputs[1]);
                break;
            case TODO_COMMAND:
                commandArgs = splitCommandArgs(command);
                validateDescritionNotEmpty(TODO_COMMAND,commandArgs[0]);
                t = Tasklist.addTodo(commandArgs[0]);
                break;
            case DEADLINE_COMMAND:
                commandArgs = splitCommandArgs(command);
                validateDescritionNotEmpty(DEADLINE_COMMAND,commandArgs[0]);
                validateTimeFormatIsPresent(commandArgs);
                t = Tasklist.addDeadline(commandArgs[0],commandArgs[1]);
                break;
            case EVENT_COMMAND:
                commandArgs = splitCommandArgs(command);
                validateDescritionNotEmpty(EVENT_COMMAND,commandArgs[0]);
                validateTimeFormatIsPresent(commandArgs);
                t = Tasklist.addEvent(commandArgs[0],commandArgs[1]);
                break;
            case DELETE_COMMAND:
                commandArgs = splitCommandArgs(command);
                validateDescritionNotEmpty(DELETE_COMMAND,commandArgs[0]);
                Tasklist.deleteTask(Tasklist.tasks,Integer.parseInt(commandArgs[0].trim()) - 1);
                break;
            case EXIT_COMMAND:
                break;
            case BYE_COMMAND:
                break;
            default:
                throw new UnknownCommandException();
        }
        if (t != null){
            Ui.printTaskAddAcknowledge(t, Tasklist.size());
        }
        try{Storage.saveToFile();}
        catch (Exception e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    //Splits the input from commandline for Deadlines and Events to the desc and time components
    private static String[] splitCommandArgs(String input){
        String[] commandArgs;
        int indexOfFirstSpace= (input.split(" ")[0].length());
        // use this regex for simplicity as a deadline is going to use /by or /at
        commandArgs = input.substring(indexOfFirstSpace).split("/[a-z]{2}");
        return commandArgs;
    }

    private static void validateTimeFormatIsPresent(String[] command)throws MissingTimeException{
        if (command.length != 2){
            throw new MissingTimeException();
        }
    }
    private static void validateDescritionNotEmpty(String name,String desc)throws EmptyDescriptionException{
        if (desc.isEmpty()){
            throw new EmptyDescriptionException(name);
        }
    }
}

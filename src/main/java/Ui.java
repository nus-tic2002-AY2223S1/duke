import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public enum UiIcon{
        FATAL("❌"),
        WARNING("⚠️"),
        INFO("💬");

        public final String icon;
        public String getIcon(){return this.icon;}
        private UiIcon(String icon) {
            this.icon = icon;
        }
    }

    public enum UiMessage{
        INFO_WELCOME("Hello from Duke. What can I do for you?"),
        INFO_GOODBYE("Bye. Hope to see you again soon!"),
        ERROR_COMMAND_UNKNOWN("OOPS!!! I'm sorry, but I don't know what that means :( \n\tSpecify Todo / Deadline / Event. \n\tE.g. Todo <Task Name>"),
        ERROR_PROCESS_ACTION("OOPS!!! The selection to %s cannot be empty."),
        ERROR_PROCESS_COMMAND("OOPS!!! The description of %s cannot be empty.");

        public final String text;
        public String getText(){return this.text;}
        private UiMessage(String text) {
            this.text = text;
        }
    }
    protected String format = "    ─────────────────────────────────────────\n    %s\n    ─────────────────────────────────────────\n";

    public Ui() {}
    private void sendWarning(UiMessage u,String m){System.out.format(this.format, String.format("%s %s",UiIcon.WARNING.getIcon(), String.format(u.getText(),m) ));}
    private void sendInfo(UiMessage u,String m){System.out.format(this.format, String.format("%s %s",UiIcon.INFO.getIcon(), String.format(u.getText(),m) ));}
    private void sendError(UiMessage u,String m){System.out.format(this.format, String.format("%s %s",UiIcon.FATAL.getIcon(), String.format(u.getText(),m) ));}
    public void sendConsolidatedOutput(StringBuilder message){System.out.format(this.format,String.format("%s %s",UiIcon.INFO.getIcon(),message));}
    public void sendGenericInfo(String message){System.out.format(this.format, String.format("%s %s",UiIcon.INFO.getIcon(),message));}
    public void sendGenericWarning(String message){System.out.format(this.format, String.format("%s %s",UiIcon.WARNING.getIcon(),message));}

    public void sendProcessActionError(String message){sendError(UiMessage.ERROR_PROCESS_ACTION,message);}
    public void sendProcessCommandError(String message){sendError(UiMessage.ERROR_PROCESS_COMMAND,message);}
    public void sendCommandUnknownError(){sendError(UiMessage.ERROR_COMMAND_UNKNOWN,"");}

    public void sendWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sendInfo(UiMessage.INFO_WELCOME,"");
    }
    public void sendGoodbyeMessage(){sendInfo(UiMessage.INFO_GOODBYE,"");}

    public void printNewTasks(String task,int size) {
        StringBuilder s = new StringBuilder();
        s.append("Got it. I've added this task:\n\t").
                append(task).
                append("\n\tNow you have ").
                append(size).
                append(size > 1 ? " tasks " :" task ").
                append("in the list.");
        sendConsolidatedOutput(s);
    }

    public void printTaskRemovedByIndex(String task, int size) {
        StringBuilder s = new StringBuilder();
        s.append("Noted. I've removed this task:\n\t").
                append(task).
                append("\n\tNow you have ").
                append(size-1).
                append(size > 1 ? " tasks " :" task ").
                append("in the list.");
        sendConsolidatedOutput(s);
    }

    public void printMarkTask(String task, Boolean isMark) {
        StringBuilder s = new StringBuilder();
        s.append(isMark ? "Nice! I've marked this task as done:\n    " :"OK, I've marked this task as not done yet:\n    " ).
                append(task);
        sendConsolidatedOutput(s);
    }

    public void printList(ArrayList<Task> tasks,Boolean withIndex) throws DukeException{
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0){
            s.append("You have no task.");
        }else{
            s.append("Here are the tasks in your list:\n    ");
            for( int i = 0; i < tasks.size(); i++){
                String suffix = "";
                if (i != tasks.size() -1){
                    suffix = "\n    ";
                }
                if (withIndex){
                    s.append(i+1);
                }
                s.append(".").append(tasks.get(i).toString()).append(suffix);
            }
        }
        sendConsolidatedOutput(s);
    }

    public String[] readIn(){
        Scanner inputRaw = new Scanner(System.in).useDelimiter("\n");
        String inputLine = inputRaw.nextLine();
        //splitting input into <command> + <task name + at/by>
        return inputLine.split(" ", 2);
    }

}

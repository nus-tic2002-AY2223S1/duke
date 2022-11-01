package Interface;
import Duke.Task;
import Duke.TaskList;
import Util.DateProcessor;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public enum UiIcon{
        FATAL("\u274c"),
        WARNING("\u26a0️"),
        INFO("\u26aa"),
        CONFIRMATION("\u2705");

        public final String icon;
        public String getIcon(){return this.icon;}
        private UiIcon(String icon) {
            this.icon = icon;
        }
    }

    public enum UiMessage{
        GENERIC(""),
        GENERIC_FORMATTED("%s"),
        INFO_WELCOME("Hello from Duke. What can I do for you?"),
        INFO_WELCOME_EXISTING("Hello again, %s! Welcome back. What can I do for you?"),
        INFO_LAST_SAVED("[Last Modified on %s]"),
        INFO_GOODBYE("Bye. Hope to see you again soon!"),
        ERROR_COMMAND_UNKNOWN("OOPS!!! I'm sorry, but I don't know what that means :( \n\tSpecify Todo / Deadline / Event. \n\tE.g. Todo <Task Name>"),
        ERROR_PROCESS_ACTION("OOPS!!! The selection to %s cannot be empty."),
        ERROR_PROCESS_COMMAND("OOPS!!! The description of %s cannot be empty."),
        ERROR_FIND_DATE("OOPS!!! The date to search cannot be empty."),
        ERROR_FIND_TASK("OOPS!!! The keyword to search cannot be empty.");
        public final String text;
        public String getText(){return this.text;}
        private UiMessage(String text) {
            this.text = text;
        }
    }
    protected String format = "    ───────────────────────────────────────────────────────────────────────────\n    %s\n    ───────────────────────────────────────────────────────────────────────────\n";

    public Ui() {}
    private void sendPlain(UiMessage u,String m){System.out.printf("\t%s%n", String.format(u.getText(),m) );}
    private void sendWarning(UiMessage u,String m){System.out.format(this.format, String.format("%s %s",UiIcon.WARNING.getIcon(), String.format(u.getText(),m) ));}
    private void sendInfo(UiMessage u,String m){System.out.format(this.format, String.format("%s %s",UiIcon.INFO.getIcon(), String.format(u.getText(),m) ));}
    private void sendFatal(UiMessage u,String m){System.out.format(this.format, String.format("%s %s",UiIcon.FATAL.getIcon(), String.format(u.getText(),m) ));}
    private void sendConfirmation(UiMessage u,String m){System.out.format(this.format, String.format("%s %s",UiIcon.CONFIRMATION.getIcon(), String.format(u.getText(),m) ));}
    public void sendConfirmedOutput(StringBuilder message){sendConfirmation(UiMessage.GENERIC_FORMATTED, String.valueOf(message));}
    public void sendGenericInfo(String message){sendInfo(UiMessage.GENERIC_FORMATTED,message);}
    public void sendGenericWarning(String message){sendWarning(UiMessage.GENERIC_FORMATTED,message);}
    public void sendGenericFatal(String message){sendFatal(UiMessage.GENERIC_FORMATTED,message);}
    public void sendProcessActionError(String message){sendFatal(UiMessage.ERROR_PROCESS_ACTION,message);}
    public void sendProcessCommandError(String message){sendFatal(UiMessage.ERROR_PROCESS_COMMAND,message);}
    public void sendCommandUnknownError(){sendFatal(UiMessage.ERROR_COMMAND_UNKNOWN,"");}
    public void sendProcessFindDateError(){sendFatal(UiMessage.ERROR_FIND_DATE,"");}
    public void sendProcessFindTaskError(){sendFatal(UiMessage.ERROR_FIND_TASK,"");}

    public void sendWelcomeMessage(TaskList t){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        if (t.getLastInfo() != null) {
            sendInfo(UiMessage.INFO_WELCOME_EXISTING, t.getLastInfo()[0]);
            printList(t.getList(),true);
            sendPlain(UiMessage.INFO_LAST_SAVED,DateProcessor.unixToString(Long.parseLong(t.getLastInfo()[1])) );
        }else{
            sendInfo(UiMessage.INFO_WELCOME,"");
        }
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
        sendConfirmedOutput(s);
    }

    public void printTaskRemovedByIndex(String task, int size) {
        StringBuilder s = new StringBuilder();
        s.append("Noted. I've removed this task:\n\t").
                append(task).
                append("\n\tNow you have ").
                append(size-1).
                append(size > 1 ? " tasks " :" task ").
                append("in the list.");
        sendConfirmedOutput(s);
    }

    public void printMarkTask(String task, Boolean isMark) {
        StringBuilder s = new StringBuilder();
        s.append(isMark ? "Nice! I've marked this task as done:\n    " :"OK, I've marked this task as not done yet:\n    " ).
                append(task);
        sendConfirmedOutput(s);
    }

    public void printList(ArrayList<Task> tasks, Boolean withIndex){
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0){
            s.append("You have no task.");
        }else{
            s.append("Here are the task(s) in your list:\n    ");
            buildList(tasks, withIndex, s);
        }
        sendConfirmedOutput(s);
    }

    private void buildList(ArrayList<Task> tasks, Boolean withIndex, StringBuilder s) {
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

    public void printSelectedList(ArrayList<Task> tasks, Boolean withIndex, String date){
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0){
            s.append("You have no task scheduled on ").append(date);
        }else{
            s.append("Here are the task(s) scheduled on this day:\n    ");
            buildList(tasks, withIndex, s);
        }
        sendConfirmedOutput(s);
    }

    public void printFoundList(ArrayList<Task> tasks, Boolean withIndex, String keyword){
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0){
            s.append("You have no task with keyword '").append(keyword).append("'");
        }else{
            s.append("Here are the task(s) that contains '").append(keyword).append("':\n    ");
            buildList(tasks, withIndex, s);
        }
        sendConfirmedOutput(s);
    }
}

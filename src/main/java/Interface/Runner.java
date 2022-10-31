package Interface;
import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import Duke.Todo;
import Util.DukeException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Runner {
    public enum Command{
        DEADLINE("deadline"),
        EVENT("event"),
        TODO("todo");

        public final String label;
        public String getLabel(){
            return this.label;
        }
        Command(String label) {
            this.label = label;
        }
    }
    public enum Action{
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete");

        public final String label;
        public String getLabel(){
            return this.label;
        }
        Action(String label) {
            this.label = label;
        }
    }
    protected Ui ui;
    protected boolean isExit;
    protected ArrayList<Task> arrayList;
    public Runner(ArrayList<Task> a) {
        arrayList = a;
        ui = new Ui();
    }
    public void run(ArrayList<Task> tasks, Ui runtimeUi) throws DukeException {
        arrayList = tasks;
        ui = runtimeUi;
    }
    public void printList(Boolean withIndex){ui.printList(arrayList,withIndex);}
    public  void printSingle(int index, Boolean isMark) {ui.printMarkTask(arrayList.get(index).toString(),isMark);}
    public  void printNewTaskAdded() {ui.printNewTasks(arrayList.get(arrayList.size()-1).toString(), arrayList.size());}
    public  void printTaskRemovedByIndex(int index) {ui.printTaskRemovedByIndex(arrayList.get(index).toString(), arrayList.size());}
    public void printExitMessage()  {ui.sendGoodbyeMessage();}
    public void printCommandUnknownMessage()  {ui.sendCommandUnknownError();}
    public void printFatalMessage(String message){ui.sendGenericFatal(message);}
    public void printWarningMessage(String message){ui.sendGenericWarning(message);}
    public void printProcessCommandMessage(String message){ui.sendProcessCommandError(message);}
    public void printProcessActionMessage(String message){ui.sendProcessActionError(message);}
    public int getIndex(String s) {
        if (!isInteger(s)){
            return -1;
        }
        int i = Integer.parseInt(s)-1;
        if (i < 0 || i >= this.arrayList.size()){
            printWarningMessage("No such index.");
            return -1;
        }
        return i;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            printFatalMessage("Enter the numeric item index.");
            return false;
        }
        return true;
    }

    public void processAction(Action a, String[] s) {
        if (s.length == 1){
            printProcessActionMessage(a.getLabel());
            return;
        }

        int idx = getIndex(s[1]);
        if (idx != -1){
            switch (a){
                case MARK:
                    arrayList.get(idx).markTask();
                    printSingle(idx, true);
                    break;
                case UNMARK:
                    arrayList.get(idx).unmarkTask();
                    printSingle(idx, false);
                    break;
                case DELETE:
                    printTaskRemovedByIndex(idx);
                    arrayList.remove(idx);
                    break;
            }
        }
    }

    public void processCommand(Command c, String[] s) {
        if (s.length == 1){
            printProcessCommandMessage(c.getLabel());
            return;
        }

        if (s[1].trim().equals("")){
            printProcessCommandMessage(c.getLabel());
            return;
        }

        switch (c){
            case EVENT:
                String[] eventByInput = s[1].split("/at", 2);
                arrayList.add(new Event(eventByInput[0].trim(),eventByInput.length == 1 ? null: eventByInput[1].trim()));
                break;
            case DEADLINE:
                String[] deadlineByInput = s[1].split("/by", 2);
                arrayList.add(new Deadline(deadlineByInput[0].trim(),deadlineByInput.length == 1 ? null: deadlineByInput[1].trim()));
                break;
            case TODO:
                String[] todoByInput = s[1].split("/by", 2);
                arrayList.add(new Todo(todoByInput[0].trim(),todoByInput.length == 1 ? null: todoByInput[1].trim()));
                break;
        }
        printNewTaskAdded();

        try{
            saveFile();
        }catch(IOException e){
            ui.sendGenericWarning("IOException");
        }
    }

    //TODO: save and read from file
    public void saveFile() throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        for(Task str: arrayList) {
//            String s = str.getType() + "|" +
//                    str.getStatusIcon() + "|" +
//                    str.getDescription() + "|" +
//                    str.getDue();
            writer.write(str.toString() + System.lineSeparator());
        }
        writer.close();
    }
}

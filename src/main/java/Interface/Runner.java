package Interface;
import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import Duke.Todo;
import Util.DateProcessor;
import Util.DukeException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Runner {
    public enum Command{
        DEADLINE("deadline"),
        EVENT("event"),
        DATE("date"),
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
    public void printSelectedList(ArrayList<Task> l,Boolean withIndex,String date){ui.printSelectedList(l,withIndex,date);}
    public void printFoundList(ArrayList<Task> l,Boolean withIndex,String keyword){ui.printFoundList(l,withIndex,keyword);}
    public  void printSingle(int index, Boolean isMark) {ui.printMarkTask(arrayList.get(index).toString(),isMark);}
    public  void printNewTaskAdded() {ui.printNewTasks(arrayList.get(arrayList.size()-1).toString(), arrayList.size());}
    public  void printTaskRemovedByIndex(int index) {ui.printTaskRemovedByIndex(arrayList.get(index).toString(), arrayList.size());}
    public void printExitMessage()  {ui.sendGoodbyeMessage();}
    public void printCommandUnknownMessage()  {ui.sendCommandUnknownError();}
    public void printFatalMessage(String message){ui.sendGenericFatal(message);}
    public void printWarningMessage(String message){ui.sendGenericWarning(message);}
    public void printProcessCommandMessage(String message){ui.sendProcessCommandError(message);}
    public void printProcessActionMessage(String message){ui.sendProcessActionError(message);}
    public void printProcessFindDateMessage(){ui.sendProcessFindDateError();}
    public void printProcessFindTaskMessage(){ui.sendProcessFindTaskError();}
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

                    try{
                        saveFile();
                    }catch(IOException e){
                        ui.sendGenericWarning("IOException");
                    }
                    break;
                case UNMARK:
                    arrayList.get(idx).unmarkTask();
                    printSingle(idx, false);

                    try{
                        saveFile();
                    }catch(IOException e){
                        ui.sendGenericWarning("IOException");
                    }
                    break;
                case DELETE:
                    printTaskRemovedByIndex(idx);
                    arrayList.remove(idx);

                    try{
                        saveFile();
                    }catch(IOException e){
                        ui.sendGenericWarning("IOException");
                    }
                    break;
            }
        }
    }

    private boolean processDue(Command c,String[] s){
        long convertedTime;

        switch (c){
            case EVENT:
                String[] eventByInput = s[1].split("/at", 2);
                if(eventByInput.length ==1){
                    arrayList.add(new Event(eventByInput[0].trim(),new long[]{}));
                }else{
                    long[] convertedTimeRange = DateProcessor.processDateTimeRange(eventByInput[1].trim());
                    if (convertedTimeRange== null){
                        return false;
                    }
                    arrayList.add(new Event(eventByInput[0].trim(),convertedTimeRange));
                }
                break;
            case DEADLINE:
                String[] deadlineByInput = s[1].split("/by", 2);
                if(deadlineByInput.length ==1){
                    arrayList.add(new Deadline(deadlineByInput[0].trim(),0));
                }else{
                    convertedTime = DateProcessor.processDateTime(deadlineByInput[1].trim());
                    if (convertedTime == -1){
                        return false;
                    }
                    arrayList.add(new Deadline(deadlineByInput[0].trim(),convertedTime));
                }
                break;
            case TODO:
                String[] todoByInput = s[1].split("/by", 2);
                if(todoByInput.length ==1){
                    arrayList.add(new Todo(todoByInput[0].trim(),0));
                }else{
                    convertedTime = DateProcessor.processDateTime(todoByInput[1].trim());
                    if (convertedTime == -1){
                        return false;
                    }
                    arrayList.add(new Todo(todoByInput[0].trim(),convertedTime));
                }
                break;
        }
        return true;
    }
    public void processAddTask(Command c, String[] s) {
        if (s.length == 1){
            printProcessCommandMessage(c.getLabel());
            return;
        }

        if (s[1].trim().equals("")){
            printProcessCommandMessage(c.getLabel());
            return;
        }

        if(!processDue(c,s)){
            return;
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
        //Name and time stamp
        writer.write(System.getProperty("user.name") + System.lineSeparator());
        writer.write(Instant.now().getEpochSecond() + System.lineSeparator());

        for(Task str: arrayList) {
            String s = str.getType() + "," +
                    str.getIsDone() + "," +
                    str.getDescription() + "," +
                    str.getDue() + "," +
                    str.getTo();
            writer.write(s + System.lineSeparator());
        }
        writer.close();
    }

    private long checkFindDate(String[] s) {
        if (s.length == 1){
            printProcessFindDateMessage();
            return -1;
        }

        if (s[1].trim().equals("")){
            printProcessFindDateMessage();
            return -1;
        }

        DateProcessor d = new DateProcessor();
        return DateProcessor.processDate(s[1]);
    }
    public void processFindDate(String[] s) {
        long d = checkFindDate(s);
        if(d == -1){
            return;
        }

        Task[] arr = {};
        ArrayList<Task> selected = new ArrayList<>(Arrays.asList(arr));

        for (Task t : this.arrayList){
            if (t.getDue() >= d && t.getDue() <= d + 86400){
                selected.add(t);
            }
        }
        printSelectedList(selected,true,s[1]);
    }

    private String checkFindTask(String[] s) {
        if (s.length == 1){
            printProcessFindTaskMessage();
            return null;
        }

        if (s[1].trim().equals("")){
            printProcessFindTaskMessage();
            return null;
        }
        return s[1];
    }
    public void processFindTask(String[] s) {
        String k = checkFindTask(s);
        if (k ==null){
            return;
        }

        Task[] arr = {};
        ArrayList<Task> selected = new ArrayList<>(Arrays.asList(arr));

        for (Task t : this.arrayList){
            if (Arrays.asList(t.getDescription().split(" ")).contains(k)){
                selected.add(t);
            }
        }
        printFoundList(selected,true,s[1]);
    }
}

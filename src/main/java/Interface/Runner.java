package Interface;
import Duke.*;
import Util.DateProcessor;
import Util.DukeException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.*;

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
    protected Map<Integer,String> fileMap= new HashMap<Integer,String>();
    public Runner(ArrayList<Task> a) {
        arrayList = a;
        ui = new Ui();
    }
    public void run(ArrayList<Task> tasks) throws DukeException {
        arrayList = tasks;
    }
    public String printList(Boolean withIndex){return ui.printList(arrayList,withIndex);}
    public void printSelectedList(ArrayList<Task> l,Boolean withIndex,String date){ui.printSelectedList(l,withIndex,date);}
    public void printFoundList(ArrayList<Task> l,Boolean withIndex,String keyword){ui.printFoundList(l,withIndex,keyword);}
    public  String printSingle(int index, Boolean isMark) {return ui.printMarkTask(arrayList.get(index).toString(),isMark);}
    public  void printNewTaskAdded() {ui.printNewTasks(arrayList.get(arrayList.size()-1).toString(), arrayList.size());}
    public  String printTaskRemovedByIndex(Task t) {
        return ui.printTaskRemovedByIndex(t.toString(), arrayList.size());}
    public void printExitMessage()  {ui.sendGoodbyeMessage();}
    public String printCommandUnknownMessage()  {return ui.sendCommandUnknownError();}
    public void printFatalMessage(String message){ui.sendGenericFatal(message);}
    public void printWarningMessage(String message){ui.sendGenericWarning(message);}
    public void printProcessCommandMessage(String message){ui.sendProcessCommandError(message);}
    public String printProcessActionMessage(String message){return ui.sendProcessActionError(message);}
    public void printProcessFindDateMessage(){ui.sendProcessFindDateError();}
    public void printProcessFindTaskMessage(){ui.sendProcessFindTaskError();}
    public void printProcessViewScheduleMessage(){ui.sendProcessViewScheduleError();}
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

    public String processAction(Action a, String[] s) {
        if (s.length == 1){
            return printProcessActionMessage(a.getLabel());
        }

        int idx = getIndex(s[1]);
        if (idx != -1){
            switch (a){
                case MARK:
                    arrayList.get(idx).markTask();
                    try{
                        saveFile();
                    }catch(IOException e){
                        return ui.sendGenericWarning("IOException");
                    }
                    return printSingle(idx, true);
                case UNMARK:
                    arrayList.get(idx).unmarkTask();
                    try{
                        saveFile();
                    }catch(IOException e){
                        return ui.sendGenericWarning("IOException");
                    }
                    return printSingle(idx, false);
                case DELETE:
                    Task copy = arrayList.get(idx);
                    arrayList.remove(idx);
                    try{
                        saveFile();
                    }catch(IOException e){
                        return ui.sendGenericWarning("IOException");
                    }
                return printTaskRemovedByIndex(copy);
            }
        }
        return "";
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
    public void restoreFile(int selection) throws IOException {
        String cacheFileName = "cache/archives.txt";
        String archiveFileDir = "archives/";
        String path = "";

        Scanner scn = new Scanner(new File(cacheFileName));
        while (selection > 0 && scn.hasNext()){
            path = scn.nextLine();
            selection--;
        }

        File fileToMove = new File(archiveFileDir + path);
        File toReplace = new File("save/output.txt");
        System.out.println(fileToMove.toPath());
        System.out.println(toReplace.toPath());

        Files.copy(fileToMove.toPath(), toReplace.toPath(),StandardCopyOption.REPLACE_EXISTING);
        ui.sendGenericConfirmation("Successfully restored records. Rebooting.");
        TaskList t = new TaskList(new Scanner(new File(toReplace.toString())));
    }

    public boolean archiveFile() throws IOException {
        String userName = System.getProperty("user.name");
        long timeNow = Instant.now().getEpochSecond();
        String archiveFileName = "archive_" + userName + "_" + timeNow+".txt";
        String archivePath ="archives/";

        File fileToMove = new File("save/output.txt");
        boolean isSuccess = fileToMove.renameTo(new File(archivePath,archiveFileName));
        if (!isSuccess){
            ui.sendGenericFatal("Failed to archive records.");
            return false;
        }
        ui.sendGenericConfirmation("Successfully archived records.");
        return true;
    }

    public void saveFile() throws IOException {
        String userName = System.getProperty("user.name");
        long timeNow = Instant.now().getEpochSecond();
        FileWriter writer = new FileWriter("save/output.txt");

        //Name and time stamp
        writer.write(userName + System.lineSeparator());
        writer.write(timeNow + System.lineSeparator());

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

    public void cacheFile() throws IOException {
        FileWriter writer = new FileWriter("cache/archives.txt");

        for(Map.Entry<Integer,String> m: fileMap.entrySet()) {
            String s = m.getValue();
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
        selected.sort(new TaskComparator());
        printSelectedList(selected,true,s[1]);
    }

    static class TaskComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2)
        {
            return Long.compare(t1.getDue(), t2.getDue());
        }
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

    private String checkViewSchedule(String[] s) {
        if (s.length == 1){
            printProcessViewScheduleMessage();
            return null;
        }

        if (s[1].trim().equals("")){
            printProcessViewScheduleMessage();
            return null;
        }
        return s[1];
    }

    //TODO
    public void processViewSchedule(String[] s) {
        String d = checkViewSchedule(s);
        if (d ==null){
            return;
        }
        long dayStartTS = DateProcessor.dateToUnix(d);
        long dayEndTS =dayStartTS + 86400;
    }

    public void processArchive() {
        try{
            if (archiveFile()){
                arrayList.clear();
            }
        }catch(IOException e){
            ui.sendGenericWarning("IOException");
        }
    }

    private long translateFileName(String fileName){
        String[] parsedFileName = fileName.split("_", 3);
        String[] parsedFileDate = parsedFileName[2].split("\\.", 2);
        return Long.parseLong(parsedFileDate[0]);
    }

    private void listFiles() {
        File folder = new File("archives/");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        int i = 1;

        ui.sendGenericInfo("Select from the files below by entering restore <index>.");
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                this.fileMap.put(i,listOfFile.getName());
                System.out.println(fileMap);
                ui.sendGenericPlain(i+ ". " + DateProcessor.unixToString(translateFileName(listOfFile.getName())) );
            }
            i++;
        }

        try{
            cacheFile();
        }catch(IOException e){
            ui.sendGenericWarning("IOException");
        }
    }

    private String checkProcessRestore(String[] s) {
        if (s.length == 1){
            listFiles();
            return null;
        }

        if (s[1].trim().equals("")){
            printProcessViewScheduleMessage();
            return null;
        }
        return s[1];
    }

    public boolean processRestore(String[] s) {
        String d = checkProcessRestore(s);
        if (d ==null){
            return false;
        }

        try{
            restoreFile(Integer.parseInt(d));
        }catch(IOException e){
            ui.sendGenericFatal("Failed to restore records.");
        }
        return true;
    }
}

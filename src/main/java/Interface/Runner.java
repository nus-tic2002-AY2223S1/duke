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
    public String printSelectedList(ArrayList<Task> l,Boolean withIndex,String date){return ui.printSelectedList(l,withIndex,date);}
    public String printFoundList(ArrayList<Task> l,Boolean withIndex,String keyword){return ui.printFoundList(l,withIndex,keyword);}
    public  String printSingle(int index, Boolean isMark) {return ui.printMarkTask(arrayList.get(index).toString(),isMark);}
    public  String printNewTaskAdded() {return ui.printNewTasks(arrayList.get(arrayList.size()-1).toString(), arrayList.size());}
    public  String printTaskRemovedByIndex(Task t) {
        return ui.printTaskRemovedByIndex(t.toString(), arrayList.size());}
    public String  printExitMessage(){return ui.sendGoodbyeMessage();}
    public String printCommandUnknownMessage()  {return ui.sendCommandUnknownError();}
    public String  printFatalMessage(String message){return ui.sendGenericFatal(message);}
    public String printWarningMessage(String message){return ui.sendGenericWarning(message);}
    public String  printProcessCommandMessage(String message){return ui.sendProcessCommandError(message);}
    public String printProcessActionMessage(String message){return ui.sendProcessActionError(message);}
    public String printProcessFindDateMessage(){return ui.sendProcessFindDateError();}
    public String printProcessFindTaskMessage(){return ui.sendProcessFindTaskError();}
    public String  printProcessViewScheduleMessage(){return ui.sendProcessViewScheduleError();}
    public int getIndex(String s) throws DukeException{
        try {
            if (!isInteger(s)){
                return -1;
            }
        }catch (DukeException e){
            throw new DukeException(e.getMessage());
        }

        int i = Integer.parseInt(s)-1;
        if (i < 0 || i >= this.arrayList.size()){
            throw new DukeException(printWarningMessage("No such index.")) ;
        }
        return i;
    }

    public boolean isInteger(String s) throws DukeException{
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException | NullPointerException e) {
            throw new DukeException(printFatalMessage("Enter the numeric item index."));
        }
    }

    public String processAction(Action a, String[] s) {
        if (s.length == 1){
            return printProcessActionMessage(a.getLabel());
        }

        int idx;
        try {
            idx = getIndex(s[1]);
        }catch (DukeException e){
            return e.getMessage();
        }

        if(idx > arrayList.size()){
            return printProcessActionMessage("That is not a valid index.");
        }

        if (idx != -1){
            switch (a){
                case MARK:
                    arrayList.get(idx).markTask();
                    try{
                        saveFile();
                        return printSingle(idx, true);
                    }catch(IOException e){
                        return ui.sendGenericWarning("IOException");
                    }
                case UNMARK:
                    arrayList.get(idx).unmarkTask();
                    try{
                        saveFile();
                        return printSingle(idx, false);
                    }catch(IOException e){
                        return ui.sendGenericWarning("IOException");
                    }
                case DELETE:
                    Task copy = arrayList.get(idx);
                    arrayList.remove(idx);
                    try{
                        saveFile();
                        return printTaskRemovedByIndex(copy);
                    }catch(IOException e){
                        return ui.sendGenericWarning("IOException");
                    }
            }
        }
        return "";
    }

    private void processDue(Command c,String[] s) throws DukeException{
        long convertedTime;

        switch (c){
            case EVENT:
                String[] eventByInput = s[1].split("/at", 2);
                if(eventByInput.length ==1){
                    arrayList.add(new Event(eventByInput[0].trim(),new long[]{}));
                }else{
                    long[] convertedTimeRange;
                    try{
                        convertedTimeRange = DateProcessor.processDateTimeRange(eventByInput[1].trim());
                    }catch (DukeException e){
                        throw new DukeException(e.getMessage());
                    }
                    if (convertedTimeRange == null){
                        throw new DukeException("");
                    }
                    arrayList.add(new Event(eventByInput[0].trim(),convertedTimeRange));
                }
                break;
            case DEADLINE:
                String[] deadlineByInput = s[1].split("/by", 2);
                if(deadlineByInput.length ==1){
                    arrayList.add(new Deadline(deadlineByInput[0].trim(),0));
                }else{
                    try {
                        convertedTime = DateProcessor.processDateTime(deadlineByInput[1].trim());
                    }catch (DukeException e){
                        throw new DukeException(e.getMessage());
                    }
                    if (convertedTime == -1){
                        throw new DukeException("");
                    }
                    arrayList.add(new Deadline(deadlineByInput[0].trim(),convertedTime));
                }
                break;
            case TODO:
                String[] todoByInput = s[1].split("/by", 2);
                if(todoByInput.length ==1){
                    arrayList.add(new Todo(todoByInput[0].trim(),0));
                }else{
                    try {
                        convertedTime = DateProcessor.processDateTime(todoByInput[1].trim());
                    }catch (DukeException e){
                        throw new DukeException(e.getMessage());
                    }
                    if (convertedTime == -1){
                        throw new DukeException("");
                    }
                    arrayList.add(new Todo(todoByInput[0].trim(),convertedTime));
                }
                break;
        }
    }
    public String processAddTask(Command c, String[] s) {
        if (s.length == 1){
            return printProcessCommandMessage(c.getLabel());
        }

        if (s[1].trim().equals("")){
            return printProcessCommandMessage(c.getLabel());
        }

        try{
            processDue(c,s);
        } catch (DukeException e) {
            return e.getMessage();
        }

        try{
            saveFile();
        }catch(IOException e){
            return ui.sendGenericWarning("IOException");
        }
        return printNewTaskAdded();
    }
    public String restoreFile(int selection) throws IOException {
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
        Files.copy(fileToMove.toPath(), toReplace.toPath(),StandardCopyOption.REPLACE_EXISTING);
        TaskList t = new TaskList(new Scanner(new File(toReplace.toString())));
        return ui.sendGenericConfirmation("Successfully restored records. Rebooting.");
    }

    public boolean archiveFile() throws IOException {
        String userName = System.getProperty("user.name");
        long timeNow = Instant.now().getEpochSecond();
        String archiveFileName = "archive_" + userName + "_" + timeNow+".txt";
        String archivePath ="archives/";

        File fileToMove = new File("save/output.txt");
        return fileToMove.renameTo(new File(archivePath,archiveFileName));
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


    private long checkFindDate(String[] s) throws DukeException {
        if (s.length == 1){
            throw new DukeException(printProcessFindDateMessage());
        }

        if (s[1].trim().equals("")){
            throw new DukeException(printProcessFindDateMessage());
        }

        DateProcessor d = new DateProcessor();

        try {
            return DateProcessor.processDate(s[1]);
        }catch (DukeException e){
            throw new DukeException(e.getMessage());
        }
    }
    public String processFindDate(String[] s){
        long d;

        try {
            d = checkFindDate(s);
        }catch (DukeException e){
            return e.getMessage();
        }

        Task[] arr = {};
        ArrayList<Task> selected = new ArrayList<>(Arrays.asList(arr));

        for (Task t : this.arrayList){
            if (t.getDue() >= d && t.getDue() <= d + 86400){
                selected.add(t);
            }
        }
        selected.sort(new TaskComparator());
        return printSelectedList(selected,true,s[1]);
    }

    static class TaskComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2)
        {
            return Long.compare(t1.getDue(), t2.getDue());
        }
    }

    private String checkFindTask(String[] s) throws DukeException{
        if (s.length == 1){
            throw new DukeException(printProcessFindTaskMessage());
        }

        if (s[1].trim().equals("")){
            throw new DukeException(printProcessFindTaskMessage());
        }
        return s[1];
    }
    public String processFindTask(String[] s){
        String k;

        try {
            k = checkFindTask(s);
        }catch (DukeException e){
            return e.getMessage();
        }

        Task[] arr = {};
        ArrayList<Task> selected = new ArrayList<>(Arrays.asList(arr));

        for (Task t : this.arrayList){
            if (Arrays.asList(t.getDescription().split(" ")).contains(k)){
                selected.add(t);
            }
        }
        return printFoundList(selected,true,s[1]);
    }

    private String checkViewSchedule(String[] s)throws DukeException {
        if (s.length == 1){
            throw new DukeException(printProcessViewScheduleMessage());
        }

        if (s[1].trim().equals("")){
            throw new DukeException(printProcessViewScheduleMessage());
        }
        return s[1];
    }

    //TODO
    public String processViewSchedule(String[] s) {
        String d;

        try {
            d =checkViewSchedule(s);
        }catch (DukeException e){
            return e.getMessage();
        }
        long dayStartTS = DateProcessor.dateToUnix(d);
        long dayEndTS =dayStartTS + 86400;
        return "";
    }

    public String processArchive() {
        try{
            if (archiveFile()){
                arrayList.clear();
                return ui.sendGenericConfirmation("Successfully archived records.");
            }
        }catch(IOException e){
            return ui.sendGenericWarning("IOException");
        }
        return ui.sendGenericFatal("Failed to archive records.");
    }

    private long translateFileName(String fileName){
        String[] parsedFileName = fileName.split("_", 3);
        String[] parsedFileDate = parsedFileName[2].split("\\.", 2);
        return Long.parseLong(parsedFileDate[0]);
    }

    private String listFiles() {
        File folder = new File("archives/");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        int i = 1;
        StringBuilder s = new StringBuilder();
        s.append(ui.sendGenericInfo("Select from the files below by entering restore <index>."));
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                this.fileMap.put(i,listOfFile.getName());
                System.out.println(fileMap);
                s.append(ui.sendGenericPlain(i+ ". " + DateProcessor.unixToString(translateFileName(listOfFile.getName()))));
            }
            i++;
        }
        s.append(ui.sendGenericInfo("Chat will be refreshed after restoring."));

        try{
            cacheFile();
        }catch(IOException e){
            return ui.sendGenericWarning("IOException");
        }
        return String.valueOf(s);
    }

    public String processRestore(String[] s) {
        if (s.length == 1){
            return listFiles();
        }

        if (s[1].trim().equals("")){
            return printProcessViewScheduleMessage();
        }

        try{
            return restoreFile(Integer.parseInt(s[1]));
        }catch(IOException e){
            return ui.sendGenericFatal("Failed to restore records.");
        }
    }
}

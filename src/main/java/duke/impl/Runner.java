package duke.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.utils.DateProcessor;
import duke.utils.DukeException;

/**
 * Executor of actual processing logic.
 */
public class Runner {
    private static final String ARCHIVE_CACHE_DIR = "data/tmp/";
    private static final String ARCHIVE_CACHE_FILE_PATH = "data/tmp/archives.txt";

    private static final String SAVED_DIR = "data/save/";
    private static final String SAVED_FILE_PATH = "data/save/output.txt";
    private static final String ARCHIVE_DIR = "data/archives/";

    /**
     * Enums of task related commands
     */
    public enum Command {
        DEADLINE("deadline"), EVENT("event"),

        DATE("date"), TODO("todo");

        public final String label;

        Command(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

    /**
     * Enums of action related commands
     */
    public enum Action {
        MARK("mark"), UNMARK("unmark"), DELETE("delete");

        public final String label;

        Action(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

    protected Ui ui;
    protected boolean isExit;
    protected ArrayList<Task> arrayList;
    protected Map<Integer, String> fileMap = new HashMap<Integer, String>();

    /**
     * Initializes TaskList and IO of Runner object.
     *
     * @param a Task list to execute
     */
    public Runner(ArrayList<Task> a) {
        arrayList = a;
        ui = new Ui();
    }

    public void run(ArrayList<Task> tasks) throws DukeException {
        arrayList = tasks;
    }

    public String printList(Boolean withIndex) {
        return ui.printList(arrayList, withIndex);
    }

    private String printSelectedList(ArrayList<Task> l, Boolean withIndex, String date) {
        return ui.printSelectedList(l, withIndex, date);
    }

    private String printFoundList(ArrayList<Task> l, Boolean withIndex, String keyword) {
        return ui.printFoundList(l, withIndex, keyword);
    }

    private String printSingle(int index, Boolean isMark) {
        return ui.printMarkTask(arrayList.get(index).toString(), isMark);
    }

    private String printNewTaskAdded() {
        return ui.printNewTasks(arrayList.get(arrayList.size() - 1).toString(), arrayList.size());
    }

    private String printTaskRemovedByIndex(Task t) {
        return ui.printTaskRemovedByIndex(t.toString(), arrayList.size());
    }

    public String printExitMessage() {
        return ui.sendGoodbyeMessage();
    }

    public String printCommandUnknownMessage() {
        return ui.sendCommandUnknownError();
    }

    private String printFatalMessage(String message) {
        return ui.sendGenericFatal(message);
    }

    private String printWarningMessage(String message) {
        return ui.sendGenericWarning(message);
    }

    private String printProcessCommandMessage(String message) {
        return ui.sendProcessCommandError(message);
    }

    private String printProcessActionMessage(String message) {
        return ui.sendProcessActionError(message);
    }

    private String printProcessFindDateMessage() {
        return ui.sendProcessFindDateError();
    }

    private String printProcessFindTaskMessage() {
        return ui.sendProcessFindTaskError();
    }

    private String printProcessArchiveMessage() {
        return ui.sendGenericConfirmation("Successfully archived records.");
    }

    private String printProcessArchiveFailureMessage() {
        return ui.sendGenericFatal("Failed to archive records.");
    }

    private String printProcessRestoreMessage() {
        return ui.sendProcessRestoreError();
    }

    private String printIoException(String s) {
        return ui.sendGenericWarning(s);
    }

    private String printListFilesHeaderMessage() {
        return ui.sendGenericInfo("Select from the files below by entering restore <index>.\n");
    }

    private String printListFilesFooterMessage() {
        return ui.sendGenericInfo("Chat will be refreshed after restoring.");
    }

    private String getCurrentUserName() {
        return System.getProperty("user.name");
    }

    private long getCurrentTimeStamp() {
        return Instant.now().getEpochSecond();
    }

    private int getIndex(String s) throws DukeException {
        try {
            if (!isInteger(s)) {
                return -1;
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        int i = Integer.parseInt(s) - 1;
        if (i < 0 || i >= this.arrayList.size()) {
            throw new DukeException(
                    printWarningMessage("This is not a valid index. Choose from the "
                            + this.arrayList.size()
                            + " tasks."));
        }
        return i;
    }

    public boolean isInteger(String s) throws DukeException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            throw new DukeException(printFatalMessage("Enter the numeric item index."));
        }
    }

    public String processAction(Action a, String[] s) {
        if (s.length == 1) {
            return printProcessActionMessage(a.getLabel());
        }

        int idx;
        try {
            idx = getIndex(s[1]);
        } catch (DukeException e) {
            return e.getMessage();
        }

        if (idx != -1) {
            switch (a) {
            case MARK:
                arrayList.get(idx).markTask();
                try {
                    saveFile();
                    return printSingle(idx, true);
                } catch (IOException e) {
                    return printIoException(e.getMessage());
                }
            case UNMARK:
                arrayList.get(idx).unmarkTask();
                try {
                    saveFile();
                    return printSingle(idx, false);
                } catch (IOException e) {
                    return printIoException(e.getMessage());
                }
            case DELETE:
                Task copy = arrayList.get(idx);
                arrayList.remove(idx);
                try {
                    saveFile();
                    return printTaskRemovedByIndex(copy);
                } catch (IOException e) {
                    return printIoException(e.getMessage());
                }
            default:
                return ("Unknown Command");
            }
        }
        return "";
    }

    private void processDue(Command c, String[] s) throws DukeException {
        long convertedTime;

        switch (c) {
        case EVENT:
            String[] eventByInput = s[1].split("/at", 2);
            if (eventByInput.length == 1) {
                arrayList.add(new Event(eventByInput[0].trim(), new long[]{}));
            } else {
                long[] convertedTimeRange;
                try {
                    convertedTimeRange = DateProcessor.processDateTimeRange(eventByInput[1].trim());
                } catch (DukeException e) {
                    throw new DukeException(e.getMessage());
                }
                if (convertedTimeRange == null) {
                    throw new DukeException("");
                }
                arrayList.add(new Event(eventByInput[0].trim(), convertedTimeRange));
            }
            break;
        case DEADLINE:
            String[] deadlineByInput = s[1].split("/by", 2);
            if (deadlineByInput.length == 1) {
                arrayList.add(new Deadline(deadlineByInput[0].trim(), 0));
            } else {
                try {
                    convertedTime = DateProcessor.processDateTime(deadlineByInput[1].trim());
                } catch (DukeException e) {
                    throw new DukeException(e.getMessage());
                }
                if (convertedTime == -1) {
                    throw new DukeException("");
                }
                arrayList.add(new Deadline(deadlineByInput[0].trim(), convertedTime));
            }
            break;
        case TODO:
            String[] todoByInput = s[1].split("/by", 2);
            if (todoByInput.length == 1) {
                arrayList.add(new Todo(todoByInput[0].trim(), 0));
            } else {
                try {
                    convertedTime = DateProcessor.processDateTime(todoByInput[1].trim());
                } catch (DukeException e) {
                    throw new DukeException(e.getMessage());
                }
                if (convertedTime == -1) {
                    throw new DukeException("");
                }
                arrayList.add(new Todo(todoByInput[0].trim(), convertedTime));
            }
            break;
        default:
            throw new DukeException("Unknown Command");
        }
    }

    public String processAddTask(Command c, String[] s) {
        if (s.length == 1) {
            return printProcessCommandMessage(c.getLabel());
        }

        if (s[1].trim().equals("")) {
            return printProcessCommandMessage(c.getLabel());
        }

        try {
            processDue(c, s);
        } catch (DukeException e) {
            return e.getMessage();
        }

        try {
            saveFile();
        } catch (IOException e) {
            return printIoException(e.getMessage());
        }
        return printNewTaskAdded();
    }

    public void restoreFile(int selection) throws IOException {
        String path = "";
        Files.createDirectories(Paths.get(ARCHIVE_CACHE_DIR));
        Scanner scn = new Scanner(new File(ARCHIVE_CACHE_FILE_PATH));
        while (selection > 0 && scn.hasNext()) {
            path = scn.nextLine();
            selection--;
        }
        Files.createDirectories(Paths.get(ARCHIVE_DIR));
        Files.createDirectories(Paths.get(SAVED_DIR));
        File fileToMove = new File(ARCHIVE_DIR + path);
        File toReplace = new File(SAVED_FILE_PATH);
        Files.copy(fileToMove.toPath(), toReplace.toPath(), StandardCopyOption.REPLACE_EXISTING);
        TaskList t = new TaskList(new Scanner(new File(toReplace.toString())));
    }

    public boolean archiveFile() throws IOException {
        String archiveFileName = "archive_" + getCurrentUserName() + "_" + getCurrentTimeStamp() + ".txt";
        File fileToMove = new File(SAVED_FILE_PATH);
        Files.createDirectories(Paths.get(ARCHIVE_DIR));
        return fileToMove.renameTo(new File(ARCHIVE_DIR, archiveFileName));
    }

    public void saveFile() throws IOException {
        Files.createDirectories(Paths.get(SAVED_DIR));
        FileWriter writer = new FileWriter(SAVED_FILE_PATH);
        writer.write(getCurrentUserName() + System.lineSeparator());
        writer.write(getCurrentTimeStamp() + System.lineSeparator());

        for (Task str : arrayList) {
            String s = str.getType()
                    + "," + str.getIsDone()
                    + "," + str.getDescription()
                    + "," + str.getDue()
                    + "," + str.getTo();
            writer.write(s + System.lineSeparator());
        }
        writer.close();
    }

    public void cacheFile() throws IOException {
        Files.createDirectories(Paths.get(ARCHIVE_CACHE_DIR));
        FileWriter writer = new FileWriter(ARCHIVE_CACHE_FILE_PATH);

        for (Map.Entry<Integer, String> m : fileMap.entrySet()) {
            String s = m.getValue();
            writer.write(s + System.lineSeparator());
        }
        writer.close();
        File f = new File(ARCHIVE_CACHE_FILE_PATH);
        f.deleteOnExit();
    }


    private long checkFindDate(String[] s) throws DukeException {
        if (s.length == 1) {
            throw new DukeException(printProcessFindDateMessage());
        }

        if (s[1].trim().equals("")) {
            throw new DukeException(printProcessFindDateMessage());
        }

        try {
            return DateProcessor.processDate(s[1]);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public String processFindDate(String[] s) {
        long d;

        try {
            d = checkFindDate(s);
        } catch (DukeException e) {
            return e.getMessage();
        }

        Task[] arr = {};
        ArrayList<Task> selected = new ArrayList<>(Arrays.asList(arr));

        for (Task t : this.arrayList) {
            if (t.getDue() >= d && t.getDue() <= d + 86400) {
                selected.add(t);
            }
        }
        selected.sort(new TaskComparator());
        return printSelectedList(selected, true, s[1]);
    }

    static class TaskComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2) {
            return Long.compare(t1.getDue(), t2.getDue());
        }
    }

    private String checkFindTask(String[] s) throws DukeException {
        if (s.length == 1) {
            throw new DukeException(printProcessFindTaskMessage());
        }

        if (s[1].trim().equals("")) {
            throw new DukeException(printProcessFindTaskMessage());
        }
        return s[1];
    }

    public String processFindTask(String[] s) {
        String k;

        try {
            k = checkFindTask(s);
        } catch (DukeException e) {
            return e.getMessage();
        }

        Task[] arr = {};
        ArrayList<Task> selected = new ArrayList<>(Arrays.asList(arr));

        for (Task t : this.arrayList) {
            if (Arrays.asList(t.getDescription().split(" ")).contains(k)) {
                selected.add(t);
            }
        }
        return printFoundList(selected, true, s[1]);
    }

    public String processArchive() {
        try {
            if (archiveFile()) {
                arrayList.clear();
                return printProcessArchiveMessage();
            }
        } catch (IOException e) {
            return printIoException(e.getMessage());
        }
        return printProcessArchiveFailureMessage();
    }

    private long translateFileNameToDateTime(String fileName) {
        String[] parsedFileName = fileName.split("_", 3);
        String[] parsedFileDate = parsedFileName[2].split("\\.", 2);
        return Long.parseLong(parsedFileDate[0]);
    }

    private String listFiles() throws IOException {
        Files.createDirectories(Paths.get(ARCHIVE_DIR));
        File folder = new File(ARCHIVE_DIR);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        int i = 1;
        StringBuilder s = new StringBuilder();
        s.append(printListFilesHeaderMessage());
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile() && !listOfFile.getName().equals(".gitkeep")) {
                this.fileMap.put(i, listOfFile.getName());
                s.append(ui
                        .sendGenericPlain(i + ". "
                                + DateProcessor.unixToString(translateFileNameToDateTime(listOfFile.getName()))));
                i++;
            }
        }
        s.append(printListFilesFooterMessage());

        try {
            cacheFile();
        } catch (IOException e) {
            return printIoException(e.getMessage());
        }
        return String.valueOf(s);
    }

    public String processRestore(String[] s) {
        try {
            if (s.length == 1) {
                return listFiles();
            }
        } catch (IOException e) {
            return printIoException("failed to retrieve archive records.");
        }

        if (s[1].trim().equals("")) {
            return printProcessRestoreMessage();
        }

        try {
            restoreFile(Integer.parseInt(s[1]));
            return "";
        } catch (IOException e) {
            return printIoException("failed to restore records");
        }
    }
}

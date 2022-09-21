public class Task {
    protected static String description = "";
    protected boolean isDone;
    public  String[] split;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void addTask (String input) throws DukeException {
        String des = "";
        String date = "";
        int dateIdx = 0;
        Duke.taskListCount++;

        String[] split = Duke.userInput.split(" ");
        for (int i=1; i<split.length; i++) {
            if (split[i].startsWith("/")) {
                dateIdx = i;
                break;
            }
            des = des + split[i] + " ";
        }

       if (des == "") {
           throw new DukeException();
       }

        for (int j=dateIdx+1; j<split.length; j++) {
            date = date + split[j] + " ";
        }

        switch (split[0]) {
            case "todo":
                Duke.taskList[Duke.taskListCount-1] = new ToDo(des);
                break;
            case "deadline":
                Duke.taskList[Duke.taskListCount-1] = new Deadline(des, date);
                break;
            case "event":
                Duke.taskList[Duke.taskListCount-1] = new Event(des, date);
                break;
            default:
                throw new DukeException();
        }
    }

    public static void list() {
        System.out.println(Duke.line);
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < Duke.taskListCount; i++) {
            System.out.println(i+1 + ". " + Duke.taskList[i].toString());
        }
        System.out.println(Duke.line);
    }

    public static void markTask() {
        try {
            String[] split = Duke.userInput.split(" ");
            int taskId = (Integer.parseInt(split[1])) - 1;
            if (Duke.userInput.contains("un")) {
                Duke.taskList[taskId].markAsNotdone();
            }
            else {
                Duke.taskList[taskId].markAsDone();
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Something went wrong. Please enter a valid operation. E.g. mark 1");
        }
    }

    public String getTask() {
        return this.description;
    }

    public void markAsNotdone() { isDone = false;
        System.out.println(Duke.line + "OK, I've marked this task as not done yet.");
        System.out.println("[ ] " + description + Duke.line);
    }

    public void markAsDone() {
        isDone = true;
        System.out.println(Duke.line + "Nice! I've marked this task as done:");
        System.out.println("[X] " + description + Duke.line);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

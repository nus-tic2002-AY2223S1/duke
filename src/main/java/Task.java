import java.io.*;

abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String myTaskType;

    //constructor
    public Task(String description, String TaskType) {
        this.description = description;
        this.myTaskType = TaskType;
    }

    private void markTaskFromFile(String filePath) throws IOException {
        //replace the task in the file to true
        File inputFile = new File(filePath);
        String originalContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine ;
        String lineToRemove = this.myTaskType + " | " + this.isDone + " | " + this.description;

        while((currentLine = reader.readLine()) != null) {//read current line from tasks.txt till it null
            originalContent = originalContent + currentLine + System.lineSeparator();
        }
        String updateContent = originalContent.replace("false", "true");

        FileWriter writer = new FileWriter(inputFile);
        writer.write(updateContent);
        writer.close();
        reader.close();
    }

    private void unmarkTaskFromFile(String filePath) throws IOException {
        //replace the task in the file to true
        File inputFile = new File(filePath);
        String originalContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine ;
        String lineToRemove = this.myTaskType + " | " + this.isDone + " | " + this.description;

        while((currentLine = reader.readLine()) != null) {//read current line from tasks.txt till it null
            originalContent = originalContent + currentLine + System.lineSeparator();
        }
        String updateContent = originalContent.replace("true", "false");

        FileWriter writer = new FileWriter(inputFile);
        writer.write(updateContent);
        writer.close();
        reader.close();
    }

    public void markAsDone() throws IOException {
        //mark true in tasks.txt
        markTaskFromFile("data/tasks.txt");

        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println("[X] " + description);
    }

    public void markAsNotDone() throws IOException {
        //mark false in tasks.txt
        unmarkTaskFromFile("data/tasks.txt");

        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
        System.out.println("[] " + description);
    }

    //get method
    public String getTaskTypeIcon() {
        return myTaskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString(){
        return "[" + getStatusIcon() + "] "  + description;
    }
}

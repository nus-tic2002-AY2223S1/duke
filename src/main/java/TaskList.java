import java.io.*;
import java.util.Scanner;

public class TaskList {
    public int task_count;
    public Task[] myTaskList;

    // //constructor reading a existing file
    TaskList(){
        task_count = 0;
        myTaskList = new Task[100];
    }

    TaskList(String fileContent) throws DukeException, IOException {
        task_count = 0;
        myTaskList = new Task[100];

        Scanner scan = new Scanner(fileContent);
        //add everyLine to myTaskList
        while(scan.hasNextLine()){
            updateTasks(scan.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }
    private static void removeLineFromFile(String filePath, String textToRemove) throws  IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("data/tasks_temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        String lineToRemove = textToRemove.trim();

        while((currentLine = reader.readLine()) != null) {//read current line from tasks.txt till it null
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)){
                continue;
            }
            writer.write(currentLine); //write current line to temp file
        }
        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void addTasks(String input) throws DukeException, IOException {
        String[] words = input.split(" ");
        String description = "";

        int dateDivider = 0;

        for(int i = 1; i < words.length; i++){
            if(words[i].startsWith("/")){
                dateDivider = i;
                break;
            }
            description = description + words[i] + " ";
        }

        String date = "";

        for(int i = dateDivider + 1; i < words.length; i++){
            date = date + words[i] + " ";
        }

        if (description.equals("")){
            throw new DukeException();
        }

        switch (words[0]) {
            case "deadline":
                myTaskList[task_count] = new Deadline(description, "deadline", date);
                break;
            case "event":
                myTaskList[task_count] = new Event(description, "event", date);
                break;
            case "todo":
                myTaskList[task_count] = new ToDo(description, "todo");
                break;
            default:
                throw new DukeException();
        }

        String file = "data/tasks.txt";
        String data = myTaskList[task_count].myTaskType + " | " +
                        myTaskList[task_count].isDone + " | " +
                        myTaskList[task_count].description;

        //write to file
        try{
            appendToFile(file,data);
        } catch (IOException e){
            writeToFile(file, data);
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ++task_count;
    }

    public void removeTasks(int taskID) throws DukeException, IOException {
        try {
            //delete the task in tasks.txt
            String file = "data/tasks.txt";
            String LineToRemove = myTaskList[taskID].myTaskType + " | " +
                    myTaskList[taskID].isDone + " | " +
                    myTaskList[taskID].description;
            removeLineFromFile(file, LineToRemove);

            for (int i = taskID; i < task_count; i++) { //replace the task with the task in front of it
                myTaskList[i] = myTaskList[i + 1];
            }
            myTaskList[task_count] = null; //initialize the last task to null

            //reduce the task count
            --task_count;
        } catch (NullPointerException e){
            System.out.println("TaskList is empty");
        }
    }

    public void updateTasks(String input) throws DukeException, IOException {
        String[] words = input.split(" ");
        String description = "";

        int dateDivider = 0;

        for(int i = 1; i < words.length; i++){
            if(words[i].startsWith("/")){
                dateDivider = i;
                break;
            }
            if(words[i].startsWith("|") || words[i].startsWith("false") || words[i].startsWith("true")){
                continue;
            }
            description = description + words[i] + " ";
        }

        String date = "";

        for(int i = dateDivider + 1; i < words.length; i++){
            date = date + words[i] + " ";
        }

        switch (words[0]) {
            case "deadline":
                myTaskList[task_count] = new Deadline(description, "deadline", date);
                break;
            case "event":
                myTaskList[task_count] = new Event(description, "event", date);
                break;
            case "todo":
                myTaskList[task_count] = new ToDo(description, "todo");
                break;
            default:
                throw new DukeException();
        }
        ++task_count;
    }


    public void listTasks(){
        for(int i = 0; i < task_count; ++i){
            System.out.println((i+1) + ". " + myTaskList[i].toString());
        }
    }
}

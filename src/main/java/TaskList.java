import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class TaskList
{
    public int task_count;
    public Task[] myTaskList;

    // //constructor reading a existing file
    TaskList(){
        task_count = 0;
        myTaskList = new Task[100];
    }

    TaskList(String fileContent) throws DukeException
    {
        task_count = 0;
        myTaskList = new Task[100];

        Scanner scan = new Scanner(fileContent);
        //add everyLine to myTaskList
        while(scan.hasNextLine()){
            updateTasks(scan.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException
    {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException
    {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }
    private static void removeLineFromFile(String filePath, String textToRemove) throws  IOException
    {
        File inputFile = new File(filePath);
        File tempFile = new File("data/tasks_temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        String lineToRemove = textToRemove.trim();

        while((currentLine = reader.readLine()) != null)
        {//read current line from tasks.txt till it null
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)){
                continue;
            }
            writer.write(currentLine + System.lineSeparator()); //write current line to temp file
        }
        writer.close();
        reader.close();

        System.gc();

        inputFile.delete();

        tempFile.renameTo(inputFile);
    }

    public boolean IsFreeTime(LocalDate existingSchedule, LocalDate newSchedule)
    {
        if(newSchedule.isEqual(existingSchedule)){
            return false;
        }
        else {
            return true;
        }
    }

    public void addTasks(String input) throws DukeException, IOException
    {
        String[] words = input.split(" ");
        String description = "";

        int dateDivider = 0;

        for(int i = 1; i < words.length; i++)
        {
            if(words[i].startsWith("/")){
                dateDivider = i;
                break;
            }
            description = description + words[i] + " ";
        }

        String date = "";

        for(int i = dateDivider + 1; i < words.length; i++)
        {
            date = date + words[i] + " ";
        }

        if (description.equals(""))
        {
            throw new DukeException();
        }

        //add task based on case, check if the tasks clash with an existing task, for event and todo
        switch (words[0])
        {
            case "deadline":
                myTaskList[task_count] = new Deadline(description, "deadline", date);
                //check date, if clash
                int i = 0;
                boolean free_time = true;
                while (i < task_count){ //new task compare with old task, only if the task is a D or E/ if match delete the new task
                    if(myTaskList[i].myTaskType.equals("todo"))
                    {
                        ++i;
                        continue;
                    }
                    free_time = IsFreeTime(myTaskList[task_count].my_TaskDate, myTaskList[i].my_TaskDate);
                    ++i;
                }
                if(!free_time)
                {
                    myTaskList[task_count] = myTaskList[task_count + 1];
                }
                break;

            case "event":
                myTaskList[task_count] = new Event(description, "event", date);
                //check date, if clash
                i = 0;
                free_time = true;
                while (i < task_count){ //new task compare with old task, only if the task is a D or E/ if match delete the new task
                    if(myTaskList[i].myTaskType.equals("todo"))
                    {
                        ++i;
                        continue;
                    }
                    free_time = IsFreeTime(myTaskList[task_count].my_TaskDate, myTaskList[i].my_TaskDate);
                    ++i;
                 }
                 if(!free_time)
                 {
                      myTaskList[task_count] = myTaskList[task_count + 1];
                 }
                 break;

            case "todo":
                myTaskList[task_count] = new ToDo(description, "todo");
                break;
            default:
                throw new DukeException();
        }

        //write and append only if the task did not clash

        String file = "data/tasks.txt";
        String data = myTaskList[task_count].myTaskType + " | " +
                        myTaskList[task_count].isDone + " | " +
                        myTaskList[task_count].description;

        // to update for event and deadline return book /by Sunday
        if (myTaskList[task_count].myTaskType.equals("event") || myTaskList[task_count].myTaskType.equals("deadline"))
        {
            data = data + " | " + date;
        }
        //write to file
        try
        {
            appendToFile(file,data);
        } catch (IOException e)
        {
            writeToFile(file, data);
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ++task_count;
    }

    public void removeTasks(int taskID) throws DukeException, IOException
    {
        try
        {
            //delete the task in tasks.txt
            String file = "data/tasks.txt";
            String LineToRemove = myTaskList[taskID].myTaskType + " | " +
                    myTaskList[taskID].isDone + " | " +
                    myTaskList[taskID].description;

            // to update for event and deadline return book /by Sunday
            if (myTaskList[taskID].myTaskType.equals("event") || myTaskList[taskID].myTaskType.equals("deadline"))
            {
                LineToRemove = LineToRemove + "| " + myTaskList[taskID].my_td;
            }

            removeLineFromFile(file, LineToRemove);

            for (int i = taskID; i < task_count; i++)
            { //replace the task with the task in front of it
                myTaskList[i] = myTaskList[i + 1];
            }
            myTaskList[task_count] = null; //initialize the last task to null

            //reduce the task count
            --task_count;
        } catch (NullPointerException e)
        {
            System.out.println("TaskList is empty");
        }
    }

    public void updateTasks(String input) throws DukeException
    {
        String[] words = input.split(" ");
        String description = "";

        int dateDivider = 0;
        int dateCounter = 0;

        for(int i = 1; i < words.length; i++)
        {
            if(dateCounter == 3)
            {
                break;
            }
            if(words[i].startsWith("|") || words[i].startsWith("false") || words[i].startsWith("true"))
            {
                if(words[i].startsWith("|"))
                {
                    dateCounter++;
                }
                dateDivider++;
                continue;
            }
            description = description + words[i] + " ";
            dateDivider++;
        }

        String date = "";

        for(int i = dateDivider + 1; i < words.length; i++)
        {
            date = date + words[i] + " ";
        }

        switch (words[0])
        {
            case "deadline":
                myTaskList[task_count] = new Deadline(description, "deadline", date);
                myTaskList[task_count].setIsDone(Boolean.parseBoolean(words[2]));
                break;
            case "event":
                myTaskList[task_count] = new Event(description, "event", date);
                myTaskList[task_count].setIsDone(Boolean.parseBoolean(words[2]));
                break;
            case "todo":
                myTaskList[task_count] = new ToDo(description, "todo");
                myTaskList[task_count].setIsDone(Boolean.parseBoolean(words[2]));
                break;
            default:
                throw new DukeException();
        }
        ++task_count;
    }


    public void listTasks()
    {
        for(int i = 0; i < task_count; ++i)
        {
            System.out.println((i+1) + ". " + myTaskList[i].toString());
        }
    }

    public void findTasks(String input)
    {
        String[] words = input.split(" ");
        String key = "";
        int noOfFind = 1;

        for(int i = 1; i < words.length; i++)
        {
            key = key + words[i] + " ";
        }

        for(int i = 0; i < task_count; ++i)
        {
            if(myTaskList[i].description.contains(key.trim())){ //if line contain the keyword print else continue
                System.out.println(noOfFind + ". " + myTaskList[i].toString());
                ++noOfFind;
            }
        }
    }
    public void viewSchedule()
    {
        //user to input a Local Date
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Date in the format, YYYY-MM-DD");

        String date = scanner.next();

        LocalDate userInputDate = LocalDate.parse(date);

        //print out a list of task with matching date
        int noOfFind = 1;
        for(int i = 0; i < task_count; ++i){
            if(!myTaskList[i].myTaskType.equals("todo")){
                if(myTaskList[i].my_TaskDate.equals(userInputDate)){
                    System.out.println(noOfFind + ". " + myTaskList[i].toString());
                    ++noOfFind;
                }
            }
        }
    }
}

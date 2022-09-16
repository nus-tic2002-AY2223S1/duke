public class Tasks{
    public int task_count;
    public Task[] myTaskList;

    public Tasks(){
        task_count = 0;
        myTaskList = new Task[100];
    }

    public void addTasks(String input) throws NoDescriptionException, UnknownCommandException {
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
            throw new NoDescriptionException();
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
                throw new UnknownCommandException();
        }
        ++task_count;
    }

    public void listTasks(){
        for(int i = 0; i < task_count; ++i){
            System.out.println((i+1) + ". " + myTaskList[i].toString());
        }
    }
}

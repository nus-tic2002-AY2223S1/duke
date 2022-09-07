public class Tasks{
    protected Task[] taskList = new Task[100];
    int task_count = 0;

    public void addTasks(String input){
        taskList[task_count] = new Task(input);
        ++task_count;
    }

    public void listTasks(){
        for(int i = 0; i < task_count; ++i){
            System.out.println((i+1) + ". " + "[" +taskList[i].getStatusIcon() + "]" +" " + taskList[i].description);
        }
    }
}

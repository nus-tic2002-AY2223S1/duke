public class Tasks {
    public int task_count;
    public Task[] mytaskList = new Task[100];

    public Tasks(){
        task_count = 0;
    }

    public void addTasks(String input){
        mytaskList[task_count] = new ToDo(input);
        ++task_count;
    }

    public void listTasks(){
        for(int i = 0; i < task_count; ++i){
            System.out.println((i+1) + ". " + mytaskList[i].toString());
        }
    }
}

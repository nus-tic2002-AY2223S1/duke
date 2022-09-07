class Tasks{
    protected String[] toDo = new String[100];
    int task_count = 0;

    public void addTasks(String input){
        System.out.println("added: ");

        toDo[task_count] = input;
        ++task_count;
    }

    public void listTasks(){
        for(int i = 0; i < task_count; ++i){
            System.out.println((i+1) + ". " + toDo[i]);
        }
    }
}

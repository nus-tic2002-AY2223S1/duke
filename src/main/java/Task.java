public class Task {
    protected String description;
    protected boolean isDone;
    

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTask(){
        return description;
    }

    public void mark(){
        isDone=true;
        System.out.println(Duke.print("Nice! I've marked this task as done:\n [" + getStatusIcon() + "] " + getTask() + "\n"));
    }
    public void unmark(){
        isDone=false;
        System.out.println(Duke.print("OK, I've marked this task as not done yet:\n [" + getStatusIcon() + "] " + getTask() + "\n"));
    }
    public String toString(){
	return ("["+getStatusIcon()+"] "+description);	
    }
    public String encapTask(String t, int count){
	return ("Got it. I've added this task:\n "+ t +"Now you have "+count+" tasks in the list.\n");
    }

    public static void list(){
        String itemList="";
        for (int i = 0; i < Duke.countTask; i++) {
            //itemList += "(i + 1) + ".["+task[i].getStatusIcon()+"] " + task[i].getTask() + "\n";
            itemList += (i + 1) + "." + Duke.task[i].toString();

        }
        System.out.println(Duke.print(itemList));
        itemList="";
    }

    public static void addTask(String taskType,String fullInput) throws DukeException{
        int indexSlash= fullInput.indexOf('/');
        int indexSpace= fullInput.indexOf(' ');
        String detail;
        String due;

        if(taskType.equals("deadline")){
            if(indexSlash==-1 || indexSpace==-1){
                throw new DukeException();
            }
            detail=fullInput.substring(indexSpace, indexSlash - 1);
            due=fullInput.substring(indexSlash + 4);

            Duke.task[Duke.countTask] = new Deadline(detail, due);
            System.out.println(Duke.print(Duke.task[Duke.countTask].encapTask(Duke.task[Duke.countTask].toString(), Duke.countTask + 1)));

        }
        else if(taskType.equals("todo")){
            if(indexSpace==-1){
                throw new DukeException();
            }
            detail = fullInput.substring(indexSpace);

            Duke.task[Duke.countTask] = new Todo(detail);
            System.out.println(Duke.print(Duke.task[Duke.countTask].encapTask(Duke.task[Duke.countTask].toString(), Duke.countTask + 1)));

        }
        else{
            if(indexSlash==-1 || indexSpace==-1){
                throw new DukeException();
            }
            detail=fullInput.substring(indexSpace, indexSlash - 1);
            due=fullInput.substring(indexSlash + 4);

            Duke.task[Duke.countTask] = new Event(detail, due);
            System.out.println(Duke.print(Duke.task[Duke.countTask].encapTask(Duke.task[Duke.countTask].toString(), Duke.countTask + 1)));

        }

    }



}
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
        for (int i = 0; i < Duke.task.size(); i++) {
            //itemList += "(i + 1) + ".["+task[i].getStatusIcon()+"] " + task[i].getTask() + "\n";
            itemList += (i + 1) + "." + Duke.task.get(i).toString();

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
            detail=fullInput.substring(indexSpace+1, indexSlash - 1);
            due=fullInput.substring(indexSlash + 4);

            Duke.task.add(new Deadline(detail, due));


        }
        else if(taskType.equals("todo")){
            if(indexSpace==-1){
                throw new DukeException();
            }
            detail = fullInput.substring(indexSpace+1);

            Duke.task.add(new Todo(detail));

        }
        else{
            if(indexSlash==-1 || indexSpace==-1){
                throw new DukeException();
            }
            detail=fullInput.substring(indexSpace+1, indexSlash - 1);
            due=fullInput.substring(indexSlash + 4);

            Duke.task.add(new Event(detail, due));


        }
        System.out.println(Duke.print(Duke.task.get(Duke.task.size()-1).encapTask(Duke.task.get(Duke.task.size()-1).toString(), Duke.task.size() )));

    }

    public static void addTask(String[] readInput){
        if(readInput[0].equals("T")){
            Duke.task.add(new Todo(readInput[2]));

        }
        else if(readInput[0].equals("E")){
            Duke.task.add(new Event(readInput[2],readInput[3]));

        }
        else{
            Duke.task.add(new Deadline(readInput[2],readInput[3]));

        }

        if(readInput[1].equals("1")){
            Duke.task.get(Duke.task.size()-1).isDone=true;

        }



    }
    public static void deleteTask(int taskID){
        System.out.println(Duke.print("Noted. I've removed this task:\n " +Duke.task.get(taskID).toString()+"Now you have "+ (taskID+1)+" tasks in the list.\n"));

        if(taskID!=Duke.task.size()-1) {

            for (int i = taskID; i < Duke.task.size()-1; i++) {
                Duke.task.set(i,Duke.task.get(i+1));
            }

        }
        Duke.task.remove(Duke.task.size()-1);

    }

    public static String toSave(){
        String saveContent="";


        for(int i=0;i<Duke.task.size();i++){
            String currentTask=Duke.task.get(i).toString();
            String mark="0";

            if(currentTask.substring(4,5).equals("X")){
                mark="1";
            }

//            System.out.println(currentTask);
//            System.out.println(currentTask.substring(1,2));
//            System.out.println(mark);
//            System.out.println(Duke.task.get(i).description);
//            System.out.println(currentTask.substring(indexDue+5,currentTask.length()-2));
            if(currentTask.substring(1,2).equals("T")){
                saveContent+=currentTask.substring(1,2)+" | "+mark+" | "+Duke.task.get(i).description+"\n";

            }
            else{
                int indexDue=currentTask.indexOf("(");
                saveContent+=currentTask.substring(1,2)+" | "+mark+" | "+Duke.task.get(i).description+" | "+currentTask.substring(indexDue+5,currentTask.length()-2)+"\n";
            }

        }
        return saveContent;
    }


}
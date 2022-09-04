public class Task extends AddList {
      protected String description;
      protected boolean isDone;

      public Task(String description){
          super(description);
          this.description=description;
          this.isDone=false;
      }

      public String getStatusIcon(){
          return (isDone ? "x":" "); //mark done task with X
      }

      public void markAsDone(){

           if(description.startsWith("mark")){

               System.out.println("Nice! I've marked this task as done:");
           }
           if(description.startsWith("unmark")){
               System.out.println("Ok, I've marked this task as not done yet:");
           }
      }

}

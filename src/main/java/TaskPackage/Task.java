package TaskPackage;

public class Task {

      protected String description;

      protected boolean isDone;

      public Task(String description){
          this.description = description;
          this.isDone = false;

      }

      public String getStatusIcon(){
          return (isDone ? "x":" "); //mark done task with X
      }

    /**
     * the method return true if task done
     */
    public void markAsDone(){
          this.isDone = true;
      }

    /**
     * the method return false if task not done
     */
      public void markAsNotdone(){
        this.isDone = false;
    }


    public String getData(){
          if (this.isDone) {
              return ("|1|" + this.description);
          } else {
              return ("|0|" + this.description);
          }
      }
      public String toString(){
          return "[" + getStatusIcon() + "]" + this.description;
      }

}

package task;


import static utility.Method.DateTime;


/**
 * deadline task is handle all task with "deadline"
 */
public class Deadline extends Task {
        private String by;

    public Deadline(String description,String by){
        super(description);
        this.by = by;
    }
      public String getData() {
          return "D" + super.getData() + "|" + by ;
      }
      public String toString()  {
          return "[D]" + super.toString() + " (by: " + DateTime(by) + ")";
      }

}

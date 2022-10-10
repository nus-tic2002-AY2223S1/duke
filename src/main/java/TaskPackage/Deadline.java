package TaskPackage;

public class Deadline extends Task {
      protected String by;

      public Deadline(String description, String by) {
          super(description);
          this.by = by;
      }

      public String getData() {
          return "D" + super.getData() + "|" + by ;
      }

      public String toString() {
          return "[D]" + super.toString() + " (by: " + by + ")";
          }


}

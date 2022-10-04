import Helpers.CommonHelper;

public class Deadline extends Task{
    public String dueDateTime;
    protected String shortName = "D";

    public Deadline(String n) {
        super(n);
        String[] f = CommonHelper.formatPassedName(n);
        this.name = f[0];
        this.dueDateTime = f[1];
    }

    @Override
    public void printItem(Integer n){
        String displayText = String.format("\t\t%d.[%s][%s] %s (by: %s)", n, this.shortName, this.isDone ? "X":" ", this.name, this.dueDateTime);
        CommonHelper.printMessage(displayText);
    }

    public String getDueDateTime(){
        return this.dueDateTime;
    }
    public void setDueDateTime(String t){
        this.dueDateTime = t;
    }
}

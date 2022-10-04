import Helpers.CommonHelper;

public class Event extends Task{
    protected String startDateTime;
    protected String shortName = "E";

    public Event(String n) {
        super(n);
        String[] f = CommonHelper.formatPassedName(n);
        this.name = f[0];
        this.startDateTime = f[1];
    }

    @Override
    public void printItem(Integer n){
        String displayText = String.format("\t\t%d.[%s][%s] %s (at: %s)", n, this.shortName, this.isDone ? "X":" ", this.name, this.startDateTime);
        CommonHelper.printMessage(displayText);
    }

    public String getStartDateTime(){
        return this.startDateTime;
    }
    public void setStartDateTime(String t){
        this.startDateTime = t;
    }
}

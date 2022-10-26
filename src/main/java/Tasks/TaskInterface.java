package Tasks;

public interface TaskInterface {
    public String getString();
    public boolean isDone();
    public void markTask(boolean mark);
    public String getWork();

    public long getID();
    public String getEndDate();
}

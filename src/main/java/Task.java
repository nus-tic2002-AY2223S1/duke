public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        String mark = this.getStatus() ? "[X]" : "[ ]";
        return mark + " " + description;
    }

    public boolean getStatus(){
        return isDone;
    }

    public void setStatus(boolean b){
        isDone = b;
    }
}
public class Task {
    protected String name;
    protected boolean status = false;

    public Task(String name) {
        this.name = name;
    }

    public void setStatus (boolean status) {
        this.status = status;
    }

    public String getTask() {
        if(status) {
            return "[X] " + name;
        }
        else {
            return "[ ] " + name;
        }
    }
}

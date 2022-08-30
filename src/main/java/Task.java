public class Task {

    private String description = "";
    private int type = 0;
    private boolean isDone = false;

    public Task(String name) {
        this.description = name;
    }

    public Task(String name, int type) {
        this.description = name;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (type >= 0) {
            this.type = type;
        }
    }

    public void updateStatus(boolean isDone) {
        this.isDone = isDone;
        if (isDone) {
            PrintUtil.printWithIndentation("%s%s%s", "\t Nice! I've marked this task as done:\n\t   [X] " + description + "\n");
        } else {
            PrintUtil.printWithIndentation("%s%s%s", "\t OK, I've marked this task as not done yet:\n\t   [ ] " + description + "\n");
        }
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}

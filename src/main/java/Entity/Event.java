package Entity;

public class Event extends Task{
    protected String at;
//    protected String time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
//        this.time = time;
    }

    @Override
    public void print(){
//        String description = String.format("\t[E][%s] %s (at: %s %s) ",this.getStatusIcon(),this.description,by);
        System.out.println(getDescription());
    }

    @Override
    public String toDisk() {
//        E | 0 | project meeting | Aug 6th 2-4pm
        return type + " | " + ((isDone) ? "1" : "0") + " | " + description + " | "+at;
    }

    @Override
    public String getDescription() {
        String description = String.format("\t [%s][%s] %s (at: %s) ",this.type,this.getStatusIcon(),this.description,at);
        return description;
    }
}

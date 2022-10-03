package Entity;

public class Event extends Task{
    protected String at;
//    protected String time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
//        this.time = time;
    }

    @Override
    public void print(){
//        String description = String.format("\t[E][%s] %s (at: %s %s) ",this.getStatusIcon(),this.description,by);
        System.out.println(getDescription());
    }

    @Override
    public String getDescription() {
        String description = String.format("\t [E][%s] %s (at: %s) ",this.getStatusIcon(),this.description,at);
        return description;
    }
}

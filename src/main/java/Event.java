public class Event extends Task{
	String schedule;
	public Event(String description,String schedule){
		super(description);
		this.schedule=schedule;
	}

	@Override
	public String toString(){
		return "[E]" + super.toString() + " (at: " + schedule + ")\n";
	}

}
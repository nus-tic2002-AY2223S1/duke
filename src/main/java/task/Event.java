package task;


public class Event extends Task{
	protected String schedule;
	public Event(String description,String schedule){
		super(description);
		this.schedule=schedule;
	}

	@Override
	public String toOutput(){
		String statusIndex="0";
		if (getStatusIcon().equals("X")){
			statusIndex="1";
		}
		return "E | "+statusIndex+" | "+super.toOutput()+" | "+schedule;
	}

	public String toString() {
		return "[E]"+super.toString()+" (at: "+schedule+")";
	}

}
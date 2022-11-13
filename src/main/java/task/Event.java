package task;


public class Event extends Task{
	protected String schedule;
	/**
	 * Event task constructor
	 */
	public Event(String description,String schedule){
		super(description);
		this.schedule=schedule;
	}
	/**
	 * Get date time
	 */
	public String getDateTime(){

		return schedule;
	}
	/**
	 * simplify to store in text file
	 */
	@Override
	public String toOutput(){
		String statusIndex="0";
		if (getStatusIcon().equals("X")){
			statusIndex="1";
		}
		return "E | "+statusIndex+" | "+super.toOutput()+" | "+schedule;
	}
	/**
	 * For output when list is called
	 */
	public String toString() {
		return "[E]"+super.toString()+" (at: "+schedule+")";
	}

}
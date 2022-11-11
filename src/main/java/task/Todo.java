package task;






public class Todo extends Task{

	/**
	 * Todo task constructor
	 */
	public Todo(String description){
		super(description);

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
		return "T | "+statusIndex+" | "+super.toOutput();
	}
	/**
	 * For output when list is called
	 */
	@Override
	public String toString() {
		return "[T]"+super.toString();
	}
}
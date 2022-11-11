package task;






public class Todo extends Task{
	public Todo(String description){
		super(description);

	}

	@Override
	public String toOutput(){
		String statusIndex="0";
		if (getStatusIcon().equals("X")){
			statusIndex="1";
		}
		return "T | "+statusIndex+" | "+super.toOutput();
	}

	@Override
	public String toString() {
		return "[T]"+super.toString();
	}
}
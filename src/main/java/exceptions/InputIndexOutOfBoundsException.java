package exceptions;

public class InputIndexOutOfBoundsException extends DukeException{
    public InputIndexOutOfBoundsException(){
        super(" ☹ OOPS!!! Index in the input is out of bound. Please choose an index that is within than the size of our tasklist");
    }
}

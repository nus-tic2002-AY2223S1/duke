public class DukeException extends Duke {
    //catch no description for tasks
    protected void NoDescriptionException() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    //catch description that do not make sense.
    protected void UnclearDescriptionException(){
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

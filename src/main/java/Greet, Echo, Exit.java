class assistant extends Tasks{
    //method
    public void greet() {
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo(String input){
        System.out.println(input);
    }
}
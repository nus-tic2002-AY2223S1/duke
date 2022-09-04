public class Echo {
    private String word;

    public Echo(String word) {

        this.word = word;
    }
    public void get() {

     if(word.equalsIgnoreCase("list")||word.equalsIgnoreCase("blah")){
         System.out.println(word);
     }
     if(word.equalsIgnoreCase("bye")){
         System.out.println("Bye. Hope to see you again soon!");
     }

    }

}

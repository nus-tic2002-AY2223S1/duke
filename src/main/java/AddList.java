public class AddList extends Echo {
    private String word;
    private static String[] book_list=new String[100];
    private static int count=0;
    public AddList(String word) {
        super(word);
        this.word = word;

    }

    public void get() {

            if ("read book".equalsIgnoreCase(word) || "return book".equalsIgnoreCase(word)) {
                book_list[count]=word;
                count++;
                System.out.println("added: " + word);
            }

            if ("bye".equalsIgnoreCase(word)) {
                System.out.println("Bye. Hope to see you again soon!");
            }

            if ("list".equalsIgnoreCase(word)) {
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + ". " + book_list[i - 1]);

                }
            }


        }



}

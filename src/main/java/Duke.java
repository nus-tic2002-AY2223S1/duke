import java.util.Scanner;
public class Duke {
    private static String[] Addword  = new String[100];
    private static int WordCounter = 0;
    private static boolean flag = false;
    public static void main(String[] args) {
        System.out.println("Whats up player! What can i help you with today?"+ System.lineSeparator() +"[If you wanna stop playing say bye to me]");
        Scanner ScanReplies = new Scanner (System.in);
        String ChatInput;
        while(!(ChatInput = ScanReplies.nextLine()).equals("bye")) {
            AnalyzeReplies(ChatInput);
        }
        System.out.println("Bye player! Come and fight me again!"); // end the entire Chat
    }
    private static void AnalyzeReplies (String ChatInput){
    if (ChatInput.equals("bye")) { // base case
        System.out.println("Bye player! Come and fight me again!"); // end the entire Chat
    }
    else if (ChatInput.equals("list")){
            flag = true;
            ListReplies();
            flag = false;
        }
    else { // considered to be adding into the list
        Addword[WordCounter] = ChatInput;
        WordCounter++;
       }
    }
    private static void ListReplies(){
        if (flag){
            for (int i = 0; i < Addword.length && Addword[i]!=null; i++) {
                System.out.println(i+1 + ": " + Addword[i]); // +1 to not allow the starting list number to be 0
            }
        }
    }
}
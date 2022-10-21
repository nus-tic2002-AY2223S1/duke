import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    ArrayList<String> remainingTokens;

    public Parser() {
        remainingTokens = new ArrayList<>();
    }

    public void tokeniseText(String incomingText) {
        String[] strSplit = incomingText.split(" ");
        remainingTokens = new ArrayList<String>(Arrays.asList(strSplit));
    }

    public String convertToString(){
        StringBuffer sb = new StringBuffer();

        for (String i : remainingTokens) {
            sb.append(i);
            sb.append(" ");
        }
        String output = sb.toString();
        return output;
    }

    public void next(){
        remainingTokens.remove(0);
    }

    public void clear(){
        remainingTokens.clear();
    }

    public static void newline(){
        System.out.println("\n");
    }
}

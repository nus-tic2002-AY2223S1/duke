import static common.constant.CommonConstant.LOGO;
import static common.util.PrintUtil.printGreet;
import static logic.parser.Parser.parseChat;

public class Duke {
    public static void main(String[] args) {
        System.out.println(LOGO);

        printGreet();
        parseChat();
    }
}
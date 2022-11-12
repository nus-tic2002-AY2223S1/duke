import cat.Nala;
import engine.Parser;
import formatting.Helper;

import static formatting.Helper.separator;

public class Duke {
    public static void main(String[] args) {
        Parser p = Parser.getInstance();

        separator();
        System.out.println("Hello! I'm Nala the Annoying Cat!\n" + "What can I do for you?\nProtip: Type !help for a list of commands!");
        System.out.println("For event and deadline, please type dates and time in the dd/mm/yyyy HHmm (24 hr) format :) (e.g. 31/12/2022 2359)");
        separator();

            while (true) {
                try {
                //tokenizer
                Nala.nalaBehaviour();
                String incomingText = Helper.userInput();
                p.stringToToken(incomingText);

                if (p.front().equalsIgnoreCase("!help")) {
                    System.out.println("Here's the list of commands:\n" +
                                        "todo [description] - create a todo \n" +
                                         "event [description] /at [start date and time, dd/mm/yyyy HHmm] to [end date and time, dd/mm/yyyy HHmm] - create an event\n"+
                                        "deadline [description] /by [date and time, dd/mm/yyyy HHmm] - create a deadline\n"+
                                        "snooze [index] for [duration][metric time] - snooze a deadline. e.g. snooze 5 for 2 hours\n"+
                                        "mark [index] - mark a task as done\n"+
                                        "unmark [index] - mark a task as not done\n"+
                                        "delete [index] - delete a task\n"+
                                        "list - shows the current list of tasks\n"+
                                        "find [keyword] - finds tasks in the list that contains keyword\n"+
                                        "print - saves the tasklist to a .txt file\n"+
                                        "bye or end - say goodbye to Nala :(");
                }
                else if (p.front().equalsIgnoreCase("mark")) {
                    Parser.parseMark();
                }

                else if (p.front().equalsIgnoreCase("unmark")){
                    Parser.parseUnmark();
                }

                else if (p.front().equalsIgnoreCase("bye") || p.front().equalsIgnoreCase("end")) {
                    Parser.parseBye();
                    break;
                }

                else if (incomingText.equalsIgnoreCase("list")) {
                    Parser.parseShowlist();
                }

                else if (p.front().equalsIgnoreCase("delete")){
                    Parser.parseDelete();
                }

                else if (p.front().equalsIgnoreCase("print")){
                    Parser.parsePrint();
                }

                else if (p.front().equalsIgnoreCase("find")){
                    Parser.parseFind();
                }

                else if (p.front().equalsIgnoreCase("snooze")){
                    Parser.parseSnooze();
                }

                else { //add new task
                    Parser.parseAddTask();
                }
                p.clear();
            } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Nala.nalaConfused();
                }
        }
    }


}



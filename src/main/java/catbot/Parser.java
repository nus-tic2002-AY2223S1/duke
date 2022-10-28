package catbot;

import catbot.command.*;
import catbot.task.Deadline;
import catbot.task.Event;
import catbot.task.ToDo;
import catbot.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {
    public Command parse(String s) throws CatBotException, java.time.DateTimeException{

        if(s.equals("bye")){
            return new ExitCommand();
        }

        else if(s.equals("list")){
            return new ListCommand();
        }

        else if(s.startsWith("mark")){
            Integer selectedTask = Character.getNumericValue(s.charAt(5));
            return new MarkCommand(selectedTask);
        }

        else if(s.startsWith("unmark")){
            Integer selectedTask = Character.getNumericValue(s.charAt(7));
            return new UnmarkCommand(selectedTask);
        }

        else if(s.startsWith("statistics")){
            return new StatisticsCommand();
        }

        else if(s.startsWith("deadline")){
            //sample deadline string => deadline return book /by Sunday
            try{
                if (s.length() < 10) {
                    throw new CatBotException("Human, deadline description cannot be empty!");
                }

                Integer indexOfBy = s.indexOf("/by");

                if(indexOfBy == -1) {
                    throw new CatBotException("Human, deadline missing /by)");
                }

                if(s.length() == indexOfBy+3){
                    throw new CatBotException("Human, deadline must have Date/Time!");
                }

                String deadlineName = s.substring(9,indexOfBy-1);
                String byDateTime = s.substring(indexOfBy+4);

                if(byDateTime.length() == 0) {
                    throw new CatBotException("Human, deadline must have Date/Time!");
                }

                String[] arrOfDateTime = byDateTime.split(" ");
                String dateString = arrOfDateTime[0];
                String timeString = arrOfDateTime[1];

                LocalDate ld = Utils.convertDateStringWithSlashToLocalDate(dateString);
                LocalTime lt;

                if(timeString.length() == 0){
                    lt = LocalTime.parse("23:59");
                } else {
                    lt = Utils.convertFourDigitTimeStringToLocalTime(timeString);
                }

                LocalDateTime ldt = LocalDateTime.of(ld,lt);

                Deadline deadline = new Deadline(deadlineName, ldt);

                return new AddCommand(deadline);
            }
            catch (CatBotException cbe) {
                System.out.println(cbe.getMessage());
            }
        }

        else if(s.startsWith("todo")){
            //sample todo string ==> todo borrow book
            try{
                if (s.length() < 6) {
                    throw new CatBotException("Human, todo description cannot be empty!");
                }

                String toDoName = s.substring(5);

                ToDo todo = new ToDo(toDoName);

                return new AddCommand(todo);
            }
            catch (CatBotException cbe) {
                System.out.println(cbe.getMessage());
            }
        }

        else if(s.startsWith("event")){
            //sample event string => event project meeting /at Mon 2-4pm
            if (s.length() < 8) {
                throw new CatBotException("Human, event description cannot be empty!");
            }

            Integer indexOfAt = s.indexOf("/at");

            if(indexOfAt == -1) {
                throw new CatBotException("Human, event must have at Date/Time!");
            }

            String eventName = s.substring(6,indexOfAt-1);
            String fromToDateString = s.substring(indexOfAt+4);

            if(fromToDateString.length() == 0) {
                throw new CatBotException("Human, event must have Date/Time!");
            }

            String[] arrOfDateTime = fromToDateString.split(" ");
            String dateString = arrOfDateTime[0];
            String fromToTimeString = arrOfDateTime[1];

            LocalDate ld = Utils.convertDateStringWithSlashToLocalDate(dateString);

            String[] arrOfFromToTime = fromToTimeString.split("-");
            String fromTimeString = arrOfFromToTime[0];
            String toTimeString = arrOfFromToTime[1];

            LocalTime fromlt = Utils.convertFourDigitTimeStringToLocalTime(fromTimeString);
            LocalTime tolt = Utils.convertFourDigitTimeStringToLocalTime(toTimeString);

            LocalDateTime fromldt = LocalDateTime.of(ld,fromlt);
            LocalDateTime toldt = LocalDateTime.of(ld,tolt);

            Event event = new Event(eventName,fromldt,toldt);

            return new AddCommand(event);
        }

        else if (s.startsWith("delete")){
            Integer selectedTask = Character.getNumericValue(s.charAt(7));
            return new DeleteCommand(selectedTask);
        }

        else if(s.startsWith("search")){
            //sample todo string ==> todo borrow book
            try{
                if (s.length() < 6) {
                    throw new CatBotException("Human, search cannot be empty!");
                }

                String query = s.substring(7);

                return new SearchCommand(query);
            }
            catch (CatBotException cbe) {
                System.out.println(cbe.getMessage());
            }
        }

        return new UnknownCommand();
    }
}

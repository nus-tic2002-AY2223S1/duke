package utility;

import storeage.StoreFile;
import task.Deadline;
import task.Events;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class Method {

        public static boolean isNumeric(String str) {
            try {
                String strNum=str;
                if(str.contains(" ")){
                    String[] array = str.split(" ");
                    strNum = array[0];

                }
                String[] num = strNum.split("/");
                if(num.length==1){
                    Integer.parseInt(str);
                }
                if(num.length==2){
                    Integer.parseInt(num[0]);
                    Integer.parseInt(num[1]);
                }
                if(num.length==3){
                    Integer.parseInt(num[0]);
                    Integer.parseInt(num[1]);
                    Integer.parseInt(num[2]);
                }
                return true;
            } catch(NumberFormatException e){
                return false;
            }
        }


            /**
             * Returns String of Date and time.
             * If input format is not year/month/day, returned year/month/day.
             * <p>
             * This method always returns year/month/day whether user input correct
             * format or not.When user input day/month/year, it will automatical
             * convert to year/month/day.
             * @param InputDate user input string date
             * @return year/month/day or year/month/day/time
             */
        public static String convertToValidDate(String InputDate) {
            String input = InputDate;
            if (InputDate.contains(" ")) {
                String[] stringDate = InputDate.split(" ");
                input = stringDate[0];
            }
            String[] date = input.split("/");
                String year = date[0];
                String month = date[1];
                String day = date[2];

            try {
                    if (year.length() != 4) {
                        if (date[1].length() == 4 && date[2].length() != 4) {
                            year = date[1];
                        } else if (date[2].length() == 4 && date[1].length() != 4) {
                            year = date[2];
                        }
                    }
                    if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
                        if (Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 31) {
                            day = date[0];
                        }
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Pls enter valid date or time");
                }

                if (month.length() == 1 ) {
                    month = '0' + month;
                }

                if (day.length() == 1 ) {
                    day = '0' + day;
                }

            assert (year.length() ==4 && day.length() ==4) : "Is not valid year";
            assert (Integer.parseInt(month)<1 || Integer.parseInt(month)>12) : "Is not valid month";
            assert ((Integer.parseInt(year)<1 || Integer.parseInt(year)>31)) && ((Integer.parseInt(day)<1 ||(Integer.parseInt(day)>31))) : "Is not valid day";

                if (InputDate.contains(" ")) {
                    return year + "/" + month + "/" + day + InputDate.substring(InputDate.length() - 5);
                } else {
                    return year + "/" + month + "/" + day;
                }

        }


    /**
     * Returns Date pattern Month/day/year.
     * This method always returns Date format when user input date as string.
     *
     * @param inputDate user input string date.
     * @return DateFormat date.
     * @throws DateTimeParseException if year/month/day is not DateFormat
     */
        public static String convertToDate(String inputDate) throws DateTimeParseException {
              String strDate = convertToValidDate(inputDate);
                    strDate = strDate.replaceAll("/","-");
                    LocalDate date = LocalDate.parse(strDate);
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
                return date.format(dateFormat);
        }

    /**
     *Return Date and time pattern  Month/day/year time.
     * This method always returns DateTime format when user input date and time as string.
     *
     * @param inputDate user input string date and time.
     * @return DateFormat date and time.
     * @throws DateTimeParseException if year/month/day/time is not DateFormat
     */
        public static String convertToTime(String inputDate) throws DateTimeParseException {
              String str = convertToValidDate(inputDate);
              String  strDate = str.replaceAll("/", "-");
              for(char c: strDate.toCharArray()){
                 if(c==' '){
                 strDate = strDate.replace(" ","T");
                 int posT = strDate.indexOf("T");
                 strDate = strDate.substring(0,posT+3)+":"+ strDate.substring(posT+3);
                 }
              }
              LocalDateTime date = LocalDateTime.parse(strDate);
              DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hhmma");
              return date.format(dateFormat);
        }


    public static String DateTime(String inputDate){
           String date=inputDate;
            try {
               if (isNumeric(inputDate)) {
                  if(inputDate.contains(" ")){
                    date= convertToTime(inputDate);
                   }else{
                    date= convertToDate(inputDate);
                }
            }
        }catch(NumberFormatException e){
            System.out.println("Pls enter number as date");
        }
        return date;
    }


    /**
     * Return a list of item which is contains searching words.
     * If the task list contain searching words,a list of item contains searching words returned.
     *
     * @param listItems a list of task
     * @param Input searching words
     */
     public static void searchWord(ArrayList<Task> listItems, String Input){
         ArrayList<String> findList = new ArrayList<>();
         String keyWord = Input.substring(5);
         for(Task item: listItems){
             if(item.description.contains(keyWord)){
                 String find = "." + item;
                 findList.add(find);
             }
         }
         if(!findList.isEmpty()){
             System.out.println("Here are the matching tasks in your list:");
             for(String item: findList){
               System.out.println(findList.indexOf(item) + 1 + item);
             }
         }else{
             System.out.println("The searching word is not in your list:");
         }
     }

    public static void eventFunction(ArrayList<Task> listItems, String Input){
        int Pos = Input.indexOf("/");
        String eventsTask = Input.substring(6,Pos);
        String eventsDate = Input.substring(Pos+4);
        Events taskEvent = new Events(eventsTask,eventsDate);
        listItems.add(taskEvent);
        System.out.println("Got it. I've added this task: ");
        System.out.println(taskEvent);
        System.out.println("Now you have " + listItems.size() + " tasks in the list.");

    }

    public static void deleteFunction(ArrayList<Task> listItems, String Input){
        try{
            String[] userInput = Input.split(" ");
            int DeleteNum = Integer.parseInt(userInput[1]);
            Task t = listItems.get(DeleteNum-1);
            listItems.remove(DeleteNum-1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + listItems.size() + " tasks in the list.");
        }catch(IndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The index out of arraylist length");
        }
    }

    public static void todoFunction(ArrayList<Task> listItems, String Input){
        String todoTask = Input.substring(5);
        Todo taskTodo = new Todo(todoTask);
        listItems.add(taskTodo);
        System.out.println("Got it. I've added this task: ");
        System.out.println(taskTodo);
        System.out.println("Now you have " + listItems.size() + " tasks in the list.");
    }

    public static void deadlineFunction(ArrayList<Task> listItems, String Input){
        int divPos = Input.indexOf("/");
        String deadlineTask = Input.substring(9,divPos);
        String deadlineDate = Input.substring(divPos+4);
        Deadline taskDeadline = new Deadline(deadlineTask,deadlineDate);
        listItems.add(taskDeadline);
        System.out.println(taskDeadline);
        System.out.println("Got it. I've added this task: ");
        System.out.println("Now you have " + listItems.size() + " tasks in the list.");
    }

    public static void unmarkFunction(ArrayList<Task> listItems, String Input){
        String[] userInput = Input.split(" ");
        int indexUnmark = Integer.parseInt(userInput[1]);
        Task unmarkTask = listItems.get(indexUnmark-1);
        unmarkTask.markAsNotdone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkTask);
    }

    public static void markFunction(ArrayList<Task> listItems, String Input){
        String[] userInput = Input.split(" ");
        int indexMark = Integer.parseInt(userInput[1]);
        Task markTask = listItems.get(indexMark-1);
        markTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markTask);
    }

    public static void printList(ArrayList<Task>listItems){
        for(int i=1;i<=listItems.size();i++){
            System.out.println(i + "." + listItems.get(i-1));
        }
    }

    public static void save(ArrayList<Task> listItems){
        StringBuilder list = new StringBuilder();
        for (Task listItem : listItems) {
            list.append(listItem.getData()).append("\n");
        }
        StoreFile.writeToFile(list.toString());
    }

}

package taskpackage;



import function.dateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static function.isNumber.isNumeric;

/**
 * deadline task is handle all task with "deadline"
 */
public class Deadline extends Task {
        private String by;

    public Deadline(String description,String by){
        super(description);
        this.by = by;
    }


      public String getData() {
          return "D" + super.getData() +  by ;
      }


    public String convertToTime( String inputDate) throws DateTimeParseException {
        String str = dateFormat.convertToValidDate(inputDate);
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

    public String convertToDate(String inputDate) throws DateTimeParseException {
        String strDate = dateFormat.convertToValidDate(inputDate);
        strDate = strDate.replaceAll("/","-");
        LocalDate date = LocalDate.parse(strDate);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateFormat);
    }



    //need handle date format like 2/12/2018 1800 to Feb 12 2018 6pm

    public String DateAndTime(String inputDate){
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


//      public String toString() {
//          return "[D]" + super.toString() + " (by: " + by + ")";
//          }

      public String toString()  {
          return "[D]" + super.toString() + " (by: " + DateAndTime(by) + ")";
      }

}

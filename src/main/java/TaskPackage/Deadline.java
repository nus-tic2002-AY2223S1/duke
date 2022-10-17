package TaskPackage;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static TaskPackage.isNumber.isNumeric;


public class Deadline extends Task {
        private String by;

    public Deadline(String description,String by){
        super(description);
        this.by = by;
    }


      public String getData() {
          return "D" + super.getData() + "|" + by ;
      }



    //need handle date format like 2/12/2018 1800 to Feb 12 2018 6pm
    public String convertToDate(String inputDate) throws DateTimeParseException {
        String strDate = dateFormat.convertToValidDate(inputDate);
           strDate = strDate.replaceAll("/","-");
           LocalDate date = LocalDate.parse(strDate);
           DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
           return date.format(dateFormat);
    }

    public String convertToTime( String inputDate) {
        String str = dateFormat.convertToValidDate(inputDate);
          String  strDate = str.replaceAll("/", "-");
            for(char c: strDate.toCharArray()){
                if(c==' '){
                    strDate = strDate.replace(" ","T");
                    int posT = strDate.indexOf("T");
                    strDate = strDate.substring(0,posT+3)+":"+ strDate.substring(posT+3);

                }
            }
            LocalDateTime  date = LocalDateTime.parse(strDate);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hhmma");
           return date.format(dateFormat);

    }

    public String DateAndTime(String inputDate){
        String date=null;
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

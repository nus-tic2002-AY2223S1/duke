package TaskPackage;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static TaskPackage.isNumber.isNumeric;


public class Deadline extends Task {
//        protected LocalDateTime time;
//        protected LocalDate date;
        private String by;
//      public Deadline(String description, LocalDateTime by){
//          super(description);
//          this.time = by;
//      }
//
//      public Deadline(String description,LocalDate by){
//          super(description);
//          this.date = by;
//      }

    public Deadline(String description,String by){
        super(description);
        this.by = by;
    }


      public String getData() {
          return "D" + super.getData() + "|" + by ;
      }

//      public String getDate(){
//          return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
//      }
//
//    public String getTime(){
//        return this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
//    }

    public String convertToDate() throws DateTimeParseException {

           String strDate = by.replaceAll("/","-");
           LocalDate date = LocalDate.parse(strDate);
           DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
           return date.format(dateFormat);
    }

    public String convertToTime() {

            String strDate = by.replaceAll("/", "-");
            for(char c: strDate.toCharArray()){
                if(c==' '){
                    strDate = strDate.replace(" ","T");
                    int posT = strDate.indexOf("T");
                    strDate = strDate.substring(0,posT+3)+":"+ strDate.substring(posT+3);

                }
            }
            LocalDateTime  date = LocalDateTime.parse(strDate);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
           return date.format(dateFormat);

    }

    public String DateAndTime(){
        String date=null;
        try {
            if (isNumeric(by)) {
                if(by.contains(" ")){
                    date= convertToTime();
                }else{
                    date= convertToDate();
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
          return "[D]" + super.toString() + " (by: " + DateAndTime() + ")";
      }

}

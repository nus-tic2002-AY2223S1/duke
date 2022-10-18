package function;

import static function.isNumber.isNumeric;
import function.convertToTime;
import function.convertToDate;

public class DateAndTime {
    public static String DateAndTime(String inputDate){
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
}

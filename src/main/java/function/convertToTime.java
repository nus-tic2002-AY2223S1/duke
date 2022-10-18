package function;

import function.dateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class convertToTime {
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
}

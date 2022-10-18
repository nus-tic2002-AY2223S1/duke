package function;

import function.dateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class convertToDate {
    public String convertToDate(String inputDate) throws DateTimeParseException {
        String strDate = dateFormat.convertToValidDate(inputDate);
        strDate = strDate.replaceAll("/","-");
        LocalDate date = LocalDate.parse(strDate);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateFormat);
    }
}



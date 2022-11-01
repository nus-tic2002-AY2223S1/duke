package Util;

import Interface.Parser;
import Interface.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateProcessor {
    static SimpleDateFormat dateTimeToUnixFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static SimpleDateFormat dateToUnixFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat unixToStringFormat = new SimpleDateFormat("E dd MMM yyyy, HH:mm");
    static Ui ui = new Ui();;
    public DateProcessor(){
    }

    private static boolean checkTime(String t){
        return t.length() == 4;
    }

    private static boolean checkDate(String d){
        String[] parsed = d.split("/", 3);

        if (parsed.length != 3) {
            ui.sendGenericWarning("Invalid date format. Date time has to be dd/mm/yyyy.");
            return false;
        }

        if(parsed[2].length() != 4){
            ui.sendGenericWarning("Invalid year format. Year has to be yyyy.");
            return false;
        }
        return true;
    }

    private static String concatDateTime(String date, String time){
        return date + " " + time;
    }

    private String concatDate(String[]s){
        return s[0] +"/"+ s[1] +"/"+ s[2];
    }
    public static long processDateTime(String s){
        String[] parsed = s.split(" ", 2);

        if (parsed.length != 2) {
            ui.sendGenericWarning("Invalid date/time format. Date time has to be dd/mm/yyyy HHmm.");
            return -1;
        }

        if (!checkTime(parsed[1])){
            ui.sendGenericWarning("Invalid time format. Time has to be 0000 ~ 2359.");
            return -1;
        }

        if (!checkDate(parsed[0])){
            return -1;
        }
        return dateTimeToUnix(concatDateTime(parsed[0],parsed[1]));
    }

    public static long[] processDateTimeRange(String s){
        // 1/1/1999 0900 - 2/2/1999 0900
        // 1/1/1999 - 2/2/1999
        // 1/1/1999
        String[] parsedRange = s.split("-", 2);

        if (parsedRange.length == 1) {
            if(s.contains(" ")){
                ui.sendGenericWarning("Invalid format. Range has to be \n\tdd/mm/yyyy  \n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
                return null;
            }
            //all day event
            // 1/1/1999 0000 - 1/1/1999 2359
            long timeFrom = processDate(parsedRange[0]);

            if (timeFrom == -1){
                return null;
            }
            return new long[]{timeFrom,timeFrom+86399} ;
        }

        if (parsedRange.length != 2) {
            ui.sendGenericWarning("Specify a range. Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
            return null;
        }

        if(!s.contains("-")){
            ui.sendGenericWarning("Invalid format.");
            return null;
        }

        String[] parsedDateFrom = parsedRange[0].trim().split(" ", 2);
        String[] parsedDateTo = parsedRange[1].trim().split(" ", 2);

        if (parsedDateFrom.length == 1 && parsedDateTo.length == 1) {
            System.out.println("day - day");
            return new long[]{processDate(parsedDateFrom[0].trim()),processDate(parsedDateTo[0].trim())+86399} ;
        }

        if (parsedDateFrom.length == 1 || parsedDateTo.length == 1) {
            ui.sendGenericWarning("Invalid time range. Range start and end should be consistent. Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
            return null;
        }

        // time to time
        long timeFrom = processDateTime(parsedRange[0].trim());
        long timeTo = processDateTime(parsedRange[1].trim());

        if(timeFrom == -1 || timeTo == -1){
            ui.sendGenericWarning("Invalid time range. Range start and end should be consistent. Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
            return null;
        }
        return new long[]{timeFrom,timeTo};
    }

    public static long processDate(String s){
        if (!checkDate(s)){
            return -1;
        }
        return dateTimeToUnix(s+ " 0000");
    }

    public static long dateTimeToUnix(String s){
        try{
            dateTimeToUnixFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            return dateTimeToUnixFormat.parse(s+ " 0000").toInstant().getEpochSecond();
        } catch (ParseException e) {
            ui.sendGenericFatal(e.getMessage());
        }
        return -1;
    }

    public static long dateToUnix(String s){
        try{
            dateToUnixFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            return dateToUnixFormat.parse(s).toInstant().getEpochSecond();
        } catch (ParseException e) {
            ui.sendGenericFatal(e.getMessage());
        }
        return -1;
    }

    public static String unixToString(long timeStamp){
        unixToStringFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return unixToStringFormat.format(new java.util.Date(timeStamp *1000));
    }
}

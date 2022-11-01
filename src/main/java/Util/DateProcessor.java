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
    static SimpleDateFormat unixToStringFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm E");
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

    public long processDate(String s){
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

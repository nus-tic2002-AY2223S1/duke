package Duke.Util;

import Duke.Interface.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

public class DateProcessor {
    static String tz = "GMT+8:00";
    static SimpleDateFormat dateTimeToUnixFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static SimpleDateFormat dateToUnixFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat unixToStringFormat = new SimpleDateFormat("E dd MMM yyyy, HH:mm");
    static SimpleDateFormat unixToSimplifiedStringFormat = new SimpleDateFormat("HH:mm");

    private static String printInvalidDateFormat() {
        return ui.sendGenericWarning("Invalid date format. Date time has to be dd/mm/yyyy.");
    }

    private static String printInvalidYearFormat() {
        return ui.sendGenericWarning("Invalid year format. Year has to be yyyy.");
    }

    private static String printInvalidDateTimeFormat() {
        return ui.sendGenericWarning("Invalid date/time format. Date time has to be dd/mm/yyyy HHmm.");
    }

    private static String printInvalidTimeFormat() {
        return ui.sendGenericWarning("Invalid time format. Time has to be 0000 ~ 2359.");
    }

    private static String printInconsistentTimeRangeFormat() {
        return ui.sendGenericWarning("Invalid time range. Range start and end should be consistent. Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    private static String printInvalidTimeRangeFormat() {
        return ui.sendGenericWarning("Invalid format. Range has to be \n\tdd/mm/yyyy  \n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    private static String printUnspecifiedTimeRangeFormat() {
        return ui.sendGenericWarning("Specify a range. Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    private static String printInvalidTDateSeparatorFormat() {
        return ui.sendGenericWarning("Date range should be separated by '-'");
    }

    static Ui ui = new Ui();

    public DateProcessor() {
    }

    private static boolean isTimeFormatValid(String t) {
        return t.length() == 4;
    }

    private static void checkDate(String d) throws DukeException {
        String[] parsed = d.split("/", 3);

        if (parsed.length != 3) {
            throw new DukeException(printInvalidDateFormat());
        }

        if (parsed[2].length() != 4) {
            throw new DukeException(printInvalidYearFormat());
        }
    }

    private static String concatDateTime(String date, String time) {
        return date + " " + time;
    }

    public static long processDateTime(String s) throws DukeException {
        String[] parsed = s.split(" ", 2);

        if (parsed.length != 2) {
            throw new DukeException(printInvalidDateTimeFormat());
        }

        if (!isTimeFormatValid(parsed[1])) {
            throw new DukeException(printInvalidTimeFormat());
        }

        try {
            checkDate(parsed[0]);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        try {
            return dateTimeToUnix(concatDateTime(parsed[0], parsed[1]));
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static long[] processDateTimeRange(String s) throws DukeException {
        // 1/1/1999 0900 - 2/2/1999 0900
        // 1/1/1999 - 2/2/1999
        // 1/1/1999
        String[] parsedRange = s.split("-", 2);

        if (parsedRange.length == 1) {
            if (s.contains(" ")) {
                throw new DukeException(printInvalidTimeRangeFormat());
            }
            //all day event
            // 1/1/1999 0000 - 1/1/1999 2359
            long timeFrom;
            try {
                timeFrom = processDate(parsedRange[0]);
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }

            if (timeFrom == -1) {
                return null;
            }
            return new long[]{timeFrom, timeFrom + 86399};
        }

        if (parsedRange.length != 2) {
            throw new DukeException(printUnspecifiedTimeRangeFormat());
        }

        if (!s.contains("-")) {
            throw new DukeException(printInvalidTDateSeparatorFormat());
        }

        String[] parsedDateFrom = parsedRange[0].trim().split(" ", 2);
        String[] parsedDateTo = parsedRange[1].trim().split(" ", 2);

        if (parsedDateFrom.length == 1 && parsedDateTo.length == 1) {
            long timeStart;
            long timeEnd;
            try {
                timeStart = processDate(parsedDateFrom[0].trim());
                timeEnd = processDate(parsedDateTo[0].trim());
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
            return new long[]{timeStart, timeEnd + 86399};
        }

        if (parsedDateFrom.length == 1 || parsedDateTo.length == 1) {
            throw new DukeException(printInconsistentTimeRangeFormat());
        }

        // time to time
        long timeFrom = processDateTime(parsedRange[0].trim());
        long timeTo = processDateTime(parsedRange[1].trim());

        if (timeFrom == -1 || timeTo == -1) {
            throw new DukeException(printInconsistentTimeRangeFormat());
        }
        return new long[]{timeFrom, timeTo};
    }

    public static long processDate(String s) throws DukeException {
        try {
            checkDate(s);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        try {
            return dateTimeToUnix(s + " 0000");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static long dateTimeToUnix(String s) throws DukeException {
        try {
            dateTimeToUnixFormat.setTimeZone(TimeZone.getTimeZone(tz));
            return dateTimeToUnixFormat.parse(s + " 0000").toInstant().getEpochSecond();
        } catch (ParseException e) {
            throw new DukeException(ui.sendGenericFatal(e.getMessage()));
        }
    }

    public static long dateToUnix(String s) {
        try {
            dateToUnixFormat.setTimeZone(TimeZone.getTimeZone(tz));
            return dateToUnixFormat.parse(s).toInstant().getEpochSecond();
        } catch (ParseException e) {
            ui.sendGenericFatal(e.getMessage());
        }
        return -1;
    }

    public static String unixToString(long timeStamp) {
        unixToStringFormat.setTimeZone(TimeZone.getTimeZone(tz));
        return unixToStringFormat.format(new java.util.Date(timeStamp * 1000));
    }

    public static String unixToSimplifiedString(long timeStamp) {
        unixToSimplifiedStringFormat.setTimeZone(TimeZone.getTimeZone(tz));
        return unixToSimplifiedStringFormat.format(new java.util.Date(timeStamp * 1000));
    }

    public static String getMetaTimeStamp() {
        long unixTime = Instant.now().getEpochSecond();
        return unixToSimplifiedString(unixTime);
    }
}

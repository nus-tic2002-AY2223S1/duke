package duke.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

import duke.impl.Ui;

/**
 * Util Processor to process date and time
 */
public class DateProcessor {
    private static Ui ui = new Ui();
    private static final String TIME_ZONE = "GMT+8:00";
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final DateFormat DATE_TIME_FORMAT_SEPARATOR = new SimpleDateFormat("E dd MMM yyyy, h:mm a");
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm a");

    public DateProcessor() {
    }

    private static String printInvalidDateFormat() {
        return ui.sendGenericWarning("Invalid date format. Date time has to be dd/mm/yyyy.");
    }

    private static String printInvalidMonthFormat() {
        return ui.sendGenericWarning("Invalid month format. Month has to be between 01 ~ 12.");
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
        return ui.sendGenericWarning("Invalid time range. Range start and end should be consistent. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    private static String printInvalidTimeRangeFormat() {
        return ui.sendGenericWarning("Invalid format. "
                + "Range has to be \n\tdd/mm/yyyy  "
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n"
                + "\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    private static String printUnspecifiedTimeRangeFormat() {
        return ui.sendGenericWarning("Specify a range. "
                + "Range has to be \n\tdd/mm/yyyy - dd/mm/yyyy "
                + "\n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm.");
    }

    private static String printInvalidTDateSeparatorFormat() {
        return ui.sendGenericWarning("Date range should be separated by '-'");
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

        if (Integer.parseInt(parsed[1]) > 12 || Integer.parseInt(parsed[1]) < 1) {
            throw new DukeException(printInvalidMonthFormat());
        }

        if (Integer.parseInt(parsed[1]) > 12 || Integer.parseInt(parsed[1]) < 1) {
            throw new DukeException(printInvalidMonthFormat());
        }
    }

    private static String concatDateTime(String date, String time) {
        return date + " " + time;
    }

    /**
     * Verification and translation of date and time
     *
     * @param s Date Time string of format dd/MM/yyyy HHmm
     * @return UNIX time converted from s
     * @throws DukeException Exception
     */
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

    /**
     * Verification and translation of two date time
     *
     * @param s Date Time string of format dd/MM/yyyy HHmm - dd/MM/yyyy HHmm
     *          or dd/MM/yyyy - dd/MM/yyyy
     *          or dd/MM/yyyy
     * @return Start and end UNIX time converted from s
     * @throws DukeException Exception
     */
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

    /**
     * Verification of Date string
     *
     * @param s Date string
     * @return UNIX time of s
     * @throws DukeException Exception
     */
    public static long processDate(String s) throws DukeException {
        try {
            checkDate(s);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        try {
            return dateToUnix(s);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Conversion of date time string to UNIX time
     *
     * @param s Date time string
     * @return UNIX time of s
     * @throws DukeException Exception
     */
    public static long dateTimeToUnix(String s) throws DukeException {
        try {
            DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
            DATE_TIME_FORMAT.setLenient(false);
            return DATE_TIME_FORMAT.parse(s).toInstant().getEpochSecond();
        } catch (ParseException e) {
            throw new DukeException(ui.sendGenericFatal("I could not recognise this date. " + e.getMessage()));
        }
    }

    /**
     * Conversion of date string to UNIX time
     *
     * @param s Date string
     * @return UNIX time of s
     */
    public static long dateToUnix(String s) throws DukeException {
        try {
            DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
            DATE_FORMAT.setLenient(false);
            return DATE_FORMAT.parse(s + " 0000").toInstant().getEpochSecond();
        } catch (ParseException e) {
            throw new DukeException(ui.sendGenericFatal("I could not recognise this date. " + e.getMessage()));
        }
    }

    /**
     * Conversion of UNIX time to date string in 'E dd MMM yyyy, HH:mm' format
     *
     * @param timeStamp UNIX timestamp
     * @return Date time string
     */
    public static String unixToString(long timeStamp) {
        DATE_TIME_FORMAT_SEPARATOR.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        DATE_TIME_FORMAT_SEPARATOR.setLenient(false);
        return DATE_TIME_FORMAT_SEPARATOR.format(new java.util.Date(timeStamp * 1000));
    }

    /**
     * Conversion of UNIX time to date string in 'HH:mm' format
     *
     * @param timeStamp UNIX timestamp
     * @return Time string
     */
    public static String unixToSimplifiedString(long timeStamp) {
        TIME_FORMAT.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        TIME_FORMAT.setLenient(false);
        return TIME_FORMAT.format(new java.util.Date(timeStamp * 1000));
    }

    /**
     * Returns current time in HH:mm format
     * Used for chat bubble timestamp
     *
     * @return Time string of current time
     */
    public static String getMetaTimeStamp() {
        long unixTime = Instant.now().getEpochSecond();
        return unixToSimplifiedString(unixTime);
    }

    public static String getTimeNowString() {
        long unixTime = Instant.now().getEpochSecond();
        return (String.valueOf(unixTime));
    }

    public static long getTimeNow() {
        return Instant.now().getEpochSecond();
    }
}

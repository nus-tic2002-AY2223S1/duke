package duke.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Locale;
import java.util.TimeZone;

import duke.impl.Ui;
import duke.impl.UiCn;
import duke.impl.UiEn;

/**
 * Util Processor to process date and time
 */
public class DateProcessor {
    private static Ui ui;
    private static final String TIME_ZONE = "GMT+8:00";
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat dateTimeFormatSeparator = new SimpleDateFormat("E dd MMM yyyy, h:mm a");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    /**
     * Initialize DataProcessor with corresponding locale
     *
     * @param l Locale region
     */
    public DateProcessor(Ui.LocaleRegion l) {
        switch (l) {
        case EN:
            ui = new UiEn();
            dateTimeFormatSeparator = new SimpleDateFormat("E dd MMM yyyy, h:mm a");
            timeFormat = new SimpleDateFormat("hh:mm a");
            break;
        case CN:
            ui = new UiCn();
            dateTimeFormatSeparator = new SimpleDateFormat("yy年MMMd日, ah:mm", new Locale("zh", "CN"));
            timeFormat = new SimpleDateFormat("ahh:mm", new Locale("zh", "CN"));
            break;
        default:
        }
    }

    private static boolean isTimeFormatValid(String t) {
        return t.length() == 4;
    }

    private static void checkDate(String d) throws DukeException {
        String[] parsed = d.split("/", 3);

        if (parsed.length != 3) {
            throw new DukeException(ui.printInvalidDateFormat());
        }

        if (parsed[2].length() != 4) {
            throw new DukeException(ui.printInvalidYearFormat());
        }

        if (Integer.parseInt(parsed[1]) > 12 || Integer.parseInt(parsed[1]) < 1) {
            throw new DukeException(ui.printInvalidMonthFormat());
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
    public long processDateTime(String s) throws DukeException {
        String[] parsed = s.split(" ", 2);

        if (parsed.length != 2) {
            throw new DukeException(ui.printInvalidDateTimeFormat());
        }

        if (!isTimeFormatValid(parsed[1])) {
            throw new DukeException(ui.printInvalidTimeFormat());
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
    public long[] processDateTimeRange(String s) throws DukeException {
        // 1/1/1999 0900 - 2/2/1999 0900
        // 1/1/1999 - 2/2/1999
        // 1/1/1999
        String[] parsedRange = s.split("-");

        if (parsedRange.length > 2) {
            throw new DukeException(ui.printTooManyTimeRangesFormat());
        }

        if (parsedRange.length == 1) {
            if (!s.contains("-")) {
                throw new DukeException(ui.printInvalidTDateSeparatorFormat());
            }

            if (s.contains(" ")) {
                throw new DukeException(ui.printInvalidTimeRangeFormat());
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

        String[] parsedDateFrom = parsedRange[0].trim().split(" ", 2);
        String[] parsedDateTo = parsedRange[1].trim().split(" ", 2);

        if (parsedDateFrom.length == 1 && parsedDateTo.length == 1) {
            long timeStart;
            long timeEnd;
            try {
                timeStart = processDate(parsedDateFrom[0].trim());
                timeEnd = processDate(parsedDateTo[0].trim());

                if (timeEnd - timeStart == 0) {
                    return new long[]{timeStart};
                }

                if (timeEnd - timeStart < 0) {
                    throw new DukeException(ui.printDateStartBeforeEndError());
                }
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
            return new long[]{timeStart, timeEnd + 86399};
        }

        if (parsedDateFrom.length == 1 || parsedDateTo.length == 1) {
            throw new DukeException(ui.printInconsistentTimeRangeFormat());
        }

        // time to time
        long timeFrom = processDateTime(parsedRange[0].trim());
        long timeTo = processDateTime(parsedRange[1].trim());

        if (timeFrom == -1 || timeTo == -1) {
            throw new DukeException(ui.printInconsistentTimeRangeFormat());
        }

        if (timeTo - timeFrom == 0) {
            throw new DukeException(ui.printDateStartEqualsEndError());
        }

        if (timeTo - timeFrom < 0) {
            throw new DukeException(ui.printDateStartBeforeEndError());
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
    public long processDate(String s) throws DukeException {
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
    public long dateTimeToUnix(String s) throws DukeException {
        try {
            DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
            DATE_TIME_FORMAT.setLenient(false);
            return DATE_TIME_FORMAT.parse(s).toInstant().getEpochSecond();
        } catch (ParseException e) {
            throw new DukeException(ui.printParseExceptionMessage(e));
        }
    }

    /**
     * Conversion of date string to UNIX time
     *
     * @param s Date string
     * @return UNIX time of s
     */
    public long dateToUnix(String s) throws DukeException {
        try {
            DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
            DATE_FORMAT.setLenient(false);
            return DATE_FORMAT.parse(s + " 0000").toInstant().getEpochSecond();
        } catch (ParseException e) {
            throw new DukeException(ui.printParseExceptionMessage(e));
        }
    }


    /**
     * Conversion of UNIX time to date string in 'E dd MMM yyyy, HH:mm' format
     *
     * @param timeStamp UNIX timestamp
     * @return Date time string
     */
    public String unixToString(long timeStamp) {
        dateTimeFormatSeparator.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        dateTimeFormatSeparator.setLenient(false);
        return dateTimeFormatSeparator.format(new java.util.Date(timeStamp * 1000));
    }

    /**
     * Conversion of UNIX time to date string in 'E dd MMM yyyy, HH:mm' format
     *
     * @param timeStamp UNIX timestamp
     * @return Date time string
     */
    public static String unixToStringEn(long timeStamp) {
        SimpleDateFormat staticEn = new SimpleDateFormat("E dd MMM yyyy, h:mm a");
        staticEn.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        staticEn.setLenient(false);
        return staticEn.format(new java.util.Date(timeStamp * 1000));
    }

    /**
     * Conversion of UNIX time to date string in 'E dd MMM yyyy, HH:mm' format
     *
     * @param timeStamp UNIX timestamp
     * @return Date time string
     */
    public static String unixToStringCn(long timeStamp) {
        SimpleDateFormat staticCn = new SimpleDateFormat("yy年MMMd日, ah:mm", new Locale("zh", "CN"));
        staticCn.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        staticCn.setLenient(false);
        return staticCn.format(new java.util.Date(timeStamp * 1000));
    }

    /**
     * Conversion of UNIX time to date string in 'HH:mm' format
     *
     * @param timeStamp UNIX timestamp
     * @return Time string
     */
    public String unixToSimplifiedString(long timeStamp) {
        timeFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        timeFormat.setLenient(false);
        return timeFormat.format(new java.util.Date(timeStamp * 1000));
    }

    /**
     * Returns current time in HH:mm format
     * Used for chat bubble timestamp
     *
     * @return Time string of current time
     */
    public String getMetaTimeStamp() {
        return unixToSimplifiedString(Instant.now().getEpochSecond());
    }

    public static String getTimeNowString() {
        long unixTime = Instant.now().getEpochSecond();
        return (String.valueOf(unixTime));
    }

    public static long getTimeNow() {
        return Instant.now().getEpochSecond();
    }
}

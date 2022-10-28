package catbot.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Utils {
    public static String hashString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        String hashtext = bigInt.toString(16);

        while (hashtext.length() < 40) {
            hashtext = "0" + hashtext;
        }

        return hashtext;
    }

    public static LocalDate convertDateStringWithSlashToLocalDate(String date) throws java.time.DateTimeException{
        String arrofDate[] = date.split("/");
        int day = Integer.parseInt(arrofDate[0]);
        int month = Integer.parseInt(arrofDate[1]);
        int year = Integer.parseInt(arrofDate[2]);

        LocalDate ld = LocalDate.of(year,month,day);
        return ld;
    }

    public static LocalTime convertFourDigitTimeStringToLocalTime(String time) throws java.time.DateTimeException{
        String hour = time.substring(0,2);
        String minute = time.substring(2);
        LocalTime lt = LocalTime.parse(hour + ":" + minute);
        return lt;
    }
}

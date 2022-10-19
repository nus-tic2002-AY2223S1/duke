package function;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Method {
        public static boolean isNumeric(String str) {
            try {
                String strNum=str;
                if(str.contains(" ")){
                    String[] array = str.split(" ");
                    strNum = array[0];

                }
                String[] num = strNum.split("/");
                if(num.length==1){
                    Integer.parseInt(str);
                }
                if(num.length==2){
                    Integer.parseInt(num[0]);
                    Integer.parseInt(num[1]);
                }
                if(num.length==3){
                    Integer.parseInt(num[0]);
                    Integer.parseInt(num[1]);
                    Integer.parseInt(num[2]);
                }

                return true;
            } catch(NumberFormatException e){
                return false;
            }
        }


        public static boolean isDate(String inputDate) {
            String[] date = inputDate.split("/");
            if (date.length == 3 || isNumeric(date[0]) || isNumeric(date[1]) || isNumeric(date[2])) {
                return true;
            } else {
                return false;
            }
        }


        public static class dateFormat {
            /**
             * 'converToValidDate' is convert not correct inputdate format to correct Date format
             * so that DateFormat can understand it.
             * For example: when user enter date as ' 2/12/2019 1800' ,need convert the format to '2019/02/12T1800'
             */
            public static String convertToValidDate(String InputDate) {
                String input = InputDate;
                if (InputDate.contains(" ")) {
                    String[] stringDate = InputDate.split(" ");
                    String strDate = stringDate[0];
                    input = strDate;
                }
                String[] date = input.split("/");

                String year = date[0];
                String month = date[1];
                String day = date[2];

                try {

                    if (year.length() != 4) {
                        if (date[1].length() == 4 && date[2].length() != 4) {
                            year = date[1];
                        } else if (date[2].length() == 4 && date[1].length() != 4) {
                            year = date[2];
                        }
                    }
                    if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                        if (Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 12) {
                            month = date[0];
                        } else if (Integer.parseInt(date[2]) >= 1 || Integer.parseInt(date[2]) <= 12)
                            month = date[2];
                    }
                    if (Integer.parseInt(day) <= 1 || Integer.parseInt(day) >= 31) {
                        if (Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 31) {
                            day = date[0];
                        } else if (Integer.parseInt(date[1]) >= 1 && Integer.parseInt(date[1]) <= 31) {
                            day = date[1];
                        }
                    }


                } catch (DateTimeParseException e) {
                    System.out.println("Pls enter valid date or time");
                }

                if (month.length() != 2) {
                    month = '0' + month;
                }

                if (day.length() != 2) {
                    day = '0' + day;
                }

                if (InputDate.contains(" ")) {
                    return year + "/" + month + "/" + day + InputDate.substring(InputDate.length() - 5);
                } else {
                    return year + "/" + month + "/" + day;
                }

            }
        }



          public String convertToDate(String inputDate) throws DateTimeParseException {
                    String strDate = dateFormat.convertToValidDate(inputDate);
                    strDate = strDate.replaceAll("/","-");
                    LocalDate date = LocalDate.parse(strDate);
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

                    return date.format(dateFormat);
                 }


           public  String convertToTime( String inputDate) throws DateTimeParseException {
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



          public String DateAndTime(String inputDate){
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

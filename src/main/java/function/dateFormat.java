//package function;
//
//import java.time.format.DateTimeParseException;
//
//
//
//public class dateFormat {
//
//
//    /**
//     *'converToValidDate' is convert not correct inputdate format to correct Date format
//     * so that DateFormat can understand it.
//     * For example: when user enter date as ' 2/12/2019 1800' ,need convert the format to '2019/02/12T1800'
//     *
//     */
//    public static String convertToValidDate(String Inputdate) {
//        String input = Inputdate;
//        if (Inputdate.contains(" ")) {
//            String[] stringDate = Inputdate.split(" ");
//            String strdate = stringDate[0];
//            input = strdate;
//        }
//        String[] date = input.split("/");
////        int day = Integer.parseInt(date[0]);
////        int month = Integer.parseInt(date[1]);
////        int year = Integer.parseInt(date[2]);
//        String year = date[0];
//        String month = date[1];
//        String day = date[2];
//
//        try {
//
//            if (year.length() != 4) {
//                if (date[1].length() == 4 && date[2].length() != 4) {
//                    year = date[1];
//                } else if (date[2].length() == 4 && date[1].length() != 4) {
//                    year = date[2];
//                }
//            }
//            if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
//                if (Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 12) {
//                    month = date[0];
//                } else if (Integer.parseInt(date[2]) >= 1 || Integer.parseInt(date[2]) <= 12)
//                    month = date[2];
//            }
//            if (Integer.parseInt(day) <= 1 || Integer.parseInt(day) >= 31) {
//                if (Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 31) {
//                    day = date[0];
//                } else if (Integer.parseInt(date[1]) >= 1 && Integer.parseInt(date[1]) <= 31) {
//                    day = date[1];
//                }
//            }
//
//
//        } catch (DateTimeParseException e) {
//            System.out.println("Pls enter valid date or time");
//        }
//
//        if(month.length() != 2){
//            month = '0'+ month;
//        }
//
//        if(day.length() !=2){
//            day = '0'+ day;
//        }
//
//        if (Inputdate.contains(" ")) {
//            return year + "/" + month + "/" + day + Inputdate.substring(Inputdate.length() - 5);
//        } else {
//            return year + "/" + month + "/" + day;
//        }
//
//    }
//}

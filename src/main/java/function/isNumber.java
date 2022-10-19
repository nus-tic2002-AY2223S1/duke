//package function;
//
//public class isNumber {
//    public static boolean isNumeric(String str) {
//        try {
//            String strNum=str;
//            if(str.contains(" ")){
//              String[] array = str.split(" ");
//              strNum = array[0];
//
//            }
//            String[] num = strNum.split("/");
//            if(num.length==1){
//                Integer.parseInt(str);
//            }
//            if(num.length==2){
//                Integer.parseInt(num[0]);
//                Integer.parseInt(num[1]);
//            }
//            if(num.length==3){
//                Integer.parseInt(num[0]);
//                Integer.parseInt(num[1]);
//                Integer.parseInt(num[2]);
//            }
//
//            return true;
//        } catch(NumberFormatException e){
//            return false;
//        }
//    }
//}

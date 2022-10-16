package TaskPackage;

public class isNumber {
    public static boolean isNumeric(String str) {
        try {
            String[] num = str.split("/");
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
                Integer.parseInt(num[1]);
            }
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

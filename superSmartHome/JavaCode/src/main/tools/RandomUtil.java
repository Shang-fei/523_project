import java.util.Random;

public class RandomUtil {
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
    public static String generateStr(int len){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i <len ; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return  sb.toString();
     }
    
//    public static void main(String[] args) {
//    	String a = RandomUtil.generateStr(5);
//    	System.out.print(a);
//    }
}
    
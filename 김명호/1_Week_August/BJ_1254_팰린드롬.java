import java.util.*;
import java.lang.*;

public class BJ_1254_팰린드롬 {
    public static String datas;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        datas = sc.nextLine();
        int result = datas.length();
        if (solution()) {
            System.out.println(result);
        } else {
            while (true) {
                datas = datas.substring(1);
                if (solution()) {
                    System.out.println(result * 2 - datas.length());
                    break;
                }
            }
        }
    }

    public static boolean solution() {
        for (int i = 0; i < datas.length()/2; i++) {
            if(datas.charAt(i) != datas.charAt(datas.length()-i-1)){
                return false;
            }
        }
        return true;
    }
}

import java.util.*;
import java.io.*;
import java.lang.*;
class Solution {
    public static int solution(int n, int k) {
        int answer = 0;
        String convert = divide(n, k);
        String[] temp = convert.split("0");
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].length()!=0 && Long.parseLong(temp[i]) != 1L && isPrime(Long.parseLong(temp[i]))) {
                answer++;
            }
        }

        return answer;
    }

    private static String divide(int n, int k) {
        String answer = "";
        while (n >= k) {
            answer = String.valueOf(n % k) + answer;
            n /= k;
        }
        answer = String.valueOf(n % k) + answer;
        return answer;

    }

    private static boolean isPrime(long n) {
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
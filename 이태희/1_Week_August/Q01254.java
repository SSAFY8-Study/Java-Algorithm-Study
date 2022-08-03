package com.boj.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/1254
 *  @performance - 14184 120
 *  @category - string
 *  @memo
 */

public class Q01254 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String src = br.readLine();
        boolean valid = false;
        int h = 0;
        for( ; h < src.length(); h++) {
            int head = h;  int tail = src.length() - 1;
            while (src.charAt(head) == src.charAt(tail)) {
                if (head >= tail) valid = true;
                if (valid) break;
                head++; tail--;
            }
            if (valid) break;
        }System.out.println(src.length()+h);
    }
}

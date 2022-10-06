package com.boj.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/5582
 *  @performance - 12092 156
 *  @category - dynamic programming
 *  @memo
 */

public class Q05582 {

    public static int[] table;
    public static int maxlen = 0;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String a = br.readLine();
        String b = br.readLine();
        table = new int [a.length()+1];
        for(int i = 0; i < b.length(); i++){
            for(int j = a.length(); j > 0; j--){
                if(b.charAt(i) == a.charAt(j-1)) table[j] = table[j-1] +1;
                else table[j] = 0;
                maxlen = Math.max(maxlen, table[j]);
            }
        }
        System.out.println(maxlen);
    }
}

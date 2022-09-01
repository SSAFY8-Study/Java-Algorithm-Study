package com.boj.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q09084{

    public static int T;
    public static int N;
    public static int M;
    public static int[] table;
    public static int[] coins;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public  static  StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            coins = new int[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int n = 0; n < N; n++) coins[n] = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(br.readLine());
            table = new int[M + 1];
            table[0] = 1;
            for (int c : coins) for (int m = c; m <= M; m++) table[m] += table[m - c];
            sb.append(table[M]+"\n");
        }
        System.out.println(sb);
    }
}

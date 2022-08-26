package com.boj.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q02293{

    public static int N;
    public static int K;
    public static int[] table;
    public static int[] coins;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        table = new int[K+1];
        table[0] = 1;
        coins = new int[N];
        for(int n = 0; n < N; n++) coins[n] = Integer.parseInt(br.readLine());
        for(int c : coins) for(int k = c; k <= K; k++) table[k] += table[k - c];
        System.out.println(table[K]);
    }
}

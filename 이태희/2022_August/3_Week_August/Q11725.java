package com.boj.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/11725
 *  @performance - 83268 592
 *  @category - graph
 *  @memo
 */

public class Q11725 {

    public static int N;
    public static int[] parents;
    public static List<Integer>[] connections;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        connections = new List [N+1];
        parents = new int [N+1];
        for(int i = 1; i <= N; i++) connections[i] = new ArrayList<>();
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            connections[v1].add(v2);
            connections[v2].add(v1);
        }
        find(1, 1);
        for(int i = 2; i <= N; i++) sb.append(parents[i]+"\n");
        System.out.print(sb);
    }

    public static void find(int v, int parent){
        parents[v] = parent;
        for(int child : connections[v]) if(child != parent) find(child, v);
    }

}

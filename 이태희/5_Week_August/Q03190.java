package com.boj.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q03190 {

    public static int N;
    public static int K;
    public static int L;
    public static int[][] map;
    public static int[][] cmd;
    public static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static Queue<int[]> q = new LinkedList<>();

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public  static  StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int [N][N];
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = 2;
        }
        L = Integer.parseInt(br.readLine());
        cmd = new int [L+1][2];
        for(int l=0; l<L; l++) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd[l][0] = Integer.parseInt(st.nextToken());
            cmd[l][1] = st.nextToken().charAt(0);
        }
        cmd[L][0] = Integer.MAX_VALUE;
        cmd[L][1] = 'D';

        int[] head = new int[] {0, 0};
        int d = 0;
        q.offer(new int[] {0, 0});
        map[0][0] = 1;
        int cur = 1;
        for(int c=0; c<=L; c++){
            for(int t=cur; t<=cmd[c][0];t++){
                head[0] += delta[d][0];
                head[1] += delta[d][1];
                if(head[0] < 0 || head[0] >= N || head[1] < 0 || head[1] >= N){
                    System.out.println(t);
                    return;
                }
                if(map[head[0]][head[1]] == 0){
                    int[] tail = q.poll();
                    map[tail[0]][tail[1]] = 0;
                }
                if(map[head[0]][head[1]] == 1){
                    System.out.println(t);
                    return;
                }
                q.offer(new int[] {head[0], head[1]});
                map[head[0]][head[1]] = 1;
            }
            if(cmd[c][1] == 'D') d = (d+1)%4;
            else if(cmd[c][1] == 'L') d = (d+3)%4;
            cur = cmd[c][0] + 1;
        }
    }
}

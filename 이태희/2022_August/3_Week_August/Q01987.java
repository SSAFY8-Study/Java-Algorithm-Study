package com.boj.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q01987 {

    public static int R, C;
    public static char [][] map;
    public static boolean[] includes = new boolean [26];
    public static int maxDist = 0;
    public static int dist = 0;
    public static int [][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) map[r][c] = s.charAt(c);
        }
        dfs(0, 0);
        System.out.println(maxDist);
    }

    public static void dfs(int r, int c){
        includes[map[r][c]-'A'] = true;
        dist++;
        maxDist = Math.max(maxDist, dist);
        for(int d = 0; d < 4; d++){
            int nr = r+delta[d][0]; int nc = c+delta[d][1];
            if(!(nr < 0 || nr >= R || nc < 0 || nc >= C || includes[map[nr][nc] - 'A'])) dfs(nr, nc);
        }
        dist--;
        includes[map[r][c]-'A'] = false;
    }

}

package com.boj.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q21608 {

    public static int N;
    public static int[][] map;
    public static List<Integer>[] students;
    public static int[] order;
    public static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int [N][N];
        students = new List [N*N+1];
        students[0] = new ArrayList<>();
        order = new int[N*N];
        for(int n=0; n<N*N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            order[n] = idx;
            students[idx] = new ArrayList<>();
            for(int i=0; i<4; i++) students[idx].add(Integer.parseInt(st.nextToken()));
        }

        for(int n=0; n<N*N; n++) {
            List<int[]> r1 = rule1(order[n]);
            if(r1.size() == 1){
                map[r1.get(0)[0]][r1.get(0)[1]] = order[n];
                continue;
            }
            List<int[]> r2 = rule2(r1);
            if(r2.size() == 1) {
                map[r2.get(0)[0]][r2.get(0)[1]] = order[n];
                continue;
            }
            int[] r3 = rule3(r2);
            map[r3[0]][r3[1]] = order[n];
        }

        System.out.println(satisfaction());
    }

    public static List<int[]> rule1(int s){
        int max = 0;
        List<int[]> ans = new ArrayList<>();
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++) {
                if(map[r][c] == 0){
                    int cnt = 0;
                    for(int[] d : delta) {
                        int nr=r+d[0], nc=c+d[1];
                        if(nr>=0&&nr<N&&nc>=0&&nc<N && students[s].contains(map[nr][nc])) cnt++;
                    }
                    if(cnt>max){
                        ans = new ArrayList<>();
                        max = cnt;
                    }
                    if (cnt>=max) ans.add(new int[] {r, c});
                }
            }
        }
        return ans;
    }
    public static List<int[]> rule2(List<int[]> r1){
        int max = 0;
        List<int[]> ans = new ArrayList<>();
        for(int[] pos : r1){
            int cnt = 0;
            for(int[] d : delta) {
                int nr=pos[0]+d[0], nc=pos[1]+d[1];
                if(nr>=0&&nr<N&&nc>=0&&nc<N && map[nr][nc]==0) cnt++;
            }
            if(cnt>max){
                ans = new ArrayList<>();
                max = cnt;
            }
            if (cnt>=max) ans.add(new int[] {pos[0], pos[1]});
        }
        return  ans;
    }
    public static int[] rule3(List<int[]> r2){
        return r2.get(0);
    }

    public static int satisfaction(){
        int sum = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++) {
                int cnt = 0;
                for(int[] d : delta) {
                    int nr=r+d[0], nc=c+d[1];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && students[map[r][c]].contains(map[nr][nc])) cnt++;
                }
                switch(cnt){
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;
                }
            }
        }
        return sum;
    }

}

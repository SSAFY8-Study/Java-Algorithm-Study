package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());//높이
        int w = Integer.parseInt(st.nextToken());//넓이
        st = new StringTokenizer(br.readLine());
        
        boolean[][] map = new boolean[h][w];

        //입력
        for (int c=0; c<w; c++) {
            int b = Integer.parseInt(st.nextToken());
            for (int r=0; r<b; r++) map[h-r-1][c] = true;
        }

        int result = 0;
        for (int r=0; r<h; r++) {
            boolean flag = false;//고이는 구역 표시
            
            int left = 0; // 왼쪽 기둥 인덱스
            for (int c=0; c<w; c++) {
                if (map[r][c]) { // 기둥이 있을때
                    if (flag) { // 이 기둥이 오른쪽 기둥이면
                    	result += c-left-1;
                    }else { // 이 기둥이 왼쪽 기둥이면
                    	flag = true;
                    }
                    left = c;
                }
            }
        }

        System.out.println(result);
    }
}
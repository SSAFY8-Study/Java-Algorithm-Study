import java.util.*;
import java.io.*;
import java.lang.*;

public class BJ_1987_알파벳 {//기저조건과 배열을 해결하지 못하였습니다 ㅠ
//12452	996
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, max = Integer.MIN_VALUE;
    static int[][] deltas = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static StringTokenizer st;
    static boolean[] visited;
    static int[][] arr;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[26];//dfs 수행시에 방문했던 알파벳도 경우에 따라 방문해야할 경우가 생기는데, 그 부분을 해결할 수 있는 배열.
        //알파벳 26개만큼의 배열을 만들고 방문한 알파벳을 체크해주는 식으로 진행한다.
        //만났던 알파벳을 다시 만나면 max와 비교 후 더 큰 값을 저장한다.
        String temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = temp.charAt(j) - 'A';//알파벳을 인덱스화 해주기 위해서
            }
        }
        dfs(0, 0, 0);


        bw.write(max + "\n");
        bw.flush();
        bw.close();

    }

    public static void dfs(int x, int y, int cnt) {
        if (visited[arr[x][y]]) {//방문했던 알파벳을 다시 만나면 종료
            max = Integer.max(max, cnt);
            return;
        }
        visited[arr[x][y]] = true;
        for (int d = 0; d < 4; d++) {//사방탐색
            int lr = x + deltas[d][0];
            int lc = y + deltas[d][1];

            if(isIn(lr,lc)){
                dfs(lr,lc,cnt+1);
            }
        }
        visited[arr[x][y]] = false;

    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }


}
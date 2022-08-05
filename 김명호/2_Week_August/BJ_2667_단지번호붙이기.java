import java.io.*;
import java.lang.*;
import java.util.*;

public class BJ_2667_단지번호붙이기 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[][] visited;
    public static int[][] arr;
    public static int[] num = new int[400];
    public static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    public static int N, M, total = 0;
    public static int cnt = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < N; j++) {

                if (temp[j].equals("1")) {
                    arr[i][j] = -1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == -1) {
                    cnt++;
                    dfs(i, j);
                }
            }
        }
        num = Arrays.copyOf(num,cnt+1);
        Arrays.sort(num);
        bw.write(cnt + "\n");
        for (int i = 1; i <= cnt; i++) {
            bw.write(num[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int i, int j) {
        arr[i][j] = cnt;
        num[cnt]++;
        for (int k = 0; k < deltas.length; k++) {
            int lr = i + deltas[k][0];
            int lc = j + deltas[k][1];
            if (isIn(lr, lc)) {
                dfs(lr, lc);
            }
        }
    }

    public static boolean isIn(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < N && arr[i][j] == -1;
    }
}

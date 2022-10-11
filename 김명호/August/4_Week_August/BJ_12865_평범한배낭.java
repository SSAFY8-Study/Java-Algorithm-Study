import java.util.*;
import java.io.*;
import java.lang.*;

public class BJ_12865_평범한배낭{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;
    static int[][] dp;
    static int[][] arr;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0],o2[0]));
        solution();
        bw.write(dp[N][K] + "\n");
        bw.flush();
        bw.close();
    }

    private static void solution() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i-1][j];
                if(j-arr[i-1][0]>=0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i-1][0]]+arr[i-1][1]);
                }
            }
        }
    }
}
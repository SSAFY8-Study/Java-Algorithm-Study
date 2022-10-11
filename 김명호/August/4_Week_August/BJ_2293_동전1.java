import java.util.*;
import java.io.*;
import java.lang.*;

public class BJ_2293_동전1{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;
    static int[] arr, dp;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        solution();
        bw.write(dp[K] + "\n");
        bw.flush();
        bw.close();
    }

    private static void solution() {
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            int coin = arr[i];
            for (int j = coin; j <= K; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }
    }

}
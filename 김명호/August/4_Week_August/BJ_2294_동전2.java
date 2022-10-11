import java.util.*;
import java.io.*;
import java.lang.*;

public class BJ_2294_동전2{
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
        for (int i = 0; i < K+1; i++) {
            dp[i] = 100001;
        }
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        solution();
        if(dp[K] == 100001){
            bw.write(-1 + "\n");
        }else{
            bw.write(dp[K] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            dp[0]=0;
            int coin = arr[i];
            for (int j = coin; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j-coin]+1);
            }
        }
    }
}
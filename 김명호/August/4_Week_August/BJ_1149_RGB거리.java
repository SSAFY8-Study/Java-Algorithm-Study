import java.io.*;
import java.lang.*;
import java.util.*;

public class BJ_1149_RGB거리{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,min=Integer.MAX_VALUE;
    static String []temp;
    static int [][]arr,dp;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];
        dfs(1);
        bw.write(min+"\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(int r) {
        if(r==N){
            min = Math.min(dp[r-1][0],dp[r-1][1]);
            min = Math.min(min,dp[r-1][2]);
            return;
        }
        dp[r][0] = Math.min(dp[r-1][1],dp[r-1][2])+arr[r][0];
        dp[r][1] = Math.min(dp[r-1][0],dp[r-1][2])+arr[r][1];
        dp[r][2] = Math.min(dp[r-1][0],dp[r-1][1])+arr[r][2];

        dfs(r+1);

    }
}
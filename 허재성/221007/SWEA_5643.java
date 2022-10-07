import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 
 * @author koreii
 * @since 2022. 10. 7.
 * @see
 * @git
 * @performance
 * @category #
 * @note
 */
public class Solution {
 
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
 
    final static int WIN = 1;
    final static int UNKNOWN = 0;
    final static int LOSE = -1;
 
    static int T;
    static int N, M;
    static int[][] dp; // dp[i][j] = WIN : i가 j보다 키가 큼, dp[i][j] = 0 : i, j 키 우열 모름, dp[i][j] = -1 : i가
                        // j보다 키가 작음
    static int ans = 0;
 
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
 
        for (int t = 1; t <= T; t++) {
            ans = 0;
            N = Integer.parseInt(bf.readLine());
            M = Integer.parseInt(bf.readLine());
 
            dp = new int[N + 1][N + 1];
 
            for (int i = 0; i < M; i++) {
                int loser, winner;
                tokens = new StringTokenizer(bf.readLine());
 
                loser = Integer.parseInt(tokens.nextToken());
                winner = Integer.parseInt(tokens.nextToken());
 
                dp[loser][winner] = LOSE;
                dp[winner][loser] = WIN;
            }
             
            floydWarshall();
             
//          System.out.println("++++++++++++++++++++++++++++++++++");
//          for(int i = 1; i <= N; i++) {
//              for(int j = 1; j <= N; j++)
//                  System.out.print(dp[i][j] + " ");
//              System.out.println();
//          }
//          System.out.println("++++++++++++++++++++++++++++++++++");
             
            for(int i = 1; i <= N; i++) {
                int cnt = 0;
                for(int j = 1; j <= N; j++) {
                    if(dp[i][j] != UNKNOWN)
                        cnt++;
                }
                 
                if(cnt == N - 1)
                    ans++;
            }
             
            sb.append(String.format("#%d %d\n", t, ans));
        }
         
        System.out.print(sb);
    }
 
    static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dp[i][k] == LOSE && dp[k][j] == LOSE) // i가 k에게 지고, k는 j에게 지면
                        dp[i][j] = LOSE; // i는 j에게 당연히 짐
                    else if (dp[i][k] == WIN && dp[k][j] == WIN) // i가 k를 이기고, k는 j를 이기면
                        dp[i][j] = WIN;
                }
            }
        }
    }
}
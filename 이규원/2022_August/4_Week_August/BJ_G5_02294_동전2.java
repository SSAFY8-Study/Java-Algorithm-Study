import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/2294
 * @performance 11764	104
 * @category DP
 * @memo
 */

public class BJ_G5_02294_동전2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
//		reader = new BufferedReader(new StringReader(str));
		
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		
		int[] coins = new int[N + 1];
		
		for (int n = 1; n <= N; n++) {
			coins[n] = Integer.parseInt(reader.readLine());
		}
		
		int[] dp = new int[K + 1];
		
		for (int n = 1; n <= N; n++) {
			for (int k = 1; k <= K; k++) {
				if (k % coins[n] == 0 || k > coins[n] && dp[k - coins[n]] > 0) {
					if (dp[k] == 0) {
						dp[k] = dp[k - coins[n]] + 1;
					}else {
						dp[k] = Math.min(dp[k], dp[k - coins[n]] + 1);
					}
				}
			}
		}
		if (dp[K] != 0)
			System.out.println(dp[K]);
		else
			System.out.println(-1);
	}
	
	static String str = "2 5\r\n" + 
			"2\r\n" + 
			"3";
}

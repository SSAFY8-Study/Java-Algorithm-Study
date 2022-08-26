import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/2293
 * @performance 11568	84
 * @category
 * @memo
 */

public class BJ_G5_02293_동전1 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new StringReader(str));
		
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		
		int[] coins = new int[N + 1];
		
		for (int n = 1; n <= N; n++) {
			coins[n] = Integer.parseInt(reader.readLine());
		}
		
		int[] dp = new int[K + 1];
		dp[0] = 1;
		for (int n = 1; n <= N; n++) {
			for (int k = 1; k <= K; k++) {
				if (k >= coins[n]) {
					dp[k] += dp[k - coins[n]];
				}
			}
		}
		System.out.println(dp[K]);
	}
	
	static String str = "3 10\r\n" + 
			"1\r\n" + 
			"2\r\n" + 
			"5";
}

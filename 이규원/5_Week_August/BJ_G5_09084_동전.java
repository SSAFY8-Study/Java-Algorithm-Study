import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/9084
 * @performance 11684	80
 * @category DP
 * @memo
 */

public class BJ_G5_09084_동전 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// reader = new BufferedReader(new StringReader(str));
		int T = Integer.parseInt(reader.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(reader.readLine());
			int[] coins = new int[N + 1];
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			for (int i = 1; i <= N; i++) {
				coins[i] = Integer.parseInt(tokens.nextToken());
			}
			int K = Integer.parseInt(reader.readLine());
			
			int[] DP = new int[K + 1];
			
			DP[0] = 1;
			
			for (int i = 1; i <= N; i++) {
				for (int k = 1; k <= K; k++) {
					if (k - coins[i] >= 0) {
						DP[k] += DP[k - coins[i]];
					}
				}
			}
			output.append(DP[K]).append("\n");
		}
		System.out.println(output);
	}
	
	static String str = "3\r\n" + 
			"2\r\n" + 
			"1 2\r\n" + 
			"1000\r\n" + 
			"3\r\n" + 
			"1 5 10\r\n" + 
			"100\r\n" + 
			"2\r\n" + 
			"5 7\r\n" + 
			"22";
}

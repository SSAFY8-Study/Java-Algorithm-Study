package algorithm_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

/**
 * @author kyuwonlee
 * @link https://www.acmicpc.net/problem/5582
 * @performance 74768	196
 * @category LCS
 * @memo
 */

public class BJ_G5_05582_공통부분문자열 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
//		reader = new BufferedReader(new StringReader(str));
		
		String A = reader.readLine();
		String B = reader.readLine();
		
		int aSize = A.length();
		int bSize = B.length();

		int[][] dp = new int[aSize + 1][bSize + 1];
		int result = 0;
		for (int i = 1; i <= aSize; i++) {
			for (int j = 1; j <= bSize; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				result = Math.max(result, dp[i][j]);
			}
		}
//		for (int[] row : dp)
//			System.out.println(Arrays.toString(row));
		System.out.println(result);
	}
	static String str = "ABRACADABRA\n"
			+ "ECADADABRBCRDARA";
}

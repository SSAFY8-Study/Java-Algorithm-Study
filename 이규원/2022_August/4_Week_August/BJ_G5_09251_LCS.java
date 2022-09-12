package algorithm_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/9251
 * @performace 16272	120
 * @category LCS
 * @memo
 */

public class BJ_G5_09251_LCS {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new StringReader(str));

		String A = reader.readLine();
		String B = reader.readLine();

		int aSize = A.length();
		int bSize = B.length();

		int[][] dp = new int[aSize + 1][bSize + 1];

		for (int i = 1; i <= aSize; i++) {
			for (int j = 1; j <= bSize; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

//		for (int[] row : dp) {
//			System.out.println(Arrays.toString(row));
//		}
//
//		int nx = aSize;
//		int ny = bSize;
//		int minValue = dp[nx][ny];
//		while (dp[nx][ny] != 0 && nx >= 0 && ny >= 0) {
//			int dx = nx;
//			int dy = ny + 1;
//
//			if (inIn(dx, dy, aSize, bSize) && dp[dx][dy] < minValue) {
//				output.append(A.charAt(nx - 1));
//				minValue = dp[dx][dy];
//			} else {
//				dx = nx - 1;
//				dy = ny;
//				if (inIn(dx, dy, aSize, bSize) && dp[dx][dy] < minValue) {
//					output.append(A.charAt(nx - 1));
//					minValue = dp[dx][dy];
//				}
//			}
//			
//			nx = dx;
//			ny = dy;
//		}
//
//		System.out.println(output.reverse());
		
		System.out.println(dp[aSize][bSize]);
	}

	static boolean inIn(int dx, int dy, int N, int M) {
		return 0 <= dx && dx <= N && 0 <= dy && dy <= M;
	}

	static String str = "ACAYKP\n" + "CAPCAK";
}

// solutions
// ACAYAP
// CAPCAK
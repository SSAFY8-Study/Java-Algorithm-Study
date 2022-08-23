package algo.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * 
 * @author TEQ
 * @since 2022. 8. 22.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class bj_9251_LCS {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static char[] one, another;
	static int[][] DP;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		one = input.readLine().toCharArray();
		another = input.readLine().toCharArray();
		DP = new int[one.length + 1][another.length + 1];
		for (int r = 1; r <= one.length; r++) {
			for (int c = 1; c <= another.length; c++) {
				if (one[r - 1] == another[c - 1]) {
					DP[r][c] = DP[r - 1][c - 1] + 1;
				} else {
					DP[r][c] = Integer.max(DP[r - 1][c], DP[r][c - 1]);
				}
			}
		}
		System.out.println(DP[one.length][another.length]);
	}

	// REMOVE_START
	private static String src = "ACAYKP\r\n" + "CAPCAK";
	// REMOVE_END
}
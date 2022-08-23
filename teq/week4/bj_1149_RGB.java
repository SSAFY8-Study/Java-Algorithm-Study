package algo.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1149_RGB {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] DP = new int[2][3];
	static int N, n;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		N = Integer.parseInt(input.readLine());
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < 3; c++) {
				n = Integer.parseInt(tokens.nextToken());
				DP[r % 2][c] = Integer.min(n + DP[(r + 1) % 2][(c + 1) % 3], n + DP[(r + 1) % 2][(c + 2) % 3]);
			}
		}
		Arrays.sort(DP[(N + 1) % 2]);
		System.out.println(DP[(N + 1) % 2][0]);
	}

	// REMOVE_START
	private static String src = "8\r\n" + "71 39 44\r\n" + "32 83 55\r\n" + "51 37 63\r\n" + "89 29 100\r\n"
			+ "83 58 11\r\n" + "65 13 15\r\n" + "47 25 29\r\n" + "60 66 19";
	// REMOVE_END
}
package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/1149
 * @performance 18672	240
 * @category DP
 * @memo
 */

public class BJ_S1_01149_RGB거리 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		reader = new BufferedReader(new StringReader(str));
		int N = Integer.parseInt(reader.readLine());
		
		
		memo = new int[N][3];
		tokens = new StringTokenizer(reader.readLine());
		memo[0][0] = Integer.parseInt(tokens.nextToken());
		memo[0][1] = Integer.parseInt(tokens.nextToken());
		memo[0][2] = Integer.parseInt(tokens.nextToken());
		for (int n = 1; n < N; n++) {
			tokens = new StringTokenizer(reader.readLine());
			int R = Integer.parseInt(tokens.nextToken());
			int G = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			DP(n, R, G, B);
		}
//		for (int[] row : memo) {
//			System.out.println(Arrays.toString(row));
//		}
		
		System.out.println(Arrays.stream(memo[N - 1]).min().getAsInt());
	}
	
	private static void DP(int n, int r, int g, int b) {
		memo[n][0] = Math.min(r + memo[n - 1][1], r + memo[n - 1][2]);
		memo[n][1] = Math.min(g + memo[n - 1][0], g + memo[n - 1][2]);
		memo[n][2] = Math.min(b + memo[n - 1][0], b + memo[n - 1][1]);
	}

	static String str = "8\r\n" + 
			"71 39 44\r\n" + 
			"32 83 55\r\n" + 
			"51 37 63\r\n" + 
			"89 29 100\r\n" + 
			"83 58 11\r\n" + 
			"65 13 15\r\n" + 
			"47 25 29\r\n" + 
			"60 66 19";
}

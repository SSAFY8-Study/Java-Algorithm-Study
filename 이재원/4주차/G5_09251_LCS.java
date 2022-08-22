package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_09251_LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] s = br.readLine().toCharArray();
		char[] t = br.readLine().toCharArray();
		
		int[][] dp = new int[s.length+1][t.length+1];
		
		for(int i = 1; i <= s.length; i++) {
			for(int j = 1; j <= t.length; j++) {
				dp[i][j] = s[i-1] == t[j-1] ? dp[i-1][j-1] + 1 : Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		System.out.println(dp[s.length][t.length]);
	}
}

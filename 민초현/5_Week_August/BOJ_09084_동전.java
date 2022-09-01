package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_09084_동전 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder out = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; t++) {
			int n = Integer.parseInt(in.readLine());
			
			st = new StringTokenizer(in.readLine());
			int[] coins = new int[n];
			for(int i=0; i<n; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			
			int m = Integer.parseInt(in.readLine());
			
			int[] dp = new int[m+1];
			dp[0] = 1;
			
			for(int coin : coins) {
				for(int money = coin; money<=m; money++) {
					dp[money] += dp[money-coin];
				}
			}
			
			out.append(dp[m]+"\n");
		}
		System.out.print(out);
	}

}

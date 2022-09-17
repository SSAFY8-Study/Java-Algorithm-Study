package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11644KB		92ms
public class BOJ_02293_동전1 {

	// 각 동전 갯수 무한대 & 순서 다른 경우는  같은 것으로 취급
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[n];
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(in.readLine());	
		}	
		
		// dp[i] : 동전 가치합을 i로 만드는 경우의 수
		int[] dp = new int[k+1];
		dp[0] = 1;
		
		for(int curCoin: coin) {
			for(int totalValue = curCoin; totalValue<=k; totalValue++) {
				dp[totalValue] += dp[totalValue-curCoin];
			}
		}
		
		System.out.println(dp[k]);
	}

}

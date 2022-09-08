package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11752KB		104ms
public class BOJ_02294_동전2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// dp[i] : 동전 가치 합을 i로 만드는 최소 동전 수
		int[] dp = new int[k+1];
		
		int[] coin = new int[n];
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(in.readLine().trim());
			if(coin[i] <= k)			// 이거 안하면 인덱스 에러. 코인 값이 k보다 큰 경우 에러남
				dp[coin[i]] = 1;
		}	

		for(int curCoin: coin) {
			for(int totalValue = curCoin; totalValue <=k; totalValue++) {
				if(dp[totalValue-curCoin] > 0)		// 이거 안하면, totalValue 만들 수 없는 경우에도 1이 나옴
					if(dp[totalValue] == 0)
						dp[totalValue] = dp[totalValue-curCoin]+1;
					else
						dp[totalValue] = Math.min(dp[totalValue], dp[totalValue-curCoin]+1);
			}
		}
		
		System.out.println(dp[k] > 0 ? dp[k] : -1);

	}

}

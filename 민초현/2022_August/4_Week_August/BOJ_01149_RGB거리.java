package sw_day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12120KB		88ms
public class BOJ_01149_RGB거리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(in.readLine());
		int[][] cost = new int[n][3];
		 
		for(int i=0; i<n; i++) {
			
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<3; j++)
				cost[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i] = {i번째 집이 빨강일 때 0~i까지 비용 최솟값, i번째 집이 초록일 때 0~i까지 비용 최솟값, i번째 집이 파랑일 때 0~i까지 비용 최솟값}
		int[][] dp = new int[n][3];
		
		dp[0][0] = cost[0][0];		// 0번째 집이 빨강인 비용
		dp[0][1] = cost[0][1];		// 0번째 집이 초록인 비용
		dp[0][2] = cost[0][2];		// 0번째 집이 파랑인 비용
		
		for(int i=1; i<n; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];	// 이전 집 색상이 초록일 때와 파랑인 경우 중 더 비용이 적은 경우 선택
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];	// 이전 집 색상이 빨강일 때와 파랑인 경우 중 더 비용이 적은 경우 선택
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];	// 이전 집 색상이 빨강일 때와 초록인 경우 중 더 비용이 적은 경우 선택
		}
		
		System.out.println(Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));
	}

}

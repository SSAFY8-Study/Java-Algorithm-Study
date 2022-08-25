import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n12865_평범한_배낭 {

	static int n, k;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n][2];
		int[][] dp = new int[n][k+1];
		
		for(int i = 0; i < n; i++) {
			str = bf.readLine();
			st = new StringTokenizer(str, " ");
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= k; i++) {
			if(arr[0][0] <= i) dp[0][i] = arr[0][1];
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j <= k; j++) {
//				아래 코드는 시간 초과...
//				if(arr[i][0] <= j && dp[i-1][j] < arr[i][1]) dp[i][j] = arr[i][1];
//				
//				if(dp[i-1][j] > dp[i][j]) dp[i][j] = dp[i-1][j];
//				
//				for(int h = 1; h <= j/2; h++) {
//					int a = dp[i][h] + dp[i][j-h];
//					if(h == j - h) a = dp[i][h];
//					
//					dp[i][j] = Math.max(dp[i][j], a);
//				}
				
				if(arr[i][0] > j) dp[i][j] = dp[i-1][j];
				//dp[i-1][0] 이 아니라 dp[i][0]으로 집어넣으는다면 현재 넣은 물건을 두번 넣어버리는 경우가 생겨 틀리는 것 같다. 
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - arr[i][0]] + arr[i][1]);
				}
				
			}
		}
		
//		for(int i = 0; i < n; i++) System.out.println(Arrays.toString(dp[i]));
		System.out.println(dp[n-1][k]);
	}

}

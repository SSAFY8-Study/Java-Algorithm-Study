import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n1149_RGB거리 {

	static int n;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st;

		n = Integer.parseInt(str);
		arr = new int[n][3];
		
		int[][] dp = new int[n][3];
//		Arrays.fill(dp, Integer.MAX_VALUE);
		
		for(int i = 0; i < n; i++) {
			str = bf.readLine();
			st = new StringTokenizer(str, " ");
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1]; 
		dp[0][2] = arr[0][2]; 
		
		for(int i = 1; i < n; i++) {
			dp[i][0] = arr[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = arr[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = arr[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		int res = Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
		System.out.println(res);
	}

}

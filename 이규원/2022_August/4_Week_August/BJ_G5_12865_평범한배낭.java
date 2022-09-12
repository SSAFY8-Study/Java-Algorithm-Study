import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BJ_G5_12865_평범한배낭 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
//		reader = new BufferedReader(new StringReader(str));
		
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		
		int[][] dp = new int[N + 1][K + 1];
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(reader.readLine());
			W[i + 1] = Integer.parseInt(tokens.nextToken());
			V[i + 1] = Integer.parseInt(tokens.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int k = 1; k <= K; k++) {
				dp[i][k] = dp[i - 1][k];
				if (k - W[i] >= 0) {
					dp[i][k] = Math.max(dp[i][k], dp[i - 1][k - W[i]] + V[i]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}
	
	static String str = "4 7\r\n" + 
			"6 13\r\n" + 
			"4 8\r\n" + 
			"3 6\r\n" + 
			"5 12";
}

// solutions
// DP 문제 - 입력이 어마무시하게 클 경우
// 
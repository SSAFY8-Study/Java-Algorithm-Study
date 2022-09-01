package study5;
/**
 * 	11740	80
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9084_동전 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] coin = new int[N];
			for(int i=0; i<N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int[] dp = new int[M+1];
			dp[0] = 1;
			for(int i=0; i<N; i++) {
				for(int j=coin[i]; j<=M; j++) {
					dp[j] += dp[j-coin[i]];
				}
			}
			System.out.println(dp[M]);
		}

	}

}

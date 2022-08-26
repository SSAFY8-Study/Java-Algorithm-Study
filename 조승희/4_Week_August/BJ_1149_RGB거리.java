package study4_08;
/**
 * 12088	88
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1149_RGB거리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N+1][3];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());	//빨 초 파 저장
			}
		}
		for(int i=1; i<N; i++) {
			dp[i][0] += Math.min(dp[i-1][1], dp[i-1][2]);	//빨강인 경우 이전의 파랑 or 초록 중 작은 값 더함
			dp[i][1] += Math.min(dp[i-1][0], dp[i-1][2]);	//초록인 경우 이전의 파랑 or 빨강 중 작은 값 더함
			dp[i][2] += Math.min(dp[i-1][0], dp[i-1][1]);	//파랑인 경우 이전의 빨강 or 초록 중 작은 값 더함
		}
		
		int ans = Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]);	//마지막 빨초파 중 작은 값 선택
		System.out.println(ans);
		
	}

}

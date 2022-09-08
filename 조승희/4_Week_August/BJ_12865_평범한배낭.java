package study4_08;
/**
 * 51344	148
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 물품의 수
		int K = Integer.parseInt(st.nextToken());	// 버틸 수 있는 무게
		int[][] dp = new int[N+1][K+1];		// dp 저장할 배열
		int[][] bag = new int[N+1][2];		// 각 물건의 무게외 가치
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i][0] = Integer.parseInt(st.nextToken());	// 무게
			bag[i][1] = Integer.parseInt(st.nextToken());	// 가치
		}
		
		for(int i=1; i<=N; i++) {	// 각 물건에 대해서
			for(int j=1; j<=K; j++) {	//	1부터 K까지의 무게에 대한 가치 확인
				if(bag[i][0]>j) {	// 확인하고 있는 무게보다 현재 물건의 무게가 더 많이 나가면
					dp[i][j] = dp[i-1][j];	// 해당 물건은 못 넣으므로 전의 값 그대로 가져오기
				}else {		// 더 적게 나가면 가방에 넣을 수 있으므로
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-bag[i][0]]+bag[i][1]);
					// 바로 전의 가치와 [현재 확인하고 있는 무게 - 물건의 무게(나머지 무게만큼 더 넣을 수 있는 물건들의 가치의 합)]+현재 물건의 가치 비교
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}

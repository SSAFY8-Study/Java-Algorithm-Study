package study4_08;

/**
 * 11608	84
 * https://seongjuk.tistory.com/entry/BOJ-%EB%B0%B1%EC%A4%80-2293%EB%B2%88-%EB%8F%99%EC%A0%84-1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2293_동전1 {

	static int N, K, ans = 0;
	static int[] coin;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 동전 종류 개수
		K = Integer.parseInt(st.nextToken());	// 만들어야 하는 액수
		coin = new int[N];		// 동전의 가치 넣을 배열
		for(int i=0; i<N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		int[] dp = new int[K+1];	
		dp[0] = 1;		// 0개의 동전을 선택하는 방법은 1가지
		for(int i=0; i<N; i++) {
			for(int j=coin[i]; j<=K; j++) {
				if(j >= coin[i]) {	
					dp[j] += dp[j-coin[i]];	
				}
			}
		}
		System.out.println(dp[K]);

	}


}

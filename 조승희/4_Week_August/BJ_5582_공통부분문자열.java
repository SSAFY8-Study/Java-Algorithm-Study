package study4_08;
/**
 * 	74568	196
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_5582_공통부분문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		int max = 0;
		
		for(int i=1; i<=s1.length(); i++) {
			for(int j=1; j<=s2.length(); j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {	// 문자열 1과 문자열 2의 문자가 같으면
					dp[i][j]=dp[i-1][j-1]+1;		// 이전의 값 +1 을 해당 위치에 넣어줌
					max = Math.max(max, dp[i][j]);	// max값 비교해서 갱신
				}
			}
		}
		System.out.println(max);	//max값 출력
	}

}

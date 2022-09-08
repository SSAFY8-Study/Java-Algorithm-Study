package study4_08;
/**
 * 	16088	112	
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9251_LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();	// 문자열 1
		String s2 = br.readLine();	// 문자열 2
		int[][] dp = new int[s1.length()+1][s2.length()+1];	//문자열1과 문자열 2의 공통 부분을 누적할 배열
		for(int i=1; i<=s1.length(); i++) {
			for(int j=1; j<=s2.length(); j++) {
				if(s1.charAt(i-1)==s2.charAt(j-1)) {	// 만약 두 문자가 같으면
					dp[i][j] = dp[i-1][j-1]+1;		// 이전의 값에서 하나 더해줌
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);	// 부분 문자열이 이어져 있지 않아도 되므로 이전의 max값 가져옴
				}
			}
		}
		System.out.println(dp[s1.length()][s2.length()]);	//최댓값 출력
	}

}

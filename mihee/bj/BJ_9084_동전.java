package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9084_동전 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] dp;
	static int[] money;
	static int N, M;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for(int t=0;t<T;t++) {
			N = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());
			
			money=new int[N];
			for(int n=0;n<N;n++) money[n]=Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(input.readLine());
			dp = new int[M+1][N+1];
			
			for(int m=1;m<=M;m++) {
				for(int n=1;n<=N;n++) {
					if(m<money[n-1]) dp[m][n]=dp[m][n-1];
					else if(m==money[n-1]) dp[m][n]=dp[m][n-1]+1;
					if(m>money[n-1]) {
//						System.out.println(m+" "+money[n-1]+" "+n);
						dp[m][n]=dp[m][n-1]+dp[m-money[n-1]][n];
					}
				}
			}
			output.append(dp[M][N]+"\n");
		}
		System.out.println(output);

	}

}

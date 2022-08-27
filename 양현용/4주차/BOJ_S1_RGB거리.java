package on0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 25.
@see
@git
@youtube
@performance
@category #
@note */
public class BOJ_S1_RGB거리 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] dp, arr;
	static int N,R,G,B;
	
	public static void main(String[] args) throws IOException {
		//input = new BufferedReader(new StringReader(src));
		input = new BufferedReader(new FileReader("./input.txt"));
		N=Integer.parseInt(input.readLine());
		arr=new int[N+1][3];
		dp= new int[N+1][3];
		
		for(int i=1;i<N+1;i++) {
			tokens=new StringTokenizer(input.readLine());
			for(int j=0;j<3;j++) {
				arr[i][j]=Integer.parseInt(tokens.nextToken());
			}
		}
//		for(int[] li: arr) {
//			System.out.println(Arrays.toString(li));			
//		}
		for(int i=1;i<N+1;i++) {
			dp[i][0]= arr[i][0]+Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1]= arr[i][1]+Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2]= arr[i][2]+Math.min(dp[i-1][0], dp[i-1][1]);
		}
		for(int[] li: dp) {
			System.out.println(Arrays.toString(li));			
		}
		
		System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]),dp[N][2]));
	}
}

//private static String src = "";
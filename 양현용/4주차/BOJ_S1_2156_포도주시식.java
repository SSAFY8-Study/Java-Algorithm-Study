import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 9. 2.
@see
@performance
@category #
@note */
public class BOJ_S1_2156_포도주시식{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[] arr,dp;
	
	public static void main(String[] args) throws IOException {
//    input = new BufferedReader(new StringReader(src));
		input=new BufferedReader(new FileReader("./input.txt"));
		N=Integer.parseInt(input.readLine());
		arr=new int[N+1];
		dp=new int[N+1];
		for(int i=1;i<N+1;i++) {
			arr[i]=Integer.parseInt(input.readLine());
		}
		
		dp[1]=arr[1];
		if(N>1) {
			dp[2]=arr[1]+arr[2];
			for(int i=3;i<N+1;i++) {
				dp[i]=Math.max(dp[i-1], Math.max(dp[i-2]+arr[i],dp[i-3]+arr[i]+arr[i-1]));
			}
		}
		System.out.println(dp[N]);
	}
	
// REMOVE_START
	private static String src = "";
// REMOVE_END
}
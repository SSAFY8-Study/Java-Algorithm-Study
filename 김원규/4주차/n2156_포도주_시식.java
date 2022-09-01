package home0831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n2156_포도주_시식 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int n = Integer.parseInt(str);
		
		int[] arr = new int[n+1];
		int[] dp = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			str = br.readLine();
			arr[i] = Integer.parseInt(str);
		}
		
		
		//1은 1을 마신게 최대
		dp[1] = arr[1];
		
		//2도 2까지 마신게 최대
		if(n!=1) {
			dp[2] = arr[2] + arr[1];
			
			//3은 3가지
			//1. 1이랑 3을 마신경우 => dp[1] + arr[3]
			//2. 1이랑 2만 마신경우 => dp[2]
			//3. 2랑 3을 마신경우 => arr[2]+ arr[3]
			
			//arr을 사용하려면 적어도 해당 인덱스보다 2 작은 수를 가져와야 한다 그래야 연속됨을 방지하니까
			//  1 2   1 3   2 3
			if(n != 2) {
				for(int i = 3; i <= n; i++) {
					int a = dp[i-1];
					int b = dp[i-2] + arr[i];
					int c = dp[i-3] + arr[i-1] + arr[i];
					
					dp[i] = Math.max(a, Math.max(b, c));
				}
			}
		}

		
		System.out.println(dp[n]);
	}

}

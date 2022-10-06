import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2023
 * @difficuly 
 * @performance 11716KB   80ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	
	static boolean isPrime(int x) {
		if(x < 2)
			return false;
		
		for(int i = 2; i * i <= x; i++) {
			if(x % i == 0)
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		dfs(0, 0);
		System.out.print(sb);
	}
	
	static void dfs(int nth, int cur) {
		if(nth == N) {
			sb.append(String.format("%d\n", cur));
			return;
		}
		
		for(int i = 1; i < 10; i++) {
			if(nth == 0) {	//	첫 자릿수일 경우
				if(isPrime(i))
					dfs(nth + 1, i);
			}
			else {
				if(i % 2 == 0 || i % 5 == 0)
					continue;
				
				if(isPrime(cur * 10 + i))
					dfs(nth + 1, cur * 10 + i);
			}
		}
 	}
}
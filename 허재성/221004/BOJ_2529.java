import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_2529 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int K;
	static char[] expression;
	static String maxStr;
	static String minStr;
	static long maxNum = Long.MIN_VALUE;
	static long minNum = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(bf.readLine());
		expression = new char[2 * K + 1];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < K; i++)
			expression[2 * i + 1] = tokens.nextToken().charAt(0);
	
		dfs(0, new boolean[10], expression);
//		System.out.println(Arrays.toString(expressions));
		System.out.println(maxStr);
		System.out.println(minStr);
	}
	
	static void dfs(int nth, boolean[] isUsed, char[] expr) {
//		System.out.println("dfs(" + nth + ", " + Arrays.toString(expr) + ")");
		
		if(nth == K + 1) {
			StringBuilder res = new StringBuilder();
			long value = 0;
			
			for(int i = 0; i <= 2 * K; i += 2)
				res.append(expr[i]);
//			System.out.println("결과 문자열 = " + res);
			
			value = Long.parseLong(res.toString());
			if(value > maxNum) {
				maxNum = value;
				maxStr = res.toString();
			}
			if(value < minNum) {
				minNum = value;
				minStr = res.toString();
			}
			
			return;
		}
		
		if(nth == 0) {
			for(int i = 0; i < 10; i++) {
				if(!isUsed[i]) {
					expr[2 * nth] = (char)(i + '0');
					isUsed[i] = true;
					dfs(nth + 1, isUsed, expr);
					isUsed[i] = false;
				}
			}
		}
		else if(nth > 0) {
			for(int i = 0; i < 10; i++) {
				if(!isUsed[i]) {
					int curNum = i;
					char op = expr[2 * nth - 1];
					int prevNum = expr[2 * nth - 2] - '0';
					
					switch(op) {
					case '>':
						if(prevNum > curNum) {
							expr[2 * nth] = (char)(i + '0');
							isUsed[i] = true;
							dfs(nth + 1, isUsed, expr);
							isUsed[i] = false;
						}
						else continue;
						break;
					case '<':
						if(prevNum < curNum) {
							expr[2 * nth] = (char)(i + '0');
							isUsed[i] = true;
							dfs(nth + 1, isUsed, expr);
							isUsed[i] = false;
						}
						else continue;
						break;
					}
				}
			}
		}
	}	//	dfs
}

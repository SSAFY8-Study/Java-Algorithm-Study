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

public class SWEA_4008 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int T;					//	tc 개수
	static int N;					//	tc 당 피연산자 개수
	static int[] nums;				//	피연산자 
	static int[] op = new int[4];	//	op[0] : + 개수, op[1] : - 개수, op[2] : * 개수, op[3] : / 개수
	
	static int maxExpr;	//	수식 최댓값
	static int minExpr;	//	수식 최솟값
	static int diff;	//	수식 최댓값 - 수식 최솟값

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++) {
			maxExpr = Integer.MIN_VALUE;
			minExpr = Integer.MAX_VALUE;
			
			N = Integer.parseInt(bf.readLine());
			nums = new int[N];
			
			tokens = new StringTokenizer(bf.readLine());
			for(int i = 0; i < 4; i++)
				op[i] = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++)
				nums[i] = Integer.parseInt(tokens.nextToken());
		
			dfs(0, nums[0], op[0], op[1], op[2], op[3]);
			sb.append(String.format("#%d %d\n", t, diff));
		}
		System.out.print(sb);
	}
	
	static void dfs(int nth, int cur, int add, int sub, int mul, int div) {
		if(nth == N - 1) {
			if(cur > maxExpr)
				maxExpr = cur;
			if(cur < minExpr)
				minExpr = cur;
			diff = maxExpr - minExpr;
			
			return;
		}
		
		if(add > 0) {
			dfs(nth + 1, cur + nums[nth + 1], add - 1, sub, mul, div);
		}
		if(sub > 0) {
			dfs(nth + 1, cur - nums[nth + 1], add, sub - 1, mul, div);
		}
		if(mul > 0) {
			dfs(nth + 1, cur * nums[nth + 1], add, sub, mul - 1, div);
		}
		if(div > 0) {
			dfs(nth + 1, cur / nums[nth + 1], add, sub, mul, div - 1);
		}
	}
}

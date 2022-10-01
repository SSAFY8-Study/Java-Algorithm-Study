import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @since 2022. 9. 27.
 * @see
 * @git
 * @performance
 * @category #
 * @note
 */
public class BOJ_11658{

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N; // N : 수 개수
	static int[] nums; // nums : 수 목록
	static int[] ops; // 연산자 개수(덧셈, 뺄셈, 곱셈, 나눗셈 개수)
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		nums = new int[N];
		ops = new int[4];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < 4; i++)
			ops[i] = Integer.parseInt(tokens.nextToken());
		
		dfs(0, nums[0], ops[0], ops[1], ops[2], ops[3]);
		System.out.println(max);
		System.out.println(min);
	}
	
	//	nth : 처리하는 수 순서
	static void dfs(int nth, int cur, int add, int sub, int mul, int div) {
		if(nth == N - 1) {
			if(cur > max)
				max = cur;
			if(cur < min)
				min = cur;
			
			return;
		}
		
		if(add > 0) 
			dfs(nth + 1, cur + nums[nth + 1], add - 1, sub, mul, div);
		if(sub > 0)
			dfs(nth + 1, cur - nums[nth + 1], add, sub - 1, mul, div);
		if(mul > 0)
			dfs(nth + 1, cur * nums[nth + 1], add, sub, mul - 1, div);
		if(div > 0)
			dfs(nth + 1, cur / nums[nth + 1], add, sub, mul, div - 1);
	}
}
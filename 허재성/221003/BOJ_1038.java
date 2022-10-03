import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

public class BOJ_1038 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static boolean found = false;
	static int cnt = -1;	//	찾은 개수
	static long ans = -1;	//	정답
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		bfs();
		System.out.println(ans);
	}
	
	static boolean isDecreasing(int num) {
		String str = Integer.toString(num);
		int len = str.length();
		
		if(len == 1)	//	한 자릿수일 경우 그냥 감소하는 수
			return true;
		
		for(int i = 0; i < len - 1; i++) {
			if(str.charAt(i) <= str.charAt(i + 1))
				return false;
		}
		
		return true;
	}
	
	static void bfs() {
		Queue<Long> q = new LinkedList<>();
		for(long i = 0; i < 10; i++) {
			q.offer(i);
//			q.offer(-1L);
		}
		
		while(!q.isEmpty()) {
			long curNum = q.poll();
//			long parent = q.poll();
//			System.out.println("curNum = " + curNum);
//			System.out.println("parent = " + parent);
			
			cnt++;			//	감소하는 수 번호 증가
			if(cnt == N) {	//	감소하는 N번째 수일 경우
				ans = curNum;
				return;
			}
			
			for(int i = 0; i < 10; i++) {
				long lastDigit = curNum % 10;
				
				if(i >= lastDigit)
					continue;
				
				q.offer(curNum * 10 + i);
//				q.offer(curNum);
			}
		}
	}
}

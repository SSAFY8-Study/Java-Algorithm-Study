package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G5_05014_스타트링크 {
	static boolean[] visited;
	static int F, S, G, U, D, ans;
	static int[] dx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		visited = new boolean[F+1];
		dx = new int[] {U, -D};
		
		boolean flag = bfs(S);
		
		System.out.println(flag ? ans : "use the stairs");
	}
	
	static boolean bfs(int s) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {s, 0});
		visited[s] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int x = cur[0];
			int dist = cur[1];
			
			if(x == G) {
				ans = dist;
				return true;
			}
			
			for(int i = 0; i < 2; i++) {
				int nx = x + dx[i];
				
				if(isIn(nx) && !visited[nx]) {
					visited[nx] = true;
					deque.add(new int[] {nx, dist+1});
				}
			}
		}
		
		return false;
	}
	
	static boolean isIn(int x) {
		return 1 <= x && x <= F;
	}
}

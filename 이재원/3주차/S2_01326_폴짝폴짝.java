package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S2_01326_폴짝폴짝 {
	static int[] stone;
	static boolean[] visited;
	static int N, a, b, ans = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		stone = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			stone[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken()) - 1;
		b = Integer.parseInt(st.nextToken()) - 1;
		
		bfs(a);
		
		System.out.println(ans);
	}
	
	static void bfs(int start) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {start, 0});
		visited[start] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int x = cur[0];
			int dist = cur[1];
			
			if(x == b) {
				ans = dist;
				return;
			}
			
			// 양의 방향 이동
			int step = 1;
			while(true) {
				int nx = x + stone[x] * step;
				
				if(!isIn(nx)) break;
				
				if(!visited[nx]) {
					visited[nx] = true;
					deque.add(new int[] {nx, dist+1});
				}
				
				step++;
			}
			
			// 음의 방향 이동
			step = 1;
			while(true) {
				int nx = x - stone[x] * step;
				
				if(!isIn(nx)) break;
				
				if(!visited[nx]) {
					visited[nx] = true;
					deque.add(new int[] {nx, dist+1});
				}
				
				step++;
			}
		}
	}
	
	static boolean isIn(int x) {
		return 0 <= x && x < N;
	}
}

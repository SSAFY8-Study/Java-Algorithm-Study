package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G4_2206_벽_부수고_이동하기 {
	static char[][] map;
	static boolean[][] visited, visitedC;
	static int N, M;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		
		map = new char[N][];
		visited = new boolean[N][M];
		visitedC = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {0, 0, 1, 0});
		visited[0][0] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int dist = cur[2];
			int crack = cur[3];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr == N-1 && nc == M-1) {
					System.out.println(dist + 1);
					return;
				}
				else if(isIn(nr, nc) && crack == 0) {
					if(!visited[nr][nc] && map[nr][nc] == '0') {
						visited[nr][nc] = true;
						deque.add(new int[] {nr, nc, dist+1, 0});
					}
					else if(!visited[nr][nc] && map[nr][nc] == '1') {
						visited[nr][nc] = true;
						deque.add(new int[] {nr, nc, dist+1, 1});
					}
				}
				else if(isIn(nr, nc) && crack == 1) {
					if(!visitedC[nr][nc] && map[nr][nc] == '0') {
						visitedC[nr][nc] = true;
						deque.add(new int[] {nr, nc, dist+1, 1});
					}
				}
			}
		}
		
		System.out.println(-1);
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}

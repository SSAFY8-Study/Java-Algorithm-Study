package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S1_02468_안전_영역 {
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int maxHeight = 0, ans = 0;
		int[] dr = {0,0,-1,1};
		int[] dc = {-1,1,0,0};
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > maxHeight) {
					maxHeight = map[i][j];
				}
			}
		}
		
		for(int h = maxHeight; h >= 0; h--) {
			boolean[][] visited = new boolean[N][N];
			int safeArea = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] > h && !visited[i][j]) {
						Deque<Integer[]> deque = new LinkedList<>();
						deque.add(new Integer[] {i, j});
						visited[i][j] = true;
						
						while(!deque.isEmpty()) {
							Integer[] cur = deque.poll();
							int r = cur[0];
							int c = cur[1];
							
							for(int k = 0; k < 4; k++) {
								int nr = r + dr[k];
								int nc = c + dc[k];
								
								if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] > h) {
									deque.add(new Integer[] {nr, nc});
									visited[nr][nc] = true;
								}
							}
						}
						
						safeArea++;
					}
				}
				if(safeArea > ans) {
					ans = safeArea;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}

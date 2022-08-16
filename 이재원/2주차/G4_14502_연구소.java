package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_14502_연구소 {
	static List<int[]> virusLoc = new ArrayList<>();
	static int[][] map;
	static boolean[][] wall, visited;
	static int N, M, cnt0 = -3, ans = Integer.MIN_VALUE; // 벽을 3개 세울 것이므로 0의 개수 -3에서 시작
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		wall = new boolean[N][M];
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					cnt0++;
				}
				else if(map[i][j] == 2) {
					virusLoc.add(new int[] {i, j}); // 바이러스의 위치 list
				}
			}
		}
		
		makeWall(0);
		System.out.println(ans);
	}
	
	static void makeWall(int cnt) {
		// 벽 3곳에 세웠으면 bfs
		if(cnt == 3) {
			int area = cnt0;
			int temp = bfs(area);
			if(temp > ans) {
				ans = temp;
			}
			return;
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 원본 배열 map 변경하지 않고 boolean 2차원 배열로 벽의 위치 기록
				if(map[r][c] == 0 && !wall[r][c]) {
					wall[r][c] = true;
					makeWall(cnt+1);
					wall[r][c] = false;
				}
			}
		}
	}
	
	static int bfs(int area) {
		Deque<int[]> deque = new LinkedList<>();
		visited = new boolean[N][M];
		
		// 모든 바이러스 위치에서 bfs
		area += virusLoc.size();
		for(int[] loc: virusLoc) {
			deque.add(loc.clone());
			visited[loc[0]][loc[1]] = true;
		}
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			area--;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && !wall[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
				}
			}
		}
		
		// 안전 영역 크기 return
		return area;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}

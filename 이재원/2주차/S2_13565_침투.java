package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_13565_침투 {
	static char[][] map;
	static boolean[][] visited;
	static int M, N;
	static String ans = "NO";
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		visited = new boolean[M][N];
		map = new char[M][];
		
		for(int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 바깥쪽 흰색 격자들에서 dfs
		for(int c = 0; c < N; c++) {
			if(!visited[0][c] && map[0][c] == '0') {
				dfs(0, c);
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int r, int c) {
		// 안쪽에 도달하거나 다른 전류가 이미 도달했으면 return
		if(r == M-1 || ans.equals("YES")) {
			ans = "YES";
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == '0') {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < M && 0 <= c && c < N;
	}
}

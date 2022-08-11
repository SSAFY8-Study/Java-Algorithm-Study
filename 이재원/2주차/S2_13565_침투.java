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
		
		for(int c = 0; c < N; c++) {
			if(!visited[0][c]) {
				dfs(0, c);
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int r, int c) {
		if(r == M-1) {
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

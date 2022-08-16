package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_01987_알파벳 {
	static char[][] map;
	static int[][] visited;
	static int R, C, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		visited = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int bit = 1 << (map[0][0] - 'A');
		dfs(0, 0, 1, bit);
		
		System.out.println(ans);
	}
	
	static void dfs(int r, int c, int d, int v) {
		if(visited[r][c] == v) return;
		visited[r][c] = v;
		
		if(d > ans) {
			ans = d;
		}
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc)) {
				int bit = 1 << (map[nr][nc] - 'A');
				if((~v & bit) == bit) {
					int nv = v | bit;
					dfs(nr, nc, d+1, nv);
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}

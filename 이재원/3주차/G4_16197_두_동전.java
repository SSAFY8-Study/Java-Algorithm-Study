package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_16197_두_동전 {
	static char[][] map;
	static int N, M, ans = Integer.MAX_VALUE;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int[][] coins = new int[2][2];
		int idx = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'o') {
					map[i][j] = '.';
					coins[idx][0] = i;
					coins[idx][1] = j;
					idx++;
				}
			}
		}
		
		recur(0, coins[0][0], coins[0][1], coins[1][0], coins[1][1], -1, -1, -1, -1);
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans+1);
		}
	}
	
	static void recur(int nth, int r1, int c1, int r2, int c2, int pr1, int pc1, int pr2, int pc2) {
		if(nth == 10) return;
		
		for(int i = 0; i < 4; i++) {
			int nr1 = r1 + dr[i];
			int nc1 = c1 + dc[i];
			int nr2 = r2 + dr[i];
			int nc2 = c2 + dc[i];
			int fall = 0;
			
			if(!isIn(nr1, nc1)) fall++;
			if(!isIn(nr2, nc2)) fall++;
			
			if(nr1 == pr1 && nc1 == pc1 && nr2 == pr2 && nc2 == pc2) continue;
			
			if(fall == 2) {
				continue;
			}
			else if(fall == 1) {
				ans = Math.min(ans, nth);
			}
			else {
				if(map[nr1][nc1] == '#') {
					nr1 = r1;
					nc1 = c1;
				}
				if(map[nr2][nc2] == '#') {
					nr2 = r2;
					nc2 = c2;
				}
				
				if(nr1 == pr1 && nc1 == pc1 && nr2 == pr2 && nc2 == pc2) continue;
				
				if(nr1 == nr2 && nc1 == nc2) continue;
				
				recur(nth+1, nr1, nc1, nr2, nc2, r1, c1, r2, c2);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}

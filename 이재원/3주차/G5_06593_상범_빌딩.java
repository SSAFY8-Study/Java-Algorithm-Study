package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G5_06593_상범_빌딩 {
	static char[][][] map;
	static boolean[][][] visited;
	static int L, R, C, ans;
	static int[] dl = {-1,1,0,0,0,0};
	static int[] dr = {0,0,-1,1,0,0};
	static int[] dc = {0,0,0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L + R + C == 0) break;
			
			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			int l = 0, r = 0, c = 0;
			ans = -1;
			
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < R; j++) {
					String line = br.readLine();
					for(int k = 0; k < C; k++) {
						map[i][j][k] = line.charAt(k);
						if(map[i][j][k] == 'S') {
							l = i;
							r = j;
							c = k;
						}
					}
				}
				br.readLine();
			}
			
			bfs(l, r, c);
			
			if(ans != -1) {
				sb.append(String.format("Escaped in %d minute(s).\n", ans));
			}
			else {
				sb.append("Trapped!\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int sl, int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sl, sr, sc, 0});
		visited[sl][sr][sc] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int l = cur[0];
			int r = cur[1];
			int c = cur[2];
			int d = cur[3];
			
			for(int i = 0; i < 6; i++) {
				int nl = l + dl[i];
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nl, nr, nc) && !visited[nl][nr][nc]) {
					if(map[nl][nr][nc] == '.') {
						visited[nl][nr][nc] = true;
						deque.add(new int[] {nl, nr, nc, d+1});
					}
					else if(map[nl][nr][nc] == 'E') {
						ans = d+1;
						return;
					}
				}
			}
		}
	}
	
	static boolean isIn(int l, int r, int c) {
		return 0 <= l && l < L && 0 <= r && r < R && 0 <= c && c < C;
	}
}

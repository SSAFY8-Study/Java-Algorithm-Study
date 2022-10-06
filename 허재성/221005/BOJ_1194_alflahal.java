// 13432KB	108ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[n][m][1<<6];
		
		char[][] maze = new char[n][];
		for(int i=0; i<n; i++) {
			maze[i] = in.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(maze[i][j] == '0') {
					q.offer(new int[] {i, j, 0, 0});
					visited[i][j][0] = true;
				}
			}
		}
		
		int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
		
		
		int ans = -1;
		
		int[] cur, nxt = new int[2];
		
		while(!q.isEmpty()) {
			// cur[0]: r , cur[1]: c, cur[2]: 거리, cur[3]: 열쇠정보
			cur = q.poll();
			
			if(maze[cur[0]][cur[1]] == '1') {
				ans = cur[2];
				break;
			}
			
			for(int[] del: deltas) {
				nxt[0] = cur[0] + del[0];
				nxt[1] = cur[1] + del[1];
				int nxtBit = cur[3];
				
				if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<m && maze[nxt[0]][nxt[1]] !='#') {
					
					char mazeValue = maze[nxt[0]][nxt[1]];
					
					if('a' <= mazeValue && mazeValue <= 'f')
						nxtBit |= ( 1<<(mazeValue-'a') );
					
					if(!visited[nxt[0]][nxt[1]][nxtBit]) {
						
						if('A' <= mazeValue && mazeValue <= 'F' && (cur[3] & (1<<(mazeValue-'A'))) == 0)
							continue;
						visited[nxt[0]][nxt[1]][nxtBit] = true;
						q.offer(new int[] {nxt[0], nxt[1], cur[2]+1, nxtBit});
						
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}
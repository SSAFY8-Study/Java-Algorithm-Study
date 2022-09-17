package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_벽부수고이동하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(input.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] field = new char[n][];
		for(int i=0; i<n; i++)
			field[i] = input.readLine().toCharArray();
		
		int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};

		// visited[i][j] = {0번 부수고 (i, j)에 도착했을 때 이동 거리, 1번 부수고 (i, j)에 도착했을 때 이동거리}
		boolean[][][] visited = new boolean[n][m][2];			
		visited[0][0][0] = true;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, 0, 1, 0});	// (r, c). r, c, 현재까지 이동 거리, 부순 횟수
		
		int[] curPos = new int[4];
		int r, c, dis, cnt;
		int nxtR, nxtC;
		
		int ans = -1;
		while(!q.isEmpty()) {
			curPos = q.poll();
			r = curPos[0]; c = curPos[1]; dis = curPos[2]; cnt = curPos[3];

			if(r == n-1 && c == m-1) {
				ans = dis;
				break;
			}
			
			for(int i = 0; i<4; i++) {
				nxtR = r+deltas[i][0];
				nxtC = c+deltas[i][1];
				if(0<=nxtR && nxtR<n && 0<=nxtC && nxtC<m) {
					if(field[nxtR][nxtC] == '0' && !visited[nxtR][nxtC][cnt]) {
						visited[nxtR][nxtC][cnt] = true;
						q.add(new int[] {nxtR, nxtC, dis+1, cnt});
					}
					else if(field[nxtR][nxtC] == '1' && cnt == 0) {
						visited[nxtR][nxtC][cnt+1] = true;
						q.add(new int[] {nxtR, nxtC, dis+1, cnt+1});
					}
				}
			}
		}
		System.out.println(ans);
	}

}

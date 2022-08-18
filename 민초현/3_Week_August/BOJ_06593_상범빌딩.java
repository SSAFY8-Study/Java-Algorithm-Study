package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_06593_상범빌딩 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder out = new StringBuilder();

		int l, r, c;
		char[][][] building;

		while(true) {
			st = new StringTokenizer(in.readLine());

			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			// 탈출 조건
			if(l == 0 && r == 0 && c == 0) {
				break;
			}

			
			// 빌딩 정보와 시작점, 탈출점 저장
			int[] start = new int[3];
			int[] end = new int[3];
			building = new char[l][r][c];

			for(int i=0; i<l; i++) {
				for(int j=0; j<r; j++) {
					building[i][j] = in.readLine().toCharArray();	// 빌딩 정보 저장
					for(int k=0; k<c; k++) {
						if(building[i][j][k] == 'S') {		// 시작점
							start[0] = i;
							start[1] = j;
							start[2] = k;
						}
						else if(building[i][j][k] == 'E') {	// 탈출점
							end[0] = i;
							end[1] = j;
							end[2] = k;
						}
					}
				}
				in.readLine();
			}

			
			// 상, 하, 좌, 우, 위, 아래
			int[][] deltas = {{0, 1, 0}, {0, -1, 0}, {0, 0, -1}, {0, 0, 1}, {1, 0, 0}, {-1, 0, 0}};
			
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {start[0], start[1], start[2], 0});		// 시작점과 시간을 큐에 삽입
			
			boolean[][][] visited = new boolean[l][r][c];
			visited[start[0]][start[1]][start[2]] = true;				// 시작점 방문 처리

			
			// bfs 수행
			int ans = -1;
			int[] curPos, nxtPos = new int[3];

			outer: while(!q.isEmpty()) {
				curPos = q.poll();

				for(int[] del: deltas) {
					nxtPos[0] = curPos[0] + del[0];
					nxtPos[1] = curPos[1] + del[1];
					nxtPos[2] = curPos[2] + del[2];

					if(0<=nxtPos[0] && nxtPos[0]<l && 0<=nxtPos[1] && nxtPos[1]<r && 0<=nxtPos[2] && nxtPos[2]<c) {

						if(!visited[nxtPos[0]][nxtPos[1]][nxtPos[2]] && building[nxtPos[0]][nxtPos[1]][nxtPos[2]] !='#') {

							visited[nxtPos[0]][nxtPos[1]][nxtPos[2]] = true;

							if(building[nxtPos[0]][nxtPos[1]][nxtPos[2]] == 'E') {

								ans = curPos[3]+1;

								break outer;

							}
							q.offer(new int[] {nxtPos[0], nxtPos[1], nxtPos[2], curPos[3]+1});

						}
					}
				}
			}

			if(ans == -1)	out.append("Trapped!\n");
			else			out.append("Escaped in "+ans+" minute(s).\n");
		}

		System.out.print(out);

	}
}

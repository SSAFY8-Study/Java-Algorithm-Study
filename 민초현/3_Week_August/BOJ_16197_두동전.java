package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16197_두동전 {

	// 11768KB	76ms
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		
		// 보드를 입력받으면서, 동전의 초기 위치를 큐에 삽입
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][][] visited = new boolean[n][m][n][m];
		char[][] board = new char[n][];
		
		List<int[]> coin = new ArrayList<>();
		for(int i=0; i<n; i++) {
			board[i] = in.readLine().toCharArray();
			for(int j = 0; j<m; j++) {
				if(board[i][j] == 'o') {		// 동전 위치를 큐에 삽입
					coin.add(new int[] {i, j});
				}
			}
		}
		
		
		// bfs 수행
		int ans = -1;
		int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};
		int[] curPos, nxtPos = new int[4];		// bfs에서 쓰일 좌표
		
		// 초기 동전 위치 큐에 삽입 & 방문 처리
		q.offer(new int[] {coin.get(0)[0], coin.get(0)[1], coin.get(1)[0], coin.get(1)[1], 0});
		visited[coin.get(0)[0]][coin.get(0)[1]][coin.get(1)[0]][coin.get(1)[1]] = true;
		
		int isOut = 0;					// 동전이 밖으로 나가면 카운팅
		outer: while(!q.isEmpty()) {
			// r1, c1, r2, c2, cnt; 동전1 좌표(r1, c1), 동전2좌표 (r2, c2), 움직인 횟수 cnt
			// curPos[0] = r1, curPos[1] = c1
			// curPos[2] = r2, curPos[3] = c2, curPos[4] = cnt
			curPos = q.poll();
			
			if(curPos[4] == 10) {
				break;
			}
			
			for(int[] del: deltas) {
				isOut = 0;
				nxtPos[0] = curPos[0] + del[0]; nxtPos[1] = curPos[1] + del[1];		// 첫번째 동전 이동
				nxtPos[2] = curPos[2] + del[0];	nxtPos[3] = curPos[3] + del[1];		// 두번째 동전 이동
				
				// 첫번째 동전이 보드 밖으로 나갈 경우
				if(0 > nxtPos[0] || nxtPos[0] >= n || 0 > nxtPos[1] || nxtPos[1] >= m) {
					isOut++;		// 카운팅
				}
				
				// 두번째 동전이 보드 밖으로 나갈 경우
				if(0 > nxtPos[2] || nxtPos[2] >= n || 0 > nxtPos[3] || nxtPos[3] >= m) {
					isOut++;
				}
				
				if(isOut == 2) 			// 동전 2개가 모두 보드 밖으로 나갈 경우		
					continue;			// 다음으로 넘어감
				else if(isOut == 1) {	// 동전 1개만 보드 밖으로 나갈 경우
					ans = curPos[4]+1;	// 정답
					break outer;
				}

				// 벽일 경우 동전 이동 불가
				if(board[nxtPos[0]][nxtPos[1]] == '#') {
					nxtPos[0] = curPos[0]; nxtPos[1] = curPos[1];
				}
					
				if(board[nxtPos[2]][nxtPos[3]] == '#') {
					nxtPos[2] = curPos[2]; nxtPos[3] = curPos[3];
				}
				

				if(!visited[nxtPos[0]][nxtPos[1]][nxtPos[2]][nxtPos[3]]) {
					visited[nxtPos[0]][nxtPos[1]][nxtPos[2]][nxtPos[3]] = true;
						
					q.offer(new int[]{nxtPos[0], nxtPos[1], nxtPos[2], nxtPos[3], curPos[4]+1});
				}
			}
		}
		System.out.println(ans);
		
	}

}

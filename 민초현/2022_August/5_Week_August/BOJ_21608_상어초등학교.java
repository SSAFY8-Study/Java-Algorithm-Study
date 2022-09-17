package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ_21608_상어초등학교 {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder out = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		
		int n = Integer.parseInt(in.readLine());
		
		// friends[i] = [false, true, true, ... ] : i번째 학생이 좋아하는 학생은 true, 아니면 false
		boolean[][] friends = new boolean[n*n+1][n*n+1];
		
		// 배치되는 학생 번호 순서
		int[] seq = new int[n*n];
		
		int now, f;
		for(int i=0; i<n*n; i++) {
			st = new StringTokenizer(in.readLine());
			
			now = Integer.parseInt(st.nextToken());
			seq[i] = now;
			
			for(int j=0; j<4; j++) {
				f = Integer.parseInt(st.nextToken());
				friends[now][f] = true;
			}
		}
		
		
		int[][] map = new int[n][n];
		int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};

		PriorityQueue<Seat> pq = new PriorityQueue<>();
		for(int curNum: seq) {
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					if(map[i][j] != 0)	continue;
					
					int fCnt = 0, eCnt = 0;
					
					// 인접한 칸 검사
					int[] nxt = new int[2];
					
					for(int[] del: deltas) {
						nxt[0] = i+del[0];
						nxt[1] = j+del[1];
						if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n) {
							if(map[nxt[0]][nxt[1]] == 0) {	// 빈 칸
								eCnt++;
							}
							else if(friends[curNum][map[nxt[0]][nxt[1]]])	// 좋아하는 친구 있으면
								fCnt++;
						}
					}
					
					pq.offer(new Seat(i, j, fCnt, eCnt));
				}
			}
			
			// 가장 적합한 자리에 현재 학생 배치
			Seat selected = pq.poll();
			map[selected.r][selected.c] = curNum;
			pq.clear();
		}
		
		int score = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				
				int curNum = map[i][j];
				
				int cnt = 0;
				// 인접한 칸 검사
				int[] nxt = new int[2];
				
				for(int[] del: deltas) {
					nxt[0] = i+del[0];
					nxt[1] = j+del[1];
					if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n) {
						if(friends[curNum][map[nxt[0]][nxt[1]]])	// 좋아하는 친구 있으면
							cnt++;
					}
				}
				
				score += Math.pow(10, cnt-1);
			}
		}
		
		System.out.println(score);
		
	}
	
	static class Seat implements Comparable<Seat>{
		int r, c, fCnt, sideEmpty;

		public Seat(int r, int c, int fCnt, int sideEmpty) {
			super();
			this.r = r;
			this.c = c;
			this.fCnt = fCnt;
			this.sideEmpty = sideEmpty;
		}

		@Override
		public int compareTo(Seat o) {
			if(this.fCnt == o.fCnt) {
				if(this.sideEmpty == o.sideEmpty) {
					if(this.r == o.r) {
						return Integer.compare(this.c, o.c);
					}
					return Integer.compare(this.r, o.r);
				}
				return Integer.compare(this.sideEmpty, o.sideEmpty)*-1;
			}
			return Integer.compare(this.fCnt, o.fCnt)*-1;
		}
		
	}
}
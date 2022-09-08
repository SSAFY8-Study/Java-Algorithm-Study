package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 127900	696ms

public class BOJ_16234_인구이동 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] a = new int[n][n];
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Stack<int[]> s = new Stack<>();			// dfs에서 쓰일 스택
		int[][] visited;						// dfs에서 쓰일 방문 배열
		int[] curPos;							// dfs에서 pop된 좌표
		int[] nxtPos = new int[2];				// 스택에 들어갈 수 있는 다음 좌표
		int diff;
		
		int[][] deltas = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
		
		int num;									// 집합(연결된 도시들의 한 뭉텅이)에 붙은 숫자
		List<Integer> nxtPop = new ArrayList<>();	// nxtPop.get(num) == num번째 집합들에 저장될 인구 수
		
		int day = 0;		// 인구 이동한 날 횟수
		
		int cnt = 0, popSum = 0;					// 한 집합에 속하는 도시 갯수, 인구 수 합
		while(true) {
			visited = new int[n][n];
			nxtPop.clear();
			num = 1;
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					if(visited[i][j] == 0) {
					
						cnt = 0; popSum = 0;		// 도시 갯수, 인구 합 초기화 
						
						visited[i][j] = num;
						s.push(new int[] {i, j});

						while(!s.isEmpty()) {
							
							curPos = s.pop();

							cnt++;
							popSum += a[curPos[0]][curPos[1]];
							
							for(int[] dir: deltas) {
								nxtPos[0] = curPos[0] + dir[0];
								nxtPos[1] = curPos[1] + dir[1];
								if(0<=nxtPos[0] && nxtPos[0]<n && 0<=nxtPos[1] && nxtPos[1]<n) {
									
									diff = Math.abs(a[curPos[0]][curPos[1]] - a[nxtPos[0]][nxtPos[1]]);
									
									if(visited[nxtPos[0]][nxtPos[1]] == 0 && l<=diff && diff<=r) {
										visited[nxtPos[0]][nxtPos[1]] = num;
										s.push(new int[] {nxtPos[0], nxtPos[1]});
									}
								}
							}
							
							
						}
						
						num++;						// 집합 넘버링 증가
						nxtPop.add(popSum/cnt);		// 현재 집합에 속하는 도시들의 다음 인구 수 계산
						
					}
				}
			}

			// 인구 업데이트
			for(int i = 0; i<n; i++) {
				for(int j = 0; j<n; j++) {
					if(visited[i][j] > 0) {
						a[i][j] = nxtPop.get(visited[i][j]-1);
					}
				}
			}

			// 연결된 도시가 없을 경우, dfs가 n*n만큼 실행됨 => 탈출 조건
			if(num == n*n+1) {
				break;
			}
			
			day++;
		}
		
		
		System.out.println(day);
	}
}

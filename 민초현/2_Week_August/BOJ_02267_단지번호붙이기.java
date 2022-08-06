package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BOJ_02267_단지번호붙이기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		
		int n = Integer.parseInt(input.readLine());
		char[][] apt = new char[n][];
		for(int i=0; i<n; i++) {
			apt[i] = input.readLine().toCharArray();
		}
		
		List<Integer> cnt = new ArrayList<>();		// 각 단지의 집 갯수 저장할 리스트
		Stack<int[]> s = new Stack<>();				// dfs에서 사용될 스택
		int[] curPos = new int[2];					// 현재 stack에서 pop된 집의 좌표
		int[] nxtPos = new int[2];					// dfs에서 사용될 다음 좌표
		
		int houseCnt = 0;							// 각 단지의 집 갯수
		boolean[][] visited = new boolean[n][n];	// 집 방문 여부
		
		int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(apt[i][j] == '1' && !visited[i][j]) {
					houseCnt = 1;					// 집 갯수 초기화
					
					s.push(new int[] {i,j});
					visited[i][j] = true;
					
					while(!s.isEmpty()) {				// dfs 수행
						curPos[0] = s.peek()[0];
						curPos[1] = s.pop()[1];
						
						for(int d = 0; d<4; d++) {
							nxtPos[0] = curPos[0]+deltas[d][0];
							nxtPos[1] = curPos[1]+deltas[d][1]; 
							if(0<=nxtPos[0] && nxtPos[0]<n && 0<=nxtPos[1] && nxtPos[1]<n && apt[nxtPos[0]][nxtPos[1]] == '1' && !visited[nxtPos[0]][nxtPos[1]]) {
								houseCnt++;
								visited[nxtPos[0]][nxtPos[1]] = true;
								s.push(Arrays.copyOf(nxtPos, 2));
							}
						}
					}
					
					cnt.add(houseCnt);
					
				}
			}
		}
		
		Collections.sort(cnt);
		output.append(cnt.size()+"\n");
		for(int c: cnt)
			output.append(c+"\n");
		System.out.println(output);
	}

}

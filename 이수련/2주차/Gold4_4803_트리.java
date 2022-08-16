

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author dolpong
 * @date 2022.08.13
 * @url https://www.acmicpc.net/problem/4803
 * @git dolpongg
 * @performance  71512	1160
 * @category #트리 #bfs
 * @not 그래프 구성은 양방향으로!
 */

public class Gold4_4803_트리 {
	static boolean[] visited;
	static List<Integer>[] G;
	static Queue<Integer> queue;
	static int N, M;
	static int answer;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = 0;
		while(true) {
			StringBuilder sb = new StringBuilder();
			sb.append("Case ").append(TC).append(": ");
			TC++;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N==M && N==0) {
				break;
			}
			
			G = new ArrayList[N+1];
			for(int i = 1; i <= N;i++) {
				G[i] = new ArrayList<>();
			}
			
			//입력 - 그래프 단들기 
			for(int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int from  = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				G[from].add(to);
				G[to].add(from);
			}
			
			queue = new LinkedList<Integer>();
			answer = 0;
			bfs();
			
			switch (answer) {
			case 0: {
				sb.append("No trees.");
				break;
			}
			case 1:{
				sb.append("There is one tree.");
				break;
			}
			default:{
				sb.append("A forest of ").append(answer).append(" trees.");
			}
			}
			System.out.println(sb);
		}
	}
	
	static void bfs() {
		// 방문 체크 배열 초기화 
		visited = new boolean[N+1];
		
		while(true) {
			for(int i = 1; i <= N; i++ ) {
				if(!visited[i]) {
					queue.offer(i);
					break;
				}
				
				
			}
			
			if(queue.isEmpty()) {
				break;
			}
			
			boolean graph = false;
			while(queue.size() > 0) {
				int out = queue.poll();
				if(visited[out]) {
					graph = true;
				}
				visited[out] = true;

				for(int nk : G[out]) {
					
					if(!visited[nk]) {
						queue.offer(nk);
					}
				}
			}
			if(!graph)answer++;
				
		}
		
	}

}

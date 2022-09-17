package sw_day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author alflahal
@since 	2022. 8. 12.
@see	https://www.acmicpc.net/problem/18352
@performance	335640		1280
@category
@note */

public class BOJ_18352_특정거리의도시찾기 {


	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder out = new StringBuilder();
		
		// 도시 수 n, 간선 수 m, 거리 k, 시작 도시 번호 start
		st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		// graph 그리기; graph[1] = [1과 연결된 노드 번호들]
		List<Integer>[] graph = new ArrayList[n+1];
		int a, b;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(graph[a] == null)	graph[a] = new ArrayList<>();

			graph[a].add(b);
		}
		
		// start를 시작으로 bfs 수행
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {start, 0});			// start 넣기
		
		boolean[] visited = new boolean[n+1];	// 방문 여부
		visited[start] = true;					// start 방문 처리
		
		List<Integer> ans = new ArrayList<>();	// k거리인 도시 번호 저장할 리스트
		int[] curInfo;
		
		while(!q.isEmpty()) {
			curInfo = q.poll();			// curInfo[0]: 도시 번호, curInfo[1]: 거리
			if(curInfo[1] == k) {		// 거리가 k면 리스트에 추가	
				ans.add(curInfo[0]);
			}
			
			if( graph[curInfo[0]] != null) {
				for(int adj: graph[curInfo[0]]) {
					if(!visited[adj]) {
						visited[adj] = true;
						q.add(new int[]{adj, curInfo[1]+1});
					}
				}
			}
			
		}
		
		// 없을 경우, -1
		if(ans.size() == 0) {
			out.append(-1+"\n");
		}else {	// 있으면 정렬 후 출력
			Collections.sort(ans);
			for(int item: ans) {
				out.append(item+"\n");
			}
		}
		System.out.print(out);
	}
}
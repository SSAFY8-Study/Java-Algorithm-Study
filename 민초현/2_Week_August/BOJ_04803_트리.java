package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_04803_트리 {

	public static boolean isTree(int idx, int prevIdx, List<Integer>[] graph, boolean[] visited) {
		if(visited[idx])		// 방문한 적 있는 노드일 경우, 이 그래프는 사이클이 있으므로 트리 아님
			return false;
		
		visited[idx] = true;
		
		if(graph[idx] != null)
			for(int nxtIdx: graph[idx]) {
				if(nxtIdx != prevIdx) {
					if(!isTree(nxtIdx, idx, graph, visited)) {
						return false;
					}
				}
			}
		
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer st;
		
		int n, m;				// 노드 수, 간선 수
		int a, b;				// 간선의 양 끝점
		List<Integer>[] graph;	// 그래프
		boolean[] visited;		// 노드 방문 여부
		
		int treeCnt;			// 트리 갯수
		
		int t = 1;
		while(true) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if(n==0 && m == 0)
				break;
			
			treeCnt = 0;
			
			graph = new ArrayList[n+1];
			visited = new boolean[n+1];
			
			for(int i = 0; i<m; i++) {
				st = new StringTokenizer(input.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if(graph[a] == null)	graph[a] = new ArrayList<>();
				if(graph[b] == null)	graph[b] = new ArrayList<>();
				graph[a].add(b);
				graph[b].add(a);
			}
			
			for(int i=1; i<=n; i++) {
				if(!visited[i] && isTree(i, 0, graph, visited)) {
					treeCnt++;
				}
			}
			
			if(treeCnt == 0) {
				output.append("Case "+t+": No trees.\n");
			} else if(treeCnt == 1) {
				output.append("Case "+t+": There is one tree.\n");
			} else {
				output.append("Case "+t+": A forest of "+treeCnt+" trees.\n");
			}
			t++;
		}
		
		System.out.print(output);
	}

}

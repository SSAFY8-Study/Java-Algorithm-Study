package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_11724_연결요소의개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(input.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 각 노드 방문 여부 저장
		boolean[] visited = new boolean[n];
		
		// 양방향 그래프; graph[1] = [1과 연결된 노드]
		List<Integer>[] graph = new ArrayList[n];
		
		// 그래프 연결 관계 저장.
		// graph[a] == a와 연결된 노드 리스트
		int a, b;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(input.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			if(graph[a] == null)	graph[a] = new ArrayList<>();
			if(graph[b] == null)	graph[b] = new ArrayList<>();
			graph[a].add(b);
			graph[b].add(a);
		}
		
		Stack<Integer> s = new Stack<>();
		int now = 0;
		int cnt = 0;
	
		for(int i = 0; i<n; i++) {
			if(!visited[i]) {
				cnt++;
				
				s.push(i);
				visited[i] = true;
				while(!s.isEmpty()) {
					now = s.pop();
					if(graph[now]!= null)
						for(int nxt: graph[now]) {
							if(!visited[nxt]) {
								visited[nxt] = true;
								s.push(nxt);
							}
						}
				}
			}
		}
	
		System.out.println(cnt);

	}

}

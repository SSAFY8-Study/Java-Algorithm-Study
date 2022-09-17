package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 72332	508ms
public class BOJ_11725_트리의부모찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder out = new StringBuilder();
		
		int n = Integer.parseInt(in.readLine());
		List<Integer>[] graph = new ArrayList[n+1];
		int a, b;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if(graph[a] == null) graph[a] = new ArrayList<>();
			if(graph[b] == null) graph[b] = new ArrayList<>();

			graph[a].add(b);
			graph[b].add(a);
		}

		int[] parent = new int[n+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		parent[1] = -1;
		
		int now;
		
		while(!q.isEmpty()) {
			now = q.poll();
			
			for(int adj: graph[now]) {
				if(parent[adj] == 0) {
					parent[adj] = now;
					q.offer(adj);
				}
			}
		}
		
		for(int i=2; i<=n; i++)
			out.append(parent[i]+"\n");
		System.out.print(out);
	}

}

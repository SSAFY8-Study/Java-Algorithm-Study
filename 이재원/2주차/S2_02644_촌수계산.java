package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class S2_02644_촌수계산 {
	static List<Integer>[] graph;
	static int[] dist;
	static int a, b;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			graph[p].add(q);
			graph[q].add(p);
		}
		
		bfs();
		if(dist[b] == 0) {
			System.out.println(-1);
		}
		else {
			System.out.println(dist[b]);
		}
	}
	
	static void bfs() {
		Deque<Integer> deque = new LinkedList<>();
		deque.add(a);
		dist[a] = 0;
		
		while(!deque.isEmpty()) {
			int cur = deque.poll();
			
			if(cur == b) {
				break;
			}
			
			for(int next: graph[cur]) {
				if(dist[next] == 0) {
					dist[next] = dist[cur] + 1;
					deque.add(next);
				}
			}
		}
	}
}

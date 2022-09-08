package com.boj.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Q18352 {
	
	public static int V;
	public static int E;
	public static int K;
	public static int start;
	public static boolean[] visited;
	public static List<Integer>[] adjacent;
	public static List<Integer> result = new ArrayList<>();
	public static Queue<Integer> queue = new LinkedList<>();

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		adjacent = new List [V+1];
		visited = new boolean [V+1];
		for(int v = 1; v <= V; v++) adjacent[v] = new ArrayList<>();
		for(int e = 0; e < E; e++){
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			adjacent[i].add(f);
		}
		visited[start] = true;
		queue.offer(start);
		int dist = 1;
		while(!queue.isEmpty()){
			int i = queue.poll();
			for(int f : adjacent[i]){
				if(!visited[f]) {
					visited[f] = true;
					if(dist == K) result.add(f);
					else queue.offer(f);
				}
			}
			if(!result.isEmpty()) break;
			dist++;
		}
		if(result.isEmpty()){
			System.out.println("-1");
			return;
		}
		Collections.sort(result);
		for(int i = 0; i < result.size(); i++) sb.append(result.get(i)+"\n");
		System.out.print(sb);
	}

}

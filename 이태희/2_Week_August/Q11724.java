package com.boj.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/11724
 *  @performance - 115568 440
 *  @category - union find
 *  @memo
 */

public class Q11724 {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb = new StringBuilder();
	public static StringTokenizer st;

	public static int N;
	public static int M;
	public static int [] parent;
	public static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int [N+1];
		for(int i = 0; i <= N; i++) parent[i] = i;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			union(u, v);
		}
		for(int i = 1; i <= N; i++) set.add(find(i));
		System.out.println(set.size());
	}
	
	public static int find(int u) {
		if (parent[u] == u) return u;
		else parent[u] = find(parent[u]);
		return parent[u];
	}
	
	public static void union(int u, int v) {
		u = find(u); v = find(v);
		if (u < v) parent[v] = u;
		else parent[u] = v;
	}
}

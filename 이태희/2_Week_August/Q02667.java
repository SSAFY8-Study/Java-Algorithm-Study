package com.boj.silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/2667
 *  @performance - 12156 108
 *  @category - bfs
 *  @memo
 */

public class Q02667 {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb = new StringBuilder();
	public static StringTokenizer st;

	public static int N;
	public static int[][] map;
	public static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static Queue<Integer> queue = new LinkedList<>();
	public  static List<Integer> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		for (int i = 0; i < N; i++) {
			String [] tmp = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) continue;;
				int cnt =1;
				map[i][j] = 0;
				queue.offer(i); queue.offer(j);
				while (!queue.isEmpty()) {
					int r = queue.poll();
					int c = queue.poll();
					for(int d = 0; d < 4; d++){ if(!isInvalid(r + delta[d][0], c + delta[d][1])) {
						cnt++;
						queue.offer(r+ delta[d][0]);
						queue.offer(c + delta[d][1]);
						map[r+ delta[d][0]][c+ delta[d][1]] = 0;
					}}
				}
				ans.add(cnt);
			}
		}

		Collections.sort(ans);
		System.out.println(ans.size());
		for(int a : ans){
			System.out.println(a);
		}
		
	}

	
	public static boolean isInvalid(int r, int c) {
		return 0 > r || N <= r || 0 > c || N <= c || map[r][c] == 0;
	}
	
}

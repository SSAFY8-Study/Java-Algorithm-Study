package com.boj.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/16234
 *  @performance - 294156	628
 *  @category - divide and conquer
 *  @memo
 */

public class Q16234 {
	
	public static int N, L, R;
	public static int[][] map;
	public static int[][] allies;
	public static int allyCnt;
	public static int[] allyPop;
	public static int[] allyNum;
	public static int[] allyRes;
	public static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static boolean trigger;
	public static Queue<Integer> queue;
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		allies = new int [N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		for(int iter = 0; iter < 2000; iter++) {
			fillAllies();
			if(!trigger) break;
			dividePopulation();
			cnt++;
		}
		System.out.println(cnt);
	}

	public static void fillAllies() {
		allies = new int [N][N];
		allyCnt = 1;
		trigger = false;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(allies[r][c] != 0) continue;
				queue = new LinkedList<>();
				allies[r][c] = allyCnt;
				queue.offer(r); queue.offer(c);
				while(!queue.isEmpty()) {
					int cr = queue.poll(); int cc = queue.poll();
					for(int dir = 0; dir < 4; dir++) {
						int nr = cr+delta[dir][0]; int nc = cc+delta[dir][1];
						if (isAlly(cr, cc, nr, nc)) {
							trigger = true;
							allies[nr][nc] = allyCnt;
							queue.offer(nr);
							queue.offer(nc);
						}
					}
				}
				allyCnt++;
			}
		}
	}
	
	public static void dividePopulation() {
		allyPop = new int[allyCnt+1];
		allyNum = new int[allyCnt+1];
		allyRes = new int[allyCnt+1];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				allyNum[allies[r][c]]++;
				allyPop[allies[r][c]] += map[r][c];
			}
		}
		for(int a = 1; a <= allyCnt; a++) {
			if(allyNum[a] != 0) allyRes[a] = allyPop[a] / allyNum[a];
		}
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				map[r][c] = allyRes[allies[r][c]];
			}
		}
	}
	
	public static boolean isAlly(int r, int c, int nr, int nc) {
		return 0 <= nr && N > nr && 0 <= nc && N > nc &&  allies[nr][nc] == 0 && L <= Math.abs(map[r][c] - map[nr][nc]) && R >= Math.abs(map[r][c] - map[nr][nc]);
	}
	
}

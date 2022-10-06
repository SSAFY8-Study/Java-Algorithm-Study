package com.boj.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/14891
 *  @performance - 14412 136
 *  @category - simulation
 *  @memo
 */

public class Q14891 {

	//default
	public static int T;
	public static int N = 8;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb = new StringBuilder();
	

	public static int K;
	public static int G = 4;
	public static int[][] gearbox = new int [G+2][N];
	public static int[][] action;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		for (int i = 1; i <= G; i++) {			
			String[] src = br.readLine().split("");
			for (int n = 0; n < N; n++) {
				gearbox[i][n] = Integer.parseInt(src[n]);
			}
		}
		K = Integer.parseInt(br.readLine());
		action = new int [K][2];
		for(int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			action[k][0] = Integer.parseInt(st.nextToken());
			action[k][1] = Integer.parseInt(st.nextToken());
			
			gearbox = operate(gearbox, action[k][0], action[k][1]);
		}
		int score = 0;
		for(int i=0; i <= G; i++) {
			score += gearbox[i][0] * Math.pow(2, i-1);
		}
		System.out.println(score);
	}
	
	public static int [][] operate(int[][] gearbox, int gearNo, int dir){
		if (gearNo-1 > 0 && gearbox[gearNo-1][2] != gearbox[gearNo][6]) gearbox = operateLeft(gearbox, gearNo-1, -dir);
		if (gearNo+1 < G+1 && gearbox[gearNo+1][6] != gearbox[gearNo][2]) gearbox = operateRight(gearbox, gearNo+1, -dir);
		rotate(gearbox, gearNo, dir);
		return gearbox;
	}
	
	public static int [][] operateLeft(int[][] gearbox, int gearNo, int dir){
		if (gearNo <= 0) return gearbox;
		if (gearbox[gearNo-1][2] != gearbox[gearNo][6]) gearbox = operateLeft(gearbox, gearNo-1, -dir);
		gearbox = rotate(gearbox, gearNo, dir);
		return gearbox;
	}
	
	public static int [][] operateRight(int[][] gearbox, int gearNo, int dir) {
		if (gearNo > G) return gearbox;
		if (gearbox[gearNo+1][6] != gearbox[gearNo][2]) gearbox = operateRight(gearbox, gearNo+1, -dir);
		gearbox = rotate(gearbox, gearNo, dir);
		return gearbox;
	}
	
	public static int [][] rotate(int[][] gearbox, int gearNo, int dir) {
		if (dir == 1) return clockwise(gearbox, gearNo, dir);
		return counterClockwise(gearbox, gearNo, dir);
	}
	
	public static int [][] clockwise(int[][] gearbox, int gearNo, int dir){
		int tmp = gearbox[gearNo][N-1];
		for (int i = N-1; i > 0; i--) {
			gearbox[gearNo][i] = gearbox[gearNo][i-1];
		}
		gearbox[gearNo][0] = tmp;
		return gearbox;
	}
	
	public static int [][] counterClockwise(int[][] gearbox, int gearNo, int dir){
		int tmp = gearbox[gearNo][0];
		for (int i = 0; i < N-1; i++) {
			gearbox[gearNo][i] = gearbox[gearNo][i+1];
		}
		gearbox[gearNo][N-1] = tmp;
		return gearbox;
	}
	
}

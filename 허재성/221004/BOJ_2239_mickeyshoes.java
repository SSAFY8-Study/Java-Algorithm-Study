// 133496KB	464ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static final int N = 9;
	public static boolean flag;
	
	public static List<Candidate> candidates;

	public static int[][] map;
	
	public static void printMap() {
		for(int[] a : map) {
			for(int b : a) {
				System.out.print(b);
			}
			System.out.println();
		}
			
	}
	
	
	public static void backTracking(int depth, int node) {
				
		if(flag) return;
		
		if(depth == candidates.size()) {
			flag = true;
			printMap();
			return;
		}
		boolean[] number = new boolean[N];
		
		Candidate cur = candidates.get(node);
		
		int x = cur.x/3 *3; int y = cur.y/3 *3;
		
		//square
		for(int r=x; r<x+3; r++) {
			for(int c=y; c<y+3; c++) {
				int idx = map[r][c];
				if(idx!=0)
					number[idx-1] = true;
			}
		}
		
		//vertical
		for(int c=0; c<N; c++) {
			int idx = map[cur.x][c];
			if(idx!=0)
				number[idx-1] = true;
				
		}
		//horizontal
		for(int r=0; r<N; r++) {
			int idx = map[r][cur.y];
			if(idx!=0)
				number[idx-1] = true;
		}
		//backtracking
		for(int i=0; i<number.length; i++) {
			if(!number[i]) {
				map[cur.x][cur.y] = i+1;
				backTracking(depth+1, node+1);
			}
		}
		
		map[cur.x][cur.y] = 0;
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		flag = false;
		map = new int[N][N];
		candidates = new ArrayList<Candidate>();
		for(int r=0; r<N; r++) {
			char[] infos = br.readLine().toCharArray();
			for(int c=0; c<N; c++) {
				map[r][c] = Character.getNumericValue(infos[c]);
				if(map[r][c] == 0)
					candidates.add(new Candidate(r,c));
			}
		}
		
		backTracking(0,0);

	}
	
	static class Candidate{
		int x;
		int y;
		
		public Candidate(int x, int y) {
			this.x=x; this.y=y;
		}
	}

}
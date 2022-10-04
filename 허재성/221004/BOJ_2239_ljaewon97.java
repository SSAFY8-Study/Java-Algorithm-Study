//  12944KB, 328ms

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int[] numRow, numCol, numSq;
	static Point[] zeroes;
	static int cnt0;
	static boolean solved;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[9][9];
		numRow = new int[9];
		numCol = new int[9];
		numSq = new int[9];
		zeroes = new Point[81];
		
		for(int i = 0; i < 9; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 9; j++) {
				map[i][j] = line.charAt(j) - '0';
				
				if(map[i][j] == 0) zeroes[cnt0++] = new Point(i, j);
				else {
					int bit = 1 << map[i][j];
					numRow[i] |= bit;
					numCol[j] |= bit;
					numSq[3*(i/3)+j/3] |= bit;
				}
			}
		}

		solve(0);
		
		System.out.println(sb);
	}
	
	static void solve(int nth) {
		if(solved) return;
		
		if(nth == cnt0) {
			solved = true;
			
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
			
			return;
		}
		
		int i = zeroes[nth].r;
		int j = zeroes[nth].c;
		int sq = zeroes[nth].sq;
		
		for(int k = 1; k <= 9; k++) {
			int bit = 1 << k;
			
			if((numRow[i] & bit) != 0 || (numCol[j] & bit) != 0 || (numSq[sq] & bit) != 0) continue;
			
			int tempR = numRow[i];
			int tempC = numCol[j];
			int tempS = numSq[sq];
			
			numRow[i] |= bit;
			numCol[j] |= bit;
			numSq[sq] |= bit;
			map[i][j] = k;
			
			solve(nth+1);
			
			if(solved) return;
			
			numRow[i] = tempR;
			numCol[j] = tempC;
			numSq[sq] = tempS;
		}
	}
}

class Point {
	int r;
	int c;
	int sq;
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
		this.sq = 3*(r/3) + c/3;
	}
}
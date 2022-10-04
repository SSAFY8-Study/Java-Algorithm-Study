// 294580KB	1376ms

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int [][] sudoku;
	static int [] pos;
	static List<int []> zeroList = new ArrayList<>();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	
	static boolean isFill(int cnt) {
		if(cnt == zeroList.size()) {
			return true;
		}
		pos = zeroList.get(cnt);
		int r = pos[0];
		int c = pos[1];
		for(int num = 1; num < 10; num++) {
			if(check(r, c, num) && isFill(cnt + 1)) {
				return true;
			}
		}
		sudoku[r][c] = 0;
		return false;
	}
	
	static boolean check(int r, int c, int num) {
		boolean [] check = new boolean[10];
		for(int i = 0; i < 9; i++) {
			check[sudoku[r][i]] = true;
			check[sudoku[i][c]] = true;
		}
		int nr = (r / 3) * 3;
		int nc = (c / 3) * 3;
		for(int i = nr; i < nr + 3; i++) {
			for(int j = nc; j < nc + 3; j++) {
				check[sudoku[i][j]] = true;
			}
		}
		if(!check[num]) {
			sudoku[r][c] = num;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		sudoku = new int[9][9];
		for(int r = 0; r < 9; r++) {
			String temp = input.readLine();
			for(int c = 0; c < 9; c++) {
				sudoku[r][c] = temp.charAt(c) - '0';
				if(sudoku[r][c] == 0) {
					zeroList.add(new int[] {r, c});
				}
			}
		}
		isFill(0);
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				output.append(sudoku[r][c]);
			}
			output.append("\n");
		}
		System.out.println(output);
		

	}

}
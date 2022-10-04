//  134364KB	424ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static List<Point> list;
	static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		
		map = new int[9][9];
		list = new ArrayList<>();
		
		for(int r=0; r<9; r++) {
			String str = input.readLine();
			for(int c=0; c<9; c++) {
				map[r][c] = str.charAt(c)-'0';
				if(map[r][c] == 0) {
					list.add(new Point(r, c)); // 빈 칸 저장
				}
			}
		} // 입력
		
		
		sudoku(0);
		

	}
	
	private static void sudoku(int depth) {
		
		if(list.size() == depth) {
			for(int r=0; r<9; r++) {
				for(int c=0; c<9; c++) {
					output.append(map[r][c]);
				}
				output.append("\n");
			}
			System.out.println(output);
			System.exit(0);
//			return;
		}
		
		// 빈칸 좌표를 뽑아서
		int r = list.get(depth).r;
		int c = list.get(depth).c;
		
		boolean[] check = new boolean[10];
		
		// 그 좌표 기준 가로를 쭉 검사할 때 빈칸이 아니라면 그 부분의 숫자를 true로 바꿔주기
		for(int i=0; i<9; i++) {
			if(map[r][i] != 0) {
				check[map[r][i]] = true;
			}
		}
		
		// 그 좌표 기준 세로를 검사할 때 이미 있는 숫자들 true로
		for(int i=0; i<9; i++) {
			if(map[i][c] != 0) {
				check[map[i][c]] = true;
			}
		}
		
		// 그 좌표가 들어있는 3*3 네모 검사
		// startR, startC는 그 네모의 가장 왼쪽 위 시작 좌표
		int startR = (r/3)*3;
		int startC = (c/3)*3;
		for(int i=startR; i<startR+3; i++) {
			for(int j=startC; j<startC+3; j++) {
				if(map[i][j] != 0) {
					check[map[i][j]] = true;
				}
			}
		}
		
		// 뽑은 좌표 (빈 좌표에) 값 넣기 (check 표시 안되어있는, 사용안한 숫자들 하나씩 사용하면서!)
		for(int i=1; i<=9; i++) {
			if(!check[i]) {
				map[r][c] = i;
				sudoku(depth+1);
				map[r][c] = 0; // 사용 안한 척
			}
		}
		
	}

	public static class Point{
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

}

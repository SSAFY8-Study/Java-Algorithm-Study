package daily221004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance 15392KB		548ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_2239_my {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int[][] sudoku = new int[9][9];
	static String ans;
	static boolean end = false;
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 9; i++) {
			char[] input = bf.readLine().toCharArray();
			for(int j = 0; j < 9; j++) {
				sudoku[i][j] = input[j] - '0';
				sb.append(9);
			}
			sb.append("\n");
		}
		
		ans = sb.toString();
		
		dfs(0);
//		System.out.println("+++++++++");
		System.out.println(ans);
		
		
	}
	
	static String sudokuToStr() {
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++)
				str.append(sudoku[i][j]);
			str.append("\n");
		}
		
		return str.toString();
	}
	
	static void dfs(int nth) {
		if(end)
			return;
		
		if(nth == 81) {	//	sudoku 완성됨
//			String result = sudokuToStr();
			
//			if(result.compareTo(ans) < 0)
//				ans = result;
			ans = sudokuToStr();
			end = true;
			
			return;
		}
		
		int y = nth / 9;
		int x = nth % 9;
		int num = sudoku[y][x];
		
		if(num != 0) 	//	이미 정해짐, 다음(nth + 1) 칸 채우러 감
			dfs(nth + 1);
		else {
			for(int i = 1; i <= 9; i++) {
				if(!isValid(y, x, i))	//	sudoku[y][x]에 i를 놓을 경우 문제가 발생
					continue;	//	i는 제끼고 i + 1을 놓아보기
				
				sudoku[y][x] = i;	//	i를 놓고
				dfs(nth + 1);		//	nth + 1번째 칸을 놓기
				sudoku[y][x] = 0;	//	i 놓은 것을 취소함
			}
		}
	}
	
	//	sudoku[y][x]에 num을 넣었을 때 sudoku에 문제 없는지 check
	static boolean isValid(int y, int x, int num) {
		for(int i = 0; i < 9; i++) {
			//	y행에 num이 이미 존재하거나, x열에 num이 이미 존재할 경우
			if(sudoku[y][i] == num || sudoku[i][x] == num)
				return false;
		}
		
		//	(y, x)를 포함하는 3 * 3 정사각형의 좌측 상단 꼭짓점 좌표 (luy, lux)
		int luy = (y / 3) * 3;
		int lux = (x / 3) * 3;
		
		for(int i = luy; i < luy + 3; i++) {
			for(int j = lux; j < lux + 3; j++)
				if(sudoku[i][j] == num)	//	3 * 3 정사각형 내에 이미 num이 존재할 경우
					return false;
		}
		
		//	세 조건 모두 통과한 경우 num을 넣어도 좋다.
		return true;
	}
}

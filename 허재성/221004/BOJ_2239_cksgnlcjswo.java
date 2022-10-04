//  15952KB 372ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int[][] map;
	static int N;
	static boolean[][] row,col,square;
			
	public static void main(String[] args) throws Exception {
		
		N = 9;
		map = new int[N][N];
		
		row = new boolean[N][N+1]; //i번째 row에 j번째 숫자있음
		col = new boolean[N][N+1];
		square = new boolean[N][N+1];
		
		for(int i=0;i<N;++i) {
			String r = br.readLine();
			for(int j=0;j<r.length();++j) {
				map[i][j] = r.charAt(j) - '0';
				
				row[i][map[i][j]] = true;
				col[j][map[i][j]] = true;
				
				square[i/3 * 3 + j/3 ][map[i][j]] = true;
			}
		}
		
		func(0);
	}
	
	static void func(int nth) {
		if(nth == 81) {
			
			for(int i=0;i<N;++i) {
				for(int j=0;j<N;++j) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		int r = nth / 9;
		int c = nth % 9;
		
		if(map[r][c] != 0) {
			func(nth+1);
			return;
		}
		
		for(int i=1;i<=9;++i) {
			
			if(!row[r][i] && !col[c][i] && !square[r/3*3 + c / 3][i]) {
				row[r][i] = true;
				col[c][i] = true;
				square[r/3*3+c/3][i] = true;
				map[r][c] = i;
				func(nth+1);
				row[r][i] = false;
				col[c][i] = false;
				square[r/3*3+c/3][i] = false;
				map[r][c] = 0;
			}
		}
	}
}

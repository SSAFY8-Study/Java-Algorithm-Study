import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n9336_NQueen {
	static int[][] map;
	static int n, count;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		n = Integer.parseInt(str);
		
		map = new int[n][n];
		queen(0, 0);
		
		System.out.println(count);
	}
	
	public static void queen(int num, int row) {
		if(num == n) {
			count++;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(check(row, i)) {
				map[row][i] = 1;
				queen(num + 1, row + 1);
				map[row][i] = 0;
			}
		}
		
	}

	public static boolean check(int x, int y) {
		
		for(int i = 0; i < n; i++) {
			if(map[x][i] == 1) return false;
		}
		for(int i = 0; i < n; i++) {
			 if(map[i][y] == 1) return false;
		 } 
		for(int i = 0; x+i<n && y+i<n; i++) {
			if(map[x+i][y+i] == 1) return false;
		}
		for(int i = 0; x+i<n && y-i>=0; i++) {
			if(map[x+i][y-i] == 1) return false;
		}
		for(int i = 0; x-i>=0 && y+i<n; i++) {
			if(map[x-i][y+i] == 1) return false;
		}
		for(int i = 0; x-i>=0 && y-i>=0; i++) {
			if(map[x-i][y-i] == 1) return false;
		}
		return true;
		 
	}
}

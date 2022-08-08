import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class n2468_안전지역 {

	static int max;
	static int maxCnt;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();

		int n = Integer.parseInt(str);
		int[][] map = new int[n][n];
		int[][] copy = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		
		for(int k = 0; k < max; k++) {
			int count = 0;
			CopyMap(copy, map, n);
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					
					if(copy[i][j] > k) {
						count++;
						copy[i][j] = 0;
						check(i, j, k, n, copy);
					}
				}
			}
			maxCnt = Math.max(maxCnt, count);
		}
		
		System.out.println(maxCnt);
	}
	
	static void CopyMap(int[][] copy, int[][] map, int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
	
	static void check(int x, int y, int k, int n, int[][] map) {
		for(int h = 0; h < 4; h++) {
			int nx = x + dx[h];
			int ny = y + dy[h];
			
			if(nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] > k) {
				map[nx][ny] = 0;
				check(nx, ny, k, n, map);
			}
		}
	}

}

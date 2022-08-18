import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n13565_침투 {

	static int n, m;
	static boolean check;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
//		copy = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			str = bf.readLine();
			for(int j  = 0; j < m; j++) {
				map[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		//맨위 행의 인덱스를 dfs로 넘겨준다, 어차피 닿기만 하면 되는 것이므로 따로 방문한 것을 되돌리지는 않는다
		for(int i = 0; i < m; i++) {
			if(map[0][i] == 0) {
//				copyMap();
				map[0][i] = 1;
				dfs(0, i);
			}
		}
		
		//맨 아래까지 닿았다면 예스 출력
		if(check) System.out.println("YES");
		else System.out.println("NO");

	}
	
	static void dfs(int x, int y) {
		for(int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 4방 탐색으로 내려가고(사실 3방이면 되겠다) 맨 아래 행을 만나면 체크해준다
			if(nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 0) {
				map[nx][ny] = 1;
				if(nx == n-1) check = true;
				dfs(nx, ny);
			}
		}
	}
	
//	static void copyMap() {
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				copy[i][j] = map[i][j];
//			}
//		}
//	}

}

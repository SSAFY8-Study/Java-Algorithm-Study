import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class n1987 {

	static int n, m, max = 1;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static char[][] map;
	static Map<Character, Integer> hMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			str = bf.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		DFS(0, 0, 0);
		
		System.out.println(max);
	}
	
	static void DFS(int x, int y, int cnt) {
		if(hMap.get(map[x][y]) != null) {
			max = Math.max(max, cnt);
			return;
		}else {
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
					hMap.put(map[x][y], 1);
					DFS(nx, ny, cnt+1);
					hMap.remove(map[x][y]);
				}
			}
		}
		
		
	}

}

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class n14502_연구소 {

	static int n, m, max = Integer.MIN_VALUE;
	static int[] dx = {0, 1, -1, 0};
	static int[] dy = {1, 0, 0, -1};
	static int[][] map, copy;
	static List<Point> virusList;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		copy = new int[n][m];
		virusList = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			str = bf.readLine();
			st = new StringTokenizer(str, " ");
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virusList.add(new Point(i, j));
			}
		}
		
		dfs(0);
		
		System.out.println(max);
	}
	
	static void virusBFS() {
		Queue<Point> q = new LinkedList<>();
		
		for(Point v : virusList) q.add(v);
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < m && copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					q.add(new Point(nx, ny));
				}
			}
		}
		
		safePlace();
	}
	
	static void safePlace() {
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copy[i][j] == 0) count++;
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}
//		System.out.println();
		max = Math.max(max, count);
	}
	
	// 벽 세우기
	static void dfs(int b) {
		if(b == 3) {
			copyMap();
			virusBFS();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(b+1);
					map[i][j] = 0;
				}
				
			}
		}
	}
	
	static void copyMap() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

}

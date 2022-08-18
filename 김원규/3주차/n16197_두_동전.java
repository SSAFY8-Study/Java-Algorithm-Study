import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n16197 {

	static int n, m, min = Integer.MAX_VALUE;
	static char[][] map;
	static Point coin1, coin2;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		
		boolean check = false;
		for(int i = 0; i < n; i++) {
			str = bf.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'o') {
					if(!check) {
						coin1 = new Point(i, j);
						check = !check;
					}else coin2 = new Point(i, j);
				}
			}
		}
		
		int res = BFS();
		
		System.out.println(res);
	}
	
	static int BFS() {
		Queue<Point[]> q = new LinkedList<>();
		
		Point[] coins = new Point[2];
		coins[0] = coin1;
		coins[1] = coin2;
		
		q.add(coins);
		int count = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			
			if(count == 10) break;
			
			count++;
			for(int l = 0; l < len; l++) {
				Point[] temp = q.poll();
				
				for(int i = 0; i < 4; i++) {
					int nx1 = temp[0].x + dx[i];
					int ny1 = temp[0].y + dy[i];
					int nx2 = temp[1].x + dx[i];
					int ny2 = temp[1].y + dy[i];
					
					if((check(nx1, ny1) != check(nx2, ny2))) {
//						System.out.println(check(nx1, ny1));
//						System.out.println(check(nx2, ny2));
//						System.out.println(!(check(nx1, ny1) && check(nx2, ny2)));
						return count;
					}
					else {
						Point c1 = temp[0], c2 = temp[1];
						if(check(nx1, ny1)) {
							if(map[nx1][ny1] != '#') c1 = new Point(nx1, ny1);
						}
						if(check(nx2, ny2)) {
							if(map[nx2][ny2] != '#') c2 = new Point(nx2, ny2);
						}
						
						q.add(new Point[] {c1, c2}); 
					}
				}
			}
		}
		
		return -1;
	}
	
	static boolean check(int nx, int ny) {
		if(nx >= 0 && ny >= 0 && nx < n && ny < m) return true;
		return false;
	}

}

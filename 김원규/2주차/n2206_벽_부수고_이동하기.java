import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n2206_벽_부수고_이동하기 {
	
	static class NewPoint{
		int x;
		int y;
		int m;
		boolean b;
		
		public NewPoint(int x, int y, int m, boolean b) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.b = b;
		}
	}

	static int n, m;
	static int[] dx = {0, 1, -1, 0};
	static int[] dy = {1, 0, 0, -1};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][m+1];
		
		for(int i = 1; i <= n; i++) {
			str = bf.readLine();
			for(int j = 1; j <= m; j++) {
				map[i][j] = Character.getNumericValue(str.charAt(j-1));
			}
		}
		
		int result = BFS();
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1; j <= m; j++) System.out.print(map[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println();
		
		System.out.println(result);
	}
	
	static int BFS() {
		Queue<NewPoint> q = new LinkedList<>();
		q.add(new NewPoint(1, 1, 1, false));
		
		while(!q.isEmpty()) {
			NewPoint temp = q.poll();
			
			if(temp.x == n && temp.y == m) return temp.m;
			
			for(int i = 0; i < 4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				//돌때마다 temp 복제가 필요... 안그러면 벽부수는 변수를 공유함
				if(nx > 0 && ny > 0 && nx <= n && ny <= m) {
					NewPoint temp1 = new NewPoint(temp.x, temp.y, temp.m+1, temp.b);
					if(map[nx][ny] == 0 || map[nx][ny] == 3) {					// 길이면 가고
						if(map[nx][ny] == 3 && temp1.b) continue;
						temp1.x = nx;
						temp1.y = ny;
						q.add(temp1);
						map[nx][ny] = 2;					// 2는 지나온 길
						if(temp.b) map[nx][ny] = 3;			// 혹시 벽을 부수고 0을 지나왔다면 3처리, 벽을 부수지 않았다면 3도 지나갈 수 있음
					}else if(map[nx][ny] == 1) {			// 혹시 벽인데
						if(temp1.b) continue;				// 벽을 부순적이 있다면 그냥 넘어가고
						else {								// 아직 부순적이 없다면 한번 부수고...
							temp1.b = true;					// 벽을 부순적이 있다는 표시를 남김
							temp1.x = nx;
							temp1.y = ny;
							q.add(temp1);
						}
					}
				}
			}	
		}
		return -1;
	}

}

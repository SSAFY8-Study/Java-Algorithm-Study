package study2_08;

/**
 * @author seungheecho
 * @see https://www.acmicpc.net/problem/2206
 * @performance 141116	616
 * @memo charAt(i)-'0'으로 int로 만들 수 있다
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		int[][] dist = new int[n][m];
		boolean[][][] visited = new boolean[2][n][m];
		Queue<int[]> q = new LinkedList<>();
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		if(n-1 == 0 && m-1 == 0){
            System.out.print(1);
            return;
        }
		
		q.offer(new int[]{0, 0, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int[] d: dir) {
				int nx = cur[0] + d[0];
				int ny = cur[1] + d[1];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
				
				if(map[nx][ny]==1) {
					if(cur[2] == 0 && !visited[1][nx][ny]){
                        visited[cur[2]][nx][ny] = true;    
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1; 
                        q.offer(new int[]{nx, ny, 1});  
                    }
				}else {
					if(!visited[cur[2]][nx][ny]){
                        visited[cur[2]][nx][ny] = true;   
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                        q.offer(new int[]{nx, ny, cur[2]}); 
                    }
				}
				
				if(nx==n-1 && ny==m-1) {
					System.out.println(dist[nx][ny]+1);
					return;
				}
			}
		}
		System.out.println(-1);

	}

}

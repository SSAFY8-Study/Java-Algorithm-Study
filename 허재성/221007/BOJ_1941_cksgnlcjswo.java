// 216144KB	556ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1941_cksgnlcjswo {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static char[][] map;
	static int sy,sx,ans;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int[] order; 
	
 	public static void main(String[] args) throws Exception {
 		
 		map = new char[5][5];
 	 		
 		for(int i=0;i<5;++i) {
 			map[i] = br.readLine().toCharArray();
 		}
 		
// 		for(char[] row : map) {
// 			System.out.println(Arrays.toString(row));
// 		}
 		
 		order = new int[7];
 		
 		Combination(0,0);
 		System.out.println(ans);
 	}
 	//r,c에서 s,y개수만큼있을 때
 	static void Combination(int nth, int start) {
 		if(nth == 7) {
 			
 			if(possible(order) == true) ans++;
 			return;
 		}
 		
 		for(int i=start;i<25;++i) {
 			order[nth] = i;
 			Combination(nth+1,i+1);
 		}
 	}
 	
 	static boolean possible(int[] order) {
 		
 		int count = 0;
 		//내가 갈수있는 좌표표시
 		boolean[][] cand = new boolean[5][5];
 		
 		for(int i=0;i<order.length;++i) {
 			int y = order[i] / 5;
 			int x = order[i] % 5;
 			if(map[y][x] == 'Y') count++;
 			cand[y][x] = true;
 		}
 		//이다솜 개수 제한
 		if(count >= 4) return false;
 		
 		boolean[][] visited = new boolean[5][5];
 		Queue<int[]> q = new LinkedList<>();
 			
 		int sy = order[0] / 5;
 		int sx = order[0] % 5;
 		int cnt = 1; //후보지점개수
 		
 		q.add(new int[] {sy,sx}); //y,x
 		visited[sy][sx] = true;
 		
 		while(!q.isEmpty()) {
 			int[] cur = q.poll();
 			
	
 			for(int i=0;i<4;++i) {
 				int ny = cur[0] + dy[i];
 				int nx = cur[1] + dx[i];
 				
 				if(isIn(ny,nx) == false) continue;
 				if(visited[ny][nx] == true || cand[ny][nx] == false) continue;
 				cnt++;
 				visited[ny][nx] = true;
 				q.offer(new int[] {ny,nx});
 				
 			}
 		}
 		
 		return cnt == 7;
 	}
 	
 	static boolean isIn(int r, int c) {
 		return r >= 0 && r < 5 && c >=0 && c< 5;
 	}
}
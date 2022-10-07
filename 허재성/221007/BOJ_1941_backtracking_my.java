import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance 140312KB	380ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_1941_backtracking_my {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static char[][] map = new char[5][5];
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) 
				pointArr[i * 5 + j] = new Point(i, j);
		}
		
		for(int i = 0; i < 5; i++)
			map[i] = bf.readLine().toCharArray();
		
		comb7(0, 0, 0, 0, new int[7]);
		System.out.println(ans);
	}
	
	static void comb7(int nth, int start, int sNum, int yNum, int[] selected) {
		if(nth == 7) {
			if(isOk(selected))
				ans++;
			return;
		}
		
		if(yNum >= 4)
			return;
		
		for(int i = start; i < 5 * 5; i++) {
			Point p = pointArr[i];
			int y = p.y;
			int x = p.x;
			int nSNum = sNum;
			int nYNum = yNum;
			
			if(map[y][x] == 'S')
				nSNum++;
			else
				nYNum++;
			
			if(nYNum >= 4)
				continue;
			
			selected[nth] = i;
			comb7(nth + 1, i + 1, nSNum, nYNum, selected);
		}
	}
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < 5) && (0 <= x && x < 5);
	}
	
	static boolean isOk(int[] selected) {
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] unvisited = new boolean[5][5];
		int cnt = 0;
		
		for(int i = 0; i < 7; i++) {
			Point p = pointArr[selected[i]];
			int y = p.y;
			int x = p.x;
			unvisited[y][x] = true;
		}
		
		Point sp = pointArr[selected[0]];
		int sy = sp.y;
		int sx = sp.x;
		unvisited[sy][sx] = false;
		cnt++;
		q.offer(new Point(sy, sx));
		
		while(!q.isEmpty()) {
			Point cp = q.poll();
			int cy = cp.y;
			int cx = cp.x;
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(!isIn(ny, nx) || !unvisited[ny][nx])
					continue;
				
				unvisited[ny][nx] = false;
				cnt++;
				q.offer(new Point(ny, nx));
			}
		}
		
		return cnt == 7;
	}
	
	static class Point {
		int y;
		int x;
		
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static Point[] pointArr = new Point[5 * 5];
}
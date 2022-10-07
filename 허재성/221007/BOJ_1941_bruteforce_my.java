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
 * @performance 14540KB	580ms
 * @category #
 * @memo
 * @etc
 * 
 */

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static char[][] map = new char[5][5];
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				pointArr[cnt++] = new Point(i, j);
		}

		for (int i = 0; i < 5; i++)
			map[i] = bf.readLine().toCharArray();
		
		comb7(0, 0, new int[7]);
		System.out.println(ans);
	}

	static void comb7(int nth, int start, int[] selected) {
		if (nth == 7) {
			if (isOk(selected))
				ans++;

			return;
		}

		for (int i = start; i < 5 * 5; i++) {
			selected[nth] = i;
			comb7(nth + 1, i + 1, selected);
		}
	}

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < 5) && (0 <= x && x < 5);
	}

	static boolean isOk(int[] selected) {
		Point sp = pointArr[selected[0]];
		int sy = sp.y;
		int sx = sp.x;
		int sNum = 0;
		int yNum = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] unvisited = new boolean[5][5];
		int cnt = 0;	//	방문한 점 개수(7개가 되어야 함)
		
		for(int i = 0; i < 7; i++) {
			Point p = pointArr[selected[i]];
			int y = p.y;
			int x = p.x;
			unvisited[y][x] = true;
		}
		q.offer(new int[] {sy, sx});
		unvisited[sy][sx] = false;
		if(map[sy][sx] == 'S')
			sNum++;
		else
			yNum++;
		
		cnt++;
		
		while(!q.isEmpty()) {
			int[] cout = q.poll();
			int cy = cout[0];
			int cx = cout[1];
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
			
				if(!isIn(ny, nx) || !unvisited[ny][nx])
					continue;
				
				unvisited[ny][nx] = false;
				cnt++;
				if(map[ny][nx] == 'S')
					sNum++;
				else
					yNum++;
				
				q.offer(new int[] {ny, nx});
			}
		}
		
		if(cnt != 7)
			return false;
		if(sNum < yNum)
			return false;
	
		return true;
	}

	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static Point[] pointArr = new Point[25];
}
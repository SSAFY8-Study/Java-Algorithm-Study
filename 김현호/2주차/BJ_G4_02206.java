package bj.g4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/2206
 * @performance
 * @category @memo input 받을 때, 공백있는지 없는지 확인하기. 최단거리는 bfs. edge 케이스 생각하기. 여기서는
 *           n,m 모두 1일 때, 1이 나와야됨.
 * 
 */

public class BJ_G4_02206 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n, m, min;
	static int[][] map;
	static int[][] isVisited;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	static class Coordinate {
		int x;
		int y;
		int breakCnt;

		public Coordinate(int x, int y, int breakCnt) {
			this.x = x;
			this.y = y;
			this.breakCnt = breakCnt;
		}

	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

	static void bfs(int x, int y) {
		// edge 예외케이스.
		if (n == 1 && m == 1) {
			min = 1;
			return;
		}

		Queue<Coordinate> q = new ArrayDeque<Coordinate>();
		q.offer(new Coordinate(x, y, 0));
		isVisited[x][y] = 1;
		while (!q.isEmpty()) {
			min++;
			int cSize = q.size();
			for (int qSize = 0; qSize < cSize; qSize++) {
				Coordinate polled = q.poll();
				int r = polled.x;
				int c = polled.y;
				int breakCnt = polled.breakCnt;
				for (int i = 0; i < 4; i++) {
					int nr = r + deltas[i][0];
					int nc = c + deltas[i][1];
					if (nr == n - 1 && nc == m - 1) {
						min++;
						return;
					}
					if (isIn(nr, nc)) {
						if (map[nr][nc] == 0) { // 길일 때,
							if (breakCnt == 0) { // 부숴본 경험이 없으면 0이나 -1일 때 갈수있음.
								if (isVisited[nr][nc] <= 0) {
									q.offer(new Coordinate(nr, nc, breakCnt));
									isVisited[nr][nc] = 1;
								}
							} else { // 부숴본 경험이 있으면 0일때만 갈 수 있음.
								if (isVisited[nr][nc] == 0) {
									q.offer(new Coordinate(nr, nc, breakCnt));
									isVisited[nr][nc] = -1; // 부숴봤던 애들 표시는 -1.
								}
							}
						} else { // 벽일 때,
							if (breakCnt == 0) {
								q.offer(new Coordinate(nr, nc, breakCnt + 1));
								isVisited[nr][nc] = -1;
							}
						}
					}
				} // for
			} // for
		} // while
		min = -1;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		n = Integer.parseInt(tokens.nextToken());
		m = Integer.parseInt(tokens.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = input.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		} // 입력 끝.

		min = 0;
		isVisited = new int[n][m];
		bfs(0, 0);

		output.append(min);
		System.out.print(output);
	}

}
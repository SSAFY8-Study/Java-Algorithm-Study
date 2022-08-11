package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5427_불 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static Queue<int[]> q;
	static int T, w, h, time = 0;
	static String[][] map;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우
	static boolean[][] visited;

	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());

		TC: for (int t = 0; t < T; t++) {
			tokens = new StringTokenizer(input.readLine());
			w = Integer.parseInt(tokens.nextToken());
			h = Integer.parseInt(tokens.nextToken());
			map = new String[h][];
			visited = new boolean [h][w];
			time = 0;
			q = new LinkedList<int[]>();

			int pR = 0, pC = 0;
			for (int r = 0; r < h; r++) {
				map[r] = input.readLine().split("");
				for (int c = 0; c < w; c++) {
					if (map[r][c].equals("@")) {
						pR = r;
						pC = c;
					} else if (map[r][c].equals("*"))
						q.add(new int[] { r, c, 1 });
				}
			}
			q.add(new int[] { pR, pC, 0 });
			
			while (q.size() > 0) {

				time += 1;
				int size = q.size();
				for (int j = 0; j < size; j++) {
					int[] position = q.poll();
					if (position[2] == 1) { // 불
						for (int i = 0; i < deltas.length; i++) {
							int nr = position[0] + deltas[i][0];
							int nc = position[1] + deltas[i][1];
							if (nr < 0 || nr >= h || nc < 0 || nc >= w)
								continue;
							if ((map[nr][nc].equals(".") || map[nr][nc].equals("@"))&&!visited[nr][nc]) {
								map[nr][nc] = "*";
								visited[nr][nc] = true;
								q.add(new int[] { nr, nc,1 });
							}
						}

					} else if (position[2] == 0) {
						for (int i = 0; i < deltas.length; i++) {
							int nr = position[0] + deltas[i][0];
							int nc = position[1] + deltas[i][1];
							if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
								output.append(time + "\n");
								continue TC;
							}
							if (map[nr][nc].equals(".")&&!visited[nr][nc]) {
								visited[nr][nc] = true;
								q.add(new int[] { nr, nc,0 });
							}

						}
					}
				}
			}
			output.append("IMPOSSIBLE\n");

		}
		System.out.println(output);
	}

}

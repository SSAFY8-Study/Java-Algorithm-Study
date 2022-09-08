package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link
 * @performace 148152	356
 * @category
 * @memo
 */

public class BJ_G4_16197_두동전 {
	static class Points {
		int ax;
		int ay;
		int bx;
		int by;
		int count;
		
		public Points() {
		}

		public Points(int ax, int ay, int bx, int by, int count) {
			super();
			this.ax = ax;
			this.ay = ay;
			this.bx = bx;
			this.by = by;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Points [ax=" + ax + ", ay=" + ay + ", bx=" + bx + ", by=" + by + ", count=" + count + "]";
		}
		
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static char[][] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		reader = new BufferedReader(new StringReader(str));

		StringTokenizer tokens = new StringTokenizer(reader.readLine());

		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		graph = new char[N][M];
		Points coins = new Points();
		int count = 0;
		for (int n = 0; n < N; n++) {
			String input = reader.readLine();
			for (int m = 0; m < M; m++) {
				graph[n][m] = input.charAt(m);
				if (graph[n][m] == 'o') {
					if (count == 0) {
						coins.ax = n;
						coins.ay = m;
						count++;
					}else {
						coins.bx = n;
						coins.by = m;
					}
				}
			}
		}
		System.out.println(BFS(coins));
	}

	static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	static int BFS(Points coins) {
		boolean[][][] visited = new boolean[graph.length][graph[0].length][2];
		visited[coins.ax][coins.ay][0] = true;
		visited[coins.bx][coins.by][1] = true;
		Queue<Points> q = new LinkedList<>();
		q.add(coins);
		while (!q.isEmpty()) {
			Points nodes = q.poll();
			if (nodes.count >= 10)
				return -1;
			for (int d = 0; d < deltas.length; d++) {
				int ax = nodes.ax + deltas[d][0];
				int ay = nodes.ay + deltas[d][1];
				int bx = nodes.bx + deltas[d][0];
				int by = nodes.by + deltas[d][1];

				if (!isIn(ax, ay) && isIn(bx, by)) {
					return nodes.count + 1;
				} else if (isIn(ax, ay) && !isIn(bx, by)) {
					return nodes.count + 1;
				} else if (isIn(ax, ay) && isIn(bx, by)) {
					if (!isVisited(ax, ay, bx, by, visited)) {
						visited[nodes.ax][nodes.ay][0] = true;
						visited[nodes.bx][nodes.by][1] = true;
						Points newCoins = new Points();
						newCoins.count = nodes.count + 1;
						if (graph[ax][ay] != '#') {
							newCoins.ax = ax;
							newCoins.ay = ay;
						} else {
							newCoins.ax = nodes.ax;
							newCoins.ay = nodes.ay;
						}
						if (graph[bx][by] != '#') {
							newCoins.bx = bx;
							newCoins.by = by;
						} else {
							newCoins.bx = nodes.bx;
							newCoins.by = nodes.by;
						}
						q.add(newCoins);
					}
				}

			}

		}
		return -1;
	}

	private static boolean isIn(int nx, int ny) {
		return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph[0].length;
	}

	private static boolean isVisited(int ax, int ay, int bx, int by, boolean[][][] visited) {
		return visited[ax][ay][0] && visited[bx][by][1];
	}

	static String str = "5 3\r\n" + 
			"###\r\n" + 
			".o.\r\n" + 
			"###\r\n" + 
			".o.\r\n" + 
			"###";
}

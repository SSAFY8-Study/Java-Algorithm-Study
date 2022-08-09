package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_02206_벽부수고이동하기 {
	static class Node {
		int x;
		int y;
		int cnt;
		boolean isBroken;
		
		public Node(int x, int y, int cnt, boolean isBroken) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.isBroken = isBroken;
		}
	}
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][][] visited;
	static char[][] graph;
	static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 1, false));
		visited[0][0][0] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int nx = node.x;
			int ny = node.y;
			if (nx == graph.length - 1 && ny == graph[0].length - 1) {
				System.out.println(node.cnt);
				return;
			}
			for (int d = 0; d < deltas.length; d++) {
				int dx = nx + deltas[d][0];
				int dy = ny + deltas[d][1];
				if (isIn(dx, dy)) {
					if (graph[dx][dy] == '0' && !visited[dx][dy][0] && !node.isBroken) {
						visited[dx][dy][0] = true;
						q.add(new Node(dx, dy, node.cnt + 1, false));
					} else if (graph[dx][dy] == '0' &&  node.isBroken && !visited[dx][dy][1]) {
						visited[dx][dy][1] = true;
						q.add(new Node(dx, dy, node.cnt + 1, true));
					} else if (graph[dx][dy] == '1' && !node.isBroken) {
						q.add(new Node(dx, dy, node.cnt + 1, true));
					}
				}
			}
		}
		System.out.println(-1);
	}
	
	static boolean isIn(int nx, int ny) {
		return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph[0].length;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		
		graph = new char[N][];
		for (int n = 0; n < N; n++) {
			graph[n] = reader.readLine().toCharArray();
		}
		
		visited = new boolean[N][M][2];
		
		BFS();
		
	}
	
}

package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/16234
 * @performance 295656	608
 * @category BFS
 * @memo
 */

public class BJ_G5_16234_인구이동 {
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static int N;
	static int L;
	static int R;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
//		reader = new BufferedReader(new StringReader(str));
		
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		graph = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(reader.readLine());
			for (int c = 0; c < N; c++) {
				graph[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		
		int result = 0;
		while (true) {
			boolean[][] visited = new boolean[N][N];
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						BFS(i, j, visited);
						count++;
					}
				}
			}
			if (count == N * N) {
				break;
			}
			result++;
		}
		System.out.println(result);
	}
	
	static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static void BFS(int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		Queue<Node> q = new LinkedList<>();
		List<Node> tmp = new ArrayList<>();
		int sum = 0;
		q.add(new Node(x, y));
		while (!q.isEmpty()) {
			Node node = q.poll();
			tmp.add(node);
			sum += graph[node.x][node.y];
			for (int d = 0; d < deltas.length; d++) {
				int nx = node.x + deltas[d][0];
				int ny = node.y + deltas[d][1];
				
				if (isIn(nx, ny) && !visited[nx][ny] && canOpen(node.x, node.y, nx, ny)) {
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
				}
			}
		}
		int avg = sum / tmp.size();
		for (Node node : tmp) {
			graph[node.x][node.y] = avg;
		}
	}
	
	static boolean canOpen(int x, int y, int nx, int ny) {
		int abs = Math.abs(graph[x][y] - graph[nx][ny]);
		return abs >= L && abs <= R;
	}
	
	static boolean isIn(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N;
	}
	
	static String str = "2 20 50\r\n" + 
			"50 30\r\n" + 
			"30 40";
}

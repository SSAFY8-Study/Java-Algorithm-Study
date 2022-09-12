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
 * @link https://www.acmicpc.net/problem/01987
 * @performance 291784	1020
 * @category DFS
 * @memo
 */


public class BJ_G4_01987_알파벳 {
	static class Node {
		int x;
		int y;
		int count;
		int alph;

		public Node(int x, int y, int count, int alph) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.alph = alph;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", count=" + count + ", alph=" + alph + "]";
		}
		
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static char[][] graph;

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new StringReader(str));
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		int R = Integer.parseInt(tokens.nextToken());
		int C = Integer.parseInt(tokens.nextToken());

		graph = new char[R][];
		for (int r = 0; r < R; r++) {
			graph[r] = reader.readLine().toCharArray();
		}
		Node start = new Node(0, 0, 1, 0);
		start.alph |= 1 <<(graph[0][0] - 'A');
		DFS(start);
		System.out.println(result);
	}

	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int result = 0;
	static void DFS(Node node) {
		result = Math.max(result, node.count);
		for (int d = 0; d < deltas.length; d++) {
			int nx = node.x + deltas[d][0];
			int ny = node.y + deltas[d][1];
			if (isIn(nx, ny) && (node.alph & 1 << (graph[nx][ny] - 'A')) == 0){
				DFS(new Node(nx, ny, node.count + 1, node.alph | 1 << (graph[nx][ny] - 'A')));
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph[0].length;
	}

	static String str = "5 5\r\n" + 
			"IEFCJ\r\n" + 
			"FHFKC\r\n" + 
			"FFALF\r\n" + 
			"HFGCF\r\n" + 
			"HMCHH";
}

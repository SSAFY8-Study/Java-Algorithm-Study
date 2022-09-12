package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @category BFS
 * @performance 11864kb	100ms
 * @memo 전형적인 BFS문제 - 큐를 통해서 구현
 */

public class BJ_S1_02667_단지번호붙이기 {
	static class Node {
		int x;
		int y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int[][] deltas = {{0, 1}, {0, -1} , {1, 0}, {-1, 0}};
	static List<Integer> sizes = new ArrayList<Integer>();
	static void BFS(int x, int y, char[][] graph) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		graph[x][y] = '0';
		int count = 1;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int nx = node.x;
			int ny = node.y;
			for (int i = 0; i < deltas.length; i++) {
				int dx = nx + deltas[i][0];
				int dy = ny + deltas[i][1];
				if (isIn(dx, dy, graph.length) && graph[dx][dy] != '0') {
					count++;
					graph[dx][dy] = '0';
					q.add(new Node(dx, dy));
				}
			}
		}
		sizes.add(count);
	}
	
	static boolean isIn(int dx, int dy, int n) {
		return 0 <= dx && dx < n && 0 <= dy && dy < n;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
//		reader = new BufferedReader(new StringReader(str));
		int N = Integer.parseInt(reader.readLine());
		char[][] graph = new char[N][];
		
		for(int r = 0; r < N; r++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			graph[r] = tokens.nextToken().toCharArray();
		}
		//input
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (graph[r][c] != '0') {
					BFS(r, c, graph);
				}
			}
		}
		
		System.out.println(sizes.size());
		Collections.sort(sizes);
		for (int s : sizes)
			System.out.println(s);
		
	}
	
	static String str = "7\r\n" + 
			"0110100\r\n" + 
			"0110101\r\n" + 
			"1110101\r\n" + 
			"0000111\r\n" + 
			"0100000\r\n" + 
			"0111110\r\n" + 
			"0111000";
}

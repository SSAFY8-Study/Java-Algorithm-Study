package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kyulee
 * @link https://www.acmicpc.net/problem/13549
 * @category BFS
 * @performance 21780	188
 * @memo
 */

public class BJ_G5_13913_숨바꼭질3 {
	static class Node {
		int n;
		int count;
		public Node(int n, int count) {
			super();
			this.n = n;
			this.count = count;
		}
	}
	static int result = Integer.MAX_VALUE;
	static int[] visited = new int[100001];
	static void BFS(int n, final int target) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(n, 1));
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.n == target) {
				result = Math.min(result, node.count);
				continue;
			}
			if (visited[node.n] > node.count) {				
				visited[node.n] = node.count;
				if (isIn(node.n + 1) && visited[node.n + 1] > node.count + 1) {
					q.add(new Node(node.n + 1, node.count + 1));
				}
				if (isIn(node.n - 1) && visited[node.n - 1] > node.count + 1) {
					q.add(new Node(node.n - 1, node.count + 1));
				}
				if (isIn(node.n * 2) && visited[node.n * 2] > node.count) {
					q.add(new Node(node.n * 2, node.count));
				}
			}
		}
	}
	
	static boolean isIn(int n) {
		return 0 <= n && n < 100001;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		
		for (int i = 0; i < visited.length; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
		
		BFS(N, K);
		System.out.println(result - 1);
	}
}

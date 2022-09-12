package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kyulee
 * @link https://www.acmicpc.net/problem/18352
 * @performance 353224	1480
 * @category BFS
 * @memo 만약 이문제에서 가중치가 있었다면? 우리는 다익스트라로 시도를 해볼 수 있을 거 같다.
 *
 */

public class BJ_S2_18352_특정거리의도시찾기 {
	static class Node {
		int n;
		int count;
		public Node(int n, int count) {
			this.n = n;
			this.count = count;
		}
	}
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static Map<Integer, List<Integer>> graph;
	static List<Integer> result = new ArrayList<>();
	static StringBuilder output = new StringBuilder();
	static void BFS(int start, int k) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[300001];
		q.add(new Node(start, 0));
		visited[start] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if (node.count == k) result.add(node.n);
			if (node.count > k) return;
			if (graph.containsKey(node.n)) {
				for (int nbr : graph.get(node.n)) {
					if (!visited[nbr]) {
						q.add(new Node(nbr, node.count + 1));
						visited[nbr] = true;
					}
				}
			}
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
//		reader = new BufferedReader(new StringReader(str));
		StringTokenizer tokens = new StringTokenizer(reader.readLine());

		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		int X = Integer.parseInt(tokens.nextToken());
		graph = new HashMap<Integer, List<Integer>>();
		for (int m = 0; m < M; m++) {
			tokens = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			if (graph.containsKey(a)) {
				List<Integer> list = graph.get(a);
				list.add(b);
			}else {
				List<Integer> list = new ArrayList<>();
				list.add(b);
				graph.put(a, list);
			}
		}
		
		BFS(X, K);
		if (!result.isEmpty()) {
			Collections.sort(result);
			for (int nbr : result) {
				output.append(String.format("%d\n", nbr));
			}
			System.out.print(output);			
		}else {
			System.out.println(-1);
		}
	}

	static String str = "4 4 1 1\r\n" + 
			"1 2\r\n" + 
			"1 3\r\n" + 
			"2 3\r\n" + 
			"2 4";
}

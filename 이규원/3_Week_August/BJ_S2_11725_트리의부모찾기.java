package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/11725
 * @performance 77408	656
 * @category BFS
 * @memo
 */

public class BJ_S2_11725_트리의부모찾기 {
	static Map<Integer, List<Integer>> graph;
	static int[] parent;
	static int N;
	static void BFS() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		while (!q.isEmpty()) {
			int nbr = q.poll();
			if (graph.containsKey(nbr)) {
				for (int node : graph.get(nbr)) {
					if (parent[node] == 0) {
						parent[node] = nbr;
						q.add(node);
					}
				}
			}
		}
	}
	static StringBuilder output = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
//		reader = new BufferedReader(new StringReader(str));
		
		N = Integer.parseInt(reader.readLine());
		parent = new int[N + 1];
		graph = new HashMap<Integer, List<Integer>>();
		for (int n = 1; n < N; n++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			if (graph.containsKey(a)) {
				List<Integer> list = graph.get(a);
				list.add(b);
			}else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(b);
				graph.put(a, list);
			}
			if (graph.containsKey(b)) {
				List<Integer> list = graph.get(b);
				list.add(a);
			}else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(a);
				graph.put(b, list);
			}
		}
		BFS();
		for (int i = 2; i < parent.length; i++) {
			output.append(parent[i]).append("\n");
		}
		System.out.print(output);
	}
	
	static String str = "7\r\n" + 
			"1 6\r\n" + 
			"6 3\r\n" + 
			"3 5\r\n" + 
			"4 1\r\n" + 
			"2 4\r\n" + 
			"4 7";
}

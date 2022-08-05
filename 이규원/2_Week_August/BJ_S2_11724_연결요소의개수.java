package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_S2_11724_연결요소의개수 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static HashMap<Integer, Queue<Integer>> graph = new HashMap<Integer, Queue<Integer>>();	
	static boolean[] isVisited;
	static void BFS(int n, boolean[] isVisited) {
		isVisited[n] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		while(!q.isEmpty()) {
			Queue<Integer> nodes = graph.get(q.poll());
			while (nodes != null && !nodes.isEmpty()) {
				int node = nodes.poll();
				if (!isVisited[node]) {
					isVisited[node] = !isVisited[node];
					q.add(node);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new StringReader(str));
		
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(reader.readLine());
			int n = Integer.parseInt(tokens.nextToken());
			int m = Integer.parseInt(tokens.nextToken());
			if (graph.containsKey(n)) {				
				Queue<Integer> node = graph.get(n);
				node.add(m);
			}else {
				Queue<Integer> node = new LinkedList<>();
				node.add(m);
				graph.put(n, node);
			}
			if (graph.containsKey(m)) {				
				Queue<Integer> node = graph.get(m);
				node.add(n);
			}else {
				Queue<Integer> node = new LinkedList<>();
				node.add(n);
				graph.put(m, node);
			}
		}
		Set<Entry<Integer, Queue<Integer>>> hashSet = graph.entrySet();
		isVisited = new boolean[N + 1];
		int result = 0;
		for (Entry<Integer, Queue<Integer>> set : hashSet) {
			if (!isVisited[set.getKey()]) {
				BFS(set.getKey(), isVisited);
				result++;
			}
		}
		for (int i = 1; i < isVisited.length; i++) {
			if(!isVisited[i]) {
				++result;
			}
		}
		
		System.out.println(result);
		
	}
	
	static String str = "6 8\r\n" + 
			"1 2\r\n" + 
			"2 5\r\n" + 
			"5 1\r\n" + 
			"3 4\r\n" + 
			"4 6\r\n" + 
			"5 4\r\n" + 
			"2 4\r\n" + 
			"2 3";
}

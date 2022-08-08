package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * 
 * @author kyulee
 * @category BFS / unionfind?
 * @performance 76328kb	624ms
 * @memo unionfind로 시도했을 때 계속 틀렸다... 이유를 모르겠다... 결국 bfs로 sol
 */


public class BJ_G4_04803_트리 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static Map<Integer, List<Integer>> graph;
	
	
	//bfs 탐색 
	static boolean BFS(int n, boolean[] visited) {
		visited[n] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		boolean flag = false;
		while (!q.isEmpty()) {
			int node = q.poll();
			List<Integer>list = graph.get(node);
			if (list != null) {
				for (Integer g : list) {
					if(visited[g]) flag = true;
					else {
						visited[g] = true;
						List<Integer>target = graph.get(g);
						target.remove((Object)node);
						q.add(g);
					}				
				}				
			}
			
		}
		return flag;
	}
	
	public static void main(String[] args) throws IOException {
//		reader = new BufferedReader(new StringReader(str));
		int T = 1;
		while (true) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			int N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());
			
			if (N == 0 && M == 0) break;
			graph = new HashMap<Integer, List<Integer>>();
			for (int m = 0; m < M; m++) {
				tokens = new StringTokenizer(reader.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				if (graph.containsKey(a)) {
					graph.get(a).add(b);
				}else {
					List<Integer> list = new LinkedList<Integer>();
					list.add(b);
					graph.put(a, list);
				}
				if (graph.containsKey(b)) {
					graph.get(b).add(a);
				}else {
					List<Integer> list = new LinkedList<Integer>();
					list.add(a);
					graph.put(b, list);
				}
			}
						
			boolean[] visited = new boolean[N + 1];
			
			int result = 0;
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					if (!BFS(i, visited)) result++;
				}
			}
			
			switch (result) {
			case 0:
				output.append(String.format("Case %d: No trees.\n", T));
				break;
			case 1:
				output.append(String.format("Case %d: There is one tree.\n", T));				
				break;
			default:
				output.append(String.format("Case %d: A forest of %d trees.\n", T, result));				
				break;
			}
			T++;
		}
		System.out.println(output);
	}
	
	static String str =
			"6 3\r\n" + 
			"1 2\r\n" + 
			"2 3\r\n" + 
			"3 4\r\n" + 
			"6 5\r\n" + 
			"1 2\r\n" + 
			"2 3\r\n" + 
			"3 4\r\n" + 
			"4 5\r\n" + 
			"5 6\r\n" + 
			"6 6\r\n" + 
			"1 2\r\n" + 
			"2 3\r\n" + 
			"1 3\r\n" + 
			"4 5\r\n" + 
			"5 6\r\n" + 
			"6 4\r\n" + 
			"0 0";
}


//
//public class BJ_G4_04803_트리 {
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	static StringBuilder output = new StringBuilder();
//	
//	public static int findParent(int a, int[] parent) {
//		if (a != parent[a]) {
//			parent[a] = findParent(parent[a], parent);
//		}
//		return parent[a];
//	}
//	
//	public static void unionParent(int a, int b, int[] parent) {
//		int pa = parent[a];
//		int pb = parent[b];
//		if (pa < pb) {
//			parent[pb] = pa;
//		}else {
//			parent[pa] = pb;
//		}
//		
//	}
//	
//	
//	public static void main(String[] args) throws IOException {
//		reader = new BufferedReader(new StringReader(str));
//		int T = 1;
//		while (true) {
//			StringTokenizer tokens = new StringTokenizer(reader.readLine());
//			int n = Integer.parseInt(tokens.nextToken());
//			int m = Integer.parseInt(tokens.nextToken());
//			int[] parent = new int[n + 1];
//			for (int i = 0; i < n + 1; i++) parent[i] = i;
//			
//			if (n == 0 && m == 0) {
//				break;
//			}
//			boolean[] cycle= new boolean [n + 1];
//			for (int i = 0; i < m; i++) {
//				tokens = new StringTokenizer(reader.readLine());
//				int a= Integer.parseInt(tokens.nextToken());
//				int b= Integer.parseInt(tokens.nextToken());
//				
//				if (cycle[findParent(a, parent)] || cycle[findParent(b, parent)]) {
//					cycle[findParent(a, parent)] = true;
//					cycle[findParent(b, parent)] = true;
//				}
//				else if (a != b && findParent(a, parent) == findParent(b, parent)) {
//					cycle[findParent(a, parent)] = true;
//				}else {
//					unionParent(a, b, parent);
//				}
//			}
//			int min = 0;
//			int result = 0;
//			for (int i = 1; i < n + 1; i++)
//				if (parent[i] > min && !cycle[parent[i]]) {
//					min = parent[i];
//					result++;
//				}
//			switch (result) {
//			case 0:
//				output.append(String.format("Case %d: No trees.\n", T));
//				break;
//			case 1:
//				output.append(String.format("Case %d: There is one tree.\n", T));				
//				break;
//			default:
//				output.append(String.format("Case %d: A forest of %d trees.\n", T, result));				
//				break;
//			}
//			T++;
//		}
//		System.out.print(output);
//		
//	}
//	
//	static String str =
//			"6 3\r\n" + 
//			"1 2\r\n" + 
//			"2 3\r\n" + 
//			"3 4\r\n" + 
//			"6 5\r\n" + 
//			"1 2\r\n" + 
//			"2 3\r\n" + 
//			"3 4\r\n" + 
//			"4 5\r\n" + 
//			"5 6\r\n" + 
//			"6 6\r\n" + 
//			"1 2\r\n" + 
//			"2 3\r\n" + 
//			"1 3\r\n" + 
//			"4 5\r\n" + 
//			"5 6\r\n" + 
//			"6 4\r\n" + 
//			"0 0";
//}

package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 12.
@see https://www.acmicpc.net/problem/18352 특정 거리의 도시 찾기
@performance 
@difficulty Silver2
@category #
@note 간선이 1일때 bfs이용
*/
public class BOJ_18352 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;
	static int N,M,K,X;
	static ArrayList<Integer> result = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		//도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>()); // 초기화
		}
		
		visited = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(b);
		}
		bfs();
		//dfs(X,0);
		
		result.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1, o2);
			}
		});
		
		if(result.size() == 0) System.out.println(-1);
		else {
			for(int i=0; i<result.size(); i++) {
				System.out.println(result.get(i));
			}
		}
		
	}
	
//	static void dfs(int V, int cnt) {
//		visited[V] = true;
//		
//		System.out.println(V+" : "+cnt);
//		
//		for(int i=0; i<graph.get(V).size(); i++) {
//			int b = graph.get(V).get(i);
//			if(!visited[b]) {
//				visited[b] = true;
//				dfs(b, ++cnt);
//			}
//		}
//		
//	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(X);
		visited[X]= true;
		int level=0;
		
		while(!queue.isEmpty()) {
			int size = queue.size(); //해당 레벨의 수만큼
			for(int q=0; q<size; q++) {
				int a = queue.poll();
				
				if(level == K) result.add(a);
				
				for(int i=0; i<graph.get(a).size(); i++) {
					int b = graph.get(a).get(i);
					
					if(!visited[b]) {
						queue.offer(b);
						visited[b] = true;
					}
				}
			}
			level++;
		}
		
	}
	
	
}
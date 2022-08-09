package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 8.
@see https://www.acmicpc.net/problem/1260 DFS와 BFS
@performance #18380	272
@difficulty Silver2
@category #dfs #bfs
@note 이것이 취업을 위한 코딩테스트다 (5-9&8.java 참고)
@note https://github.com/ndb796/python-for-coding-test/blob/master/5/9.java
*/
public class BOJ_01260 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokens.nextToken()); //정점의 개수 -> 노드의 개수?
		int M = Integer.parseInt(tokens.nextToken()); //간선의 개수
		int V = Integer.parseInt(tokens.nextToken()); //탐색을 시작할 정점의 번호 -> 루트노드!
		visited= new boolean[N+1]; //1부터 시작하므로
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<Integer>());//초기화
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(b); //해당 정점(노드)에 연결된 노드 추가
			graph.get(b).add(a);
			//간선은 양방향
			Collections.sort(graph.get(a));
			Collections.sort(graph.get(b));
		}
		
		
		//System.out.println(graph.toString());
		dfs(V);
		System.out.println();
		visited= new boolean[N+1];
		bfs(V);
	}
	
	//방문한 노드의 자식 노드들은 큐에 저장하고 루트 노드와 인접한 노드부터 탐색한다.
	static void bfs(int V) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(V); //처음 시작 노드
		visited[V]= true; //처음 노드 방문 처리
		
		while(!queue.isEmpty()) {
			int a = queue.poll();
			System.out.print(a+" ");
			
			for(int i=0; i<graph.get(a).size(); i++) {
				//해당 노드와 연결된 노드들을 큐에 추가한다.
				int b = graph.get(a).get(i);
				if(!visited[b]) {
					queue.offer(b);
					visited[b]= true; //큐에 추가해봤다 = 방문해봤다.
				}
			}
		}
	}
	
	//방문한 노드와 인접한 노드를 먼저 방문한다.
	static void dfs(int V) {
		visited[V]= true; //방문 처리
		System.out.print(V+" ");
		
		//선택한 노드와 인접한 노드들을 방문한다.
		for(int i=0; i<graph.get(V).size(); i++) {
			int b = graph.get(V).get(i);//인접노드
			if(!visited[b]) dfs(b);
		}
	}
}
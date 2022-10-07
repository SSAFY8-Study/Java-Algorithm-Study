package sw_a_practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author koreii
@since 2022. 10. 7.
@see
@git
@performance   14960KB	108ms
@category #    부분집합, BFS
@note */
public class BOJ_17471 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int[] population;
	static boolean[] set;	//	true면 left 선거구, false면 right 선거구
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int leftCnt = 0, rightCnt = 0;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		population = new int[N + 1];
		set = new boolean[N + 1];
		
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		
		tokens = new StringTokenizer(bf.readLine());
		
		for(int i = 1; i <= N; i++)
			population[i] = Integer.parseInt(tokens.nextToken());
		
		for(int i = 1; i <= N; i++) {
			int cnt;
			tokens = new StringTokenizer(bf.readLine());
			
			cnt = Integer.parseInt(tokens.nextToken());
			
			for(int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(tokens.nextToken());
				graph.get(i).add(num);
			}
		}
		
		decide(1);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static boolean[] visited;
	
	static void decide(int nth) {
		if(nth == N + 1) {	
			if(leftCnt * rightCnt == 0)
				return;
			
			int leftPop = 0, rightPop = 0;
			int leftStart = -1, rightStart = -1;
			visited = new boolean[N + 1];
			
			for(int i = 1; i <= N; i++) {
				if(set[i]) {
					leftPop += population[i];
					if(leftStart == -1)
						leftStart = i;
				}
				else {
					rightPop += population[i];
					if(rightStart == -1)
						rightStart = i;
				}
			}
			
			bfs(leftStart, set[leftStart]);
			bfs(rightStart, set[rightStart]);
			
			for(int i = 1; i <= N; i++)	//	두 선거구로 나눌 수 없는 경우
				if(!visited[i])
					return;
			
			ans = Integer.min(ans, Math.abs(leftPop - rightPop));
			
			return;
		}
		
		set[nth] = true;
		leftCnt++;
		decide(nth + 1);
		leftCnt--;
		set[nth] = false;
		rightCnt++;
		decide(nth + 1);
		rightCnt--;
	}
	
	static void bfs(int start, boolean pos) {
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				
				if(set[next] != pos)	//	cur과 next가 다른 선거구일 경우 pass
					 continue;
				if(visited[next])
					continue;
				
				visited[next] = true;
				q.offer(next);
			}
		}
	}
}
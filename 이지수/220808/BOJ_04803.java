package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 8.
@see https://www.acmicpc.net/problem/4803 트리
@performance
@difficulty Gold4
@category #그래프 #트리
@note boolean의 초기값은 false..
*/
public class BOJ_04803 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited ;
	static boolean flag = true;
	static int cnt; //트리 개수

	public static void main(String[] args) throws IOException {
		for(int tc=1; ; tc++) {
			tokens = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());
			
			if(N==0 && M==0) break;
			
			cnt=0;
			graph = new ArrayList<ArrayList<Integer>>();
			visited = new boolean[N+1];
			
			for(int i=0; i<N+1; i++) {//0번째는 빈공간이라 생각해야함 ! 1부터 시작하기 때문에
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<M; i++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				
				graph.get(a).add(b);
				graph.get(b).add(a);//양방향
			}
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				dfs(i,0);
				if(flag) {
					cnt++; //i에서 시작해서 탐색했을 때 사이클 없이 탐색됨
				}
				flag = true;
				//visited = new boolean[N+1];
			}
			
			//연결되어 있는 걸 하나로 처리해야 하는데 이걸 어떻게??
			
			
			sb.append("Case "+tc+": ");
			if(cnt>1) {
				sb.append("A forest of "+cnt+" trees.\n");
			}
			else if(cnt ==1) {
				sb.append("There is one tree.\n");
			}
			else {
				sb.append("No trees.\n");
			}
		}
		System.out.println(sb.toString());
		
	}
	
	static void dfs(int index, int prev) {
		if(!flag) return;
		visited[index] = true;
		
		for(int i=0; i<graph.get(index).size(); i++) {
			int a = graph.get(index).get(i);
			//System.out.println(index+ " : "+ prev + " : "+ a);
			if(!visited[a]) {
				dfs(a, index);
			}
			else if(visited[a] && a!=prev) { //방문했던 곳이고 부모가 아닌걸 가리키고 있다면 -> 사이클임
				//만약 a=자식을 가리키고 있었다면 dfs 특성상 자식을 먼저 탐색했기 때문에 visited[a]=false였을 것이다. /즉, visited[a]=true인 경우는 부모이거나 사이클이 발생한 경우??
				flag = false;
			}
		}
	}
}
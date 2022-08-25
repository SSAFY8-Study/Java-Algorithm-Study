import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class ABCDE {

	static List<Integer>[] map;
	static boolean [] visited;
	static int V,E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		map = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from].add(to);//양방향 
			map[to].add(from);
		}
		
		
		
		
		for(int i = 0; i < V; i++) { //모든 노드를 탐색 시작 노드해서 dfs
			visited = new boolean[V];
			visited[i] = true;
			if(dfs(i,1)) {//탐색 시작노드, 노드 갯수 -> 노드 다섯개 카운팅 될 때만 true로 반
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
		
	}

	private static boolean dfs(int i, int dep) {
		if(dep == 5) {
			return true;
		}
		for(int v : map[i]) {
			if(!visited[v]) {
				visited[v] = true;
				if(dfs(v,dep+1)) return true;
				visited[v] = false;
			}
		}
		
		return false;
	}

}

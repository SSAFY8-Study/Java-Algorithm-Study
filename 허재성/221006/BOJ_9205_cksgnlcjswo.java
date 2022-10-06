// 14156KB	124ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,sy,sx,ey,ex;
	static int[][] adj;
	static List<int[]> pos;
	
	public static void main(String[] args) throws Exception {
	
		T = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<T;++tc) {
			N = Integer.parseInt(br.readLine());
			adj = new int[N][N];
			pos = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			
			for(int i=0;i<N;++i) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				pos.add(new int[] {y,x});
			}
			
			st = new StringTokenizer(br.readLine());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			
			
			if(possible(new int[] {sy,sx},new int[] {ey,ex})) {
				sb.append("happy\n");
				continue;
			} 
				
			//i -> j 로 갈 수 있는지 여부
			for(int i=0;i<N;++i) {
				for(int j=i+1;j<N;++j) {
					if(possible(pos.get(i),pos.get(j))) {
						adj[j][i]= adj[i][j] = 1;
					}
				}
			}
			
			if(BFS()) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
			
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean BFS() {
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		
		//다음 진행할 수있는 경로넣기
		for(int i=0;i<N;++i) {
			if(possible(new int[] {sy,sx},pos.get(i))) {
				q.add(i);
				visited[i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(possible(pos.get(cur),new int[] {ey,ex})) {
				return true;
			}
			
			for(int i=0;i<N;++i) {
				//다음지점으로 갈수 있을 때
				if(adj[cur][i] != 0 && !visited[i] && possible(pos.get(i),pos.get(cur))) {
					visited[i] = true;
					q.add(i);
				}
			}
		}
		return false;
	}
	
	static boolean possible(int[] p1, int[] p2) {
		return (Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1])) <= 1000;
	}
}
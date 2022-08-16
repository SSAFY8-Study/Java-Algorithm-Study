package boj.g4;

import java.util.ArrayList;

public class G4_04803_트리 {
	static Reader in = new Reader();
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		
		while(true) {
			int cnt = 0;
			
			int n = in.nextInt();
			int m = in.nextInt();
			
			if(n == 0 && m == 0) break;
			
			graph = new ArrayList[n+1];
			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < m; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				
				graph[a].add(b);
				graph[b].add(a);
			}
			
			visited = new boolean[n+1];
			
			for(int i = 1; i <= n; i++) {
				if(!visited[i]) {
					if(!isCycle(i, 0)) cnt++;
				}
			}
			
			if(cnt > 1) {
				sb.append(String.format("Case %d: A forest of %d trees.\n", tc, cnt));
			}
			else if(cnt == 1) {
				sb.append(String.format("Case %d: There is one tree.\n", tc));
			}
			else {
				sb.append(String.format("Case %d: No trees.\n", tc));
			}
			
			tc++;
		}
		
		System.out.println(sb);
	}
	
	// 싸이클 있으면 true 반환 -> 트리가 아닌 그래프
	static boolean isCycle(int cur, int prev) {
		visited[cur] = true;
		
		for(int next: graph[cur]) {
			if(!visited[next]) {
				if(isCycle(next, cur)) {
					return true;
				}
			}
			// 방문했었던 노드인데 바로 전 노드가 아님 -> 싸이클
			else if(next != prev) {
				return true;
			}
		}
		
		return false;
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}

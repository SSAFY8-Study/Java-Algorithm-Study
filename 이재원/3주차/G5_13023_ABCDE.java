package boj.g5;

import java.util.ArrayList;
import java.util.List;

public class G5_13023_ABCDE {
	static Reader in = new Reader();
	static int N, M, ans, far, d;
	static List<Integer>[] graph;
	static boolean[] visited, temp;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		graph = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, 0); // i에서 시작하여 길이가 4인 정점 있는지 확인
				visited[i] = false;
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int node, int dist) {
		if(dist == 4) {
			ans = 1;
			return;
		}
		
		for(int next: graph[node]) {
			if(ans == 1) return;
			if(!visited[next]) {
				visited[next] = true;
				dfs(next, dist+1);
				visited[next] = false; // 모든 경로를 확인해야 하므로 방문기록 제거
			}
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
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

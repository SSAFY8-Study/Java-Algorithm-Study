package boj.g4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class G4_01967_트리의_지름 {
	static Reader in = new Reader();
	static List<int[]>[] tree;
	static int[] dist;
	static int N, start, max = -1;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		tree = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int w = in.nextInt();
			
			tree[a].add(new int[] {b, w});
			tree[b].add(new int[] {a, w});
		}
		
		// 루트노드에서 가장 먼 노드 탐색 -> start에 대입
		dist = new int[N+1];
		Arrays.fill(dist, -1);
		dist[1] = 0;
		dfs(1);
		
		// start 노드로부터 가장 먼 노드 탐색
		max = -1;
		dist = new int[N+1];
		Arrays.fill(dist, -1);
		dist[start] = 0;
		dfs(start);
		
		System.out.println(max);
	}
	
	static void dfs(int node) {
		if(tree[node].isEmpty()) {
			return;
		}
		
		for(int[] next: tree[node]) {
			if(dist[next[0]] == -1) {
				dist[next[0]] = dist[node] + next[1];
				
				if(dist[next[0]] > max) {
					max = dist[next[0]];
					start = next[0];
				}
				
				dfs(next[0]);
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

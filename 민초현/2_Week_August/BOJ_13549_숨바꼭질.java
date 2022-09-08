package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int subin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {subin, 0});
		
		boolean[] visited = new boolean[100001];
		visited[subin] = true;
		
		int pos, time, nxtPos;
		int[] move = {2, -1, 1};
		
		while(!q.isEmpty()) {
			pos = q.peek()[0]; time = q.poll()[1];
			
			if(pos == sister) {
				System.out.println(time);
				break;
			}
			
			for(int m: move) {
				if(m == 2) {
					nxtPos = pos*2;
					if(0<=nxtPos && nxtPos<=100_000 && !visited[nxtPos]) {
						visited[nxtPos] = true;
						q.add(new int[] {nxtPos, time});
					}
				}else {
					nxtPos = pos+m;
					if(0<=nxtPos && nxtPos<=100_000 && !visited[nxtPos]) {
						visited[nxtPos] = true;
						q.add(new int[] {nxtPos, time+1});
					}
				}
			}
		}
	}

}

package bj.s2;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/2644
 * @performance 11680 KB 80 ms
 * @category #그래프 이론 #그래프 탐색 #너비 우선 탐색 #깊이 우선 탐색
 * @memo bfs 큐에서 depth를 구분하는 좋은 방법이 있을까?
 * 
 */

public class BJ_S2_2644 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int N, P1, P2, M, Chon;
	static int[][] ParentChild, Relation;
	static boolean[] Visited;

	static boolean bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(P1);
		Visited[P1] = true;

		int cnt = 1; // Chon수를 어디서 증가시켜야할지, depth를 구분하기 위해.
		while (!q.isEmpty()) {
			int num = 0; // cnt를 업데이트 하기 위해.
			for (int c = 0; c < cnt; c++) {
				int polled = q.poll();
				if (polled == P2) {
					return true;
				}
				for (int i = 1; i <= N; i++) {
					if (Relation[polled][i] == 1 && !Visited[i]) {
						q.offer(i);
						Visited[i] = true;
						num++;
					}
					if (Relation[i][polled] == 1 && !Visited[i]) {
						q.offer(i);
						Visited[i] = true;
						num++;
					}
				}
			}
			cnt = num;
			Chon++;

		}
		return false;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		P1 = Integer.parseInt(tokens.nextToken());
		P2 = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(input.readLine());

		Relation = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int p = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			Relation[p][c] = 1;
		} // 입력 끝.

		Chon = 0;
		Visited = new boolean[N + 1];

		if (bfs()) {
			output.append(Chon);
		} else {
			output.append(-1);
		}
		System.out.print(output);
	}

}

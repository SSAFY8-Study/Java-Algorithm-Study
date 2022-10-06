package sw_a_practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @since 2022. 10. 5.
 * @see
 * @git
 * @performance	18768KB	132ms
 * @category #	Bitmasking + BFS
 * @note	 # 	열쇠(9328)도 풀어보기
 * @memo	 # 	시간도 다른 사람들 평균보다 조금 느리고(최소 10ms 정도), 메모리도 40000KB 정도 더 쓴다.
 */
public class BOJ_1194 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	final static int DIFF = 'A' - 'a';
	static int N, M;
	static char[][] graph;
	static int sy, sx;

	static int ans = Integer.MAX_VALUE;

	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		graph = new char[N][M];
		visited = new boolean[1 << 6][N][M];

		for (int i = 0; i < N; i++) {
			String input = bf.readLine();
			for (int j = 0; j < M; j++) {
				graph[i][j] = input.charAt(j);

				if (graph[i][j] == '0') {
					sy = i;
					sx = j;
				}
			}
		}

		bfs();
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static int getKey(int key, char ch) { // 열쇠 ch 획득
//		System.out.println("얻은 key = " + ch);
		key = key | (1 << (ch - 'a'));
//		System.out.println("보유 key = " + key);
		return key;
	}

	static boolean hasKey(int key, char door) {
//		System.out.println("door = " + door);
//		System.out.println("현재 보유 key = " + key);
		char ch = (char) (door - DIFF); // door를 열기 위해 필요한 key
//		System.out.println("key = " + ch);

		return (key & (1 << (ch - 'a'))) != 0;
	}

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited[0][sy][sx] = true;
		q.offer(new int[] { sy, sx, 0, 0 }); // 좌표, key 상태, 이동한 거리

		while (!q.isEmpty()) {
			int[] out = q.poll();
			int cy = out[0];
			int cx = out[1];
			int cKey = out[2];
			int cCost = out[3];
//			System.out.println("큐에서 나옴");
//			System.out.println("[" + cy + ", " + cx + ", " + cKey + ", " + cCost + "]");

			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				int nKey = cKey;
				int nCost = cCost;
//				System.out.println("다음에 가볼 곳");
//				System.out.println("[" + ny + ", " + nx + ", " + nKey + ", " + nCost + "]");

				if (!isIn(ny, nx) || graph[ny][nx] == '#') // 범위 벗어나거나 벽일 경우
					continue;

//				System.out.println("방문전 key = " + nKey);
				if (visited[nKey][ny][nx]) // 현재 key 상태로 방문한 적이 있을 경우
					continue;
				else 
//					System.out.println("방문");

				nCost += 1;

				if (graph[ny][nx] == '.' || graph[ny][nx] == '0') { // 단순 빈칸 또는 출발점일 경우
					visited[nKey][ny][nx] = true;
					q.offer(new int[] { ny, nx, nKey, nCost });
//					System.out.println("큐에 들어감");
//					System.out.println("[" + ny + ", " + nx + ", " + nKey + ", " + nCost + "]");
				} else if ('a' <= graph[ny][nx] && graph[ny][nx] <= 'f') { // 열쇠를 얻은 경우
					visited[nKey][ny][nx] = true;
					nKey = getKey(nKey, graph[ny][nx]);
					q.offer(new int[] { ny, nx, nKey, nCost });
//					System.out.println("큐에 들어감");
//					System.out.println("[" + ny + ", " + nx + ", " + nKey + ", " + nCost + "]");
				} else if ('A' <= graph[ny][nx] && graph[ny][nx] <= 'F') { // 문을 마주친 경우
					if (hasKey(nKey, graph[ny][nx])) {
						visited[nKey][ny][nx] = true;
						q.offer(new int[] { ny, nx, nKey, nCost });
//						System.out.println("큐에 들어감");
//						System.out.println("[" + ny + ", " + nx + ", " + nKey + ", " + nCost + "]");
					}
				} else if (graph[ny][nx] == '1') { // 도착점에 도달한 경우
					if (ans > nCost)
						ans = nCost;
				}
			} // for-4-dir end
		} // while-end
	} // bfs-end
}
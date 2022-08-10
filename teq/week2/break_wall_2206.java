package algo.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * @author TEQ
 * @since 2022. 8. 9.
 * @see
 * @performance
 * @category BFS by queue 
 * @note
 */
public class break_wall_2206 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int answer = Integer.MAX_VALUE;
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int R, C;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		char[][] map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = input.readLine().toCharArray();
		}
		Deque<int[]> deque = new ArrayDeque<int[]>();
		boolean[][][] visited = new boolean[R][C][2]; // 부수고 들어갔는지 안부수고 들어갔는지
		deque.addLast(new int[] { 0, 0, 1, 1 }); // row, col, bomb, time
		visited[0][0][0] = true;
		while (!deque.isEmpty()) { // bfs
			int[] me = deque.removeFirst();
			int r = me[0];
			int c = me[1];
			int bomb = me[2];
			int time = me[3];
			if (r == R - 1 && c == C - 1) {
				answer = Integer.min(answer, time);
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nr = r + delta[d][0];
				int nc = c + delta[d][1];
				if (is_in(nr, nc)) {
					if (map[nr][nc] == '0') {
						if(!visited[nr][nc][0] && bomb == 1) {
							deque.addLast(new int[] { nr, nc, bomb, time + 1 });
							visited[nr][nc][0] = true;
						} else if(bomb == 0 && !visited[nr][nc][1]) {
							deque.addLast(new int[] { nr, nc, bomb, time + 1 });
							visited[nr][nc][1] = true;
						}
					} else {
						if (bomb == 1) { // 벽이고 부술수있으면
							deque.addLast(new int[] { nr, nc, bomb - 1, time + 1 });
							visited[nr][nc][1] = true;
						}
					}
				}
			}
		}

		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
	}

	static boolean is_in(int r, int c) {
		return r > -1 && r < R && c > -1 && c < C;
	}

	// REMOVE_START
	private static String src = "9 9 \r\n" + 
			"010001000\r\n" + 
			"010101010\r\n" + 
			"010101010\r\n" + 
			"010101010\r\n" + 
			"010101010\r\n" + 
			"010101010\r\n" + 
			"010101010\r\n" + 
			"010101011\r\n" + 
			"000100010";
	// REMOVE_END
}
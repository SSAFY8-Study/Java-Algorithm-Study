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
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class fire_5427 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static char[][] map;
	static boolean[][] visited;
	static int R, C;
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static Deque<int[]> fires;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			boolean is_possible = false;
			tokens = new StringTokenizer(input.readLine());
			C = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			map = new char[R][C];
			visited = new boolean[R][C];
			fires = new ArrayDeque<>();
			for (int r = 0; r < R; r++) {
				map[r] = input.readLine().toCharArray();
			}
			int sr = 0, sc = 0; // 상근이 위치, 불 위치
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == '@') {
						sr = r;
						sc = c;
					}
					if (map[r][c] == '*') {
						fires.offer(new int[] { r, c });
					}
				}
			}
			int offset = 0; // 불 업데이트 타이밍
			Deque<int[]> deque = new ArrayDeque<int[]>();
			deque.add(new int[] { sr, sc, 0 }); // 위치와 점수
			update_fire(); // 불 먼저 업데이트
			while (!deque.isEmpty()) {
				int[] sang = deque.poll();
				sr = sang[0];
				sc = sang[1];
				if (sr == 0 || sr == R - 1 || sc == 0 || sc == C - 1) {
					System.out.println(sang[2] + 1);
					is_possible = true;
					break;
				}
				for (int d = 0; d < 4; d++) { // 상근이 업데이트
					int nr = sr + delta[d][0];
					int nc = sc + delta[d][1];
					if (nr > -1 && nr < R && nc > -1 && nc < C) {
						if (map[nr][nc] != '#' && !visited[nr][nc] && map[nr][nc] != '*') {
							deque.offer(new int[] { nr, nc, sang[2] + 1 });
							visited[nr][nc] = true;
						}
					}
				}
				if (!deque.isEmpty() && deque.getFirst()[2] != offset) {
					offset = deque.getFirst()[2];
					update_fire(); // 불 업데이트
				}
			}
			if (!is_possible)
				System.out.println("IMPOSSIBLE");
		}

	}

	static void update_fire() {
		int size = fires.size();
		for(int i = 0; i < size; i++) {
			int[] fire = fires.poll();
			int fr = fire[0];
			int fc = fire[1];
			for (int d = 0; d < 4; d++) {
				int nr = fr + delta[d][0];
				int nc = fc + delta[d][1];
				if (nr > -1 && nr < R && nc > -1 && nc < C) {
					if (map[nr][nc] != '#' && map[nr][nc] != '*') {
						map[nr][nc] = '*';
						fires.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}

	// REMOVE_START
	private static String src = "5\r\n" + "4 3\r\n" + "####\r\n" + "#*@.\r\n" + "####\r\n" + "7 6\r\n" + "###.###\r\n"
			+ "#*#.#*#\r\n" + "#.....#\r\n" + "#.....#\r\n" + "#..@..#\r\n" + "#######\r\n" + "7 4\r\n" + "###.###\r\n"
			+ "#....*#\r\n" + "#@....#\r\n" + ".######\r\n" + "5 5\r\n" + ".....\r\n" + ".***.\r\n" + ".*@*.\r\n"
			+ ".***.\r\n" + ".....\r\n" + "3 3\r\n" + "###\r\n" + "#@#\r\n" + "###";
	// REMOVE_END
}
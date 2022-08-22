package teq.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author TEQ
 * @since 2022. 8. 19.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class bj_6593_상범빌딩 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int L, R, C;
	static char[][][] building;
	static boolean[][][] visited;
	static Deque<int[]> deque;
	static int[][] delta = { { 0, 0, 1 }, { 0, 0, -1 }, { 0, 1, 0 }, { 0, -1, 0 }, { 1, 0, 0 }, { -1, 0, 0 } };
	static boolean escaped;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		while (true) {
			tokens = new StringTokenizer(input.readLine());
			L = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			if (L == 0 && R == 0 && C == 0) {
				System.out.println(output);
				return;
			} else {
				escaped = false;
				building = new char[L][R][C];
				visited = new boolean[L][R][C];
				for (int l = 0; l < L; l++) {
					for (int r = 0; r < R; r++) {
						building[l][r] = input.readLine().toCharArray();
					}
					input.readLine();
				}
				deque = new ArrayDeque<int[]>();
				out: for (int l = 0; l < L; l++) {
					for (int r = 0; r < R; r++) {
						for (int c = 0; c < C; c++) {
							if (building[l][r][c] == 'S') {
								deque.offer(new int[] { l, r, c, 0 });
								visited[l][r][c] = true;
								break out;
							}
						}
					}
				}
				while (!deque.isEmpty()) {
					int[] me = deque.poll();
					int l = me[0];
					int r = me[1];
					int c = me[2];
					int t = me[3];
					if (building[l][r][c] == 'E') {
						output.append(String.format("Escaped in %d minute(s).%n", t));
						escaped = true;
						break;
					} else {
						for (int d = 0; d < delta.length; d++) {
							int nl = l + delta[d][0];
							int nr = r + delta[d][1];
							int nc = c + delta[d][2];
							if (is_in(nl, nr, nc) && !visited[nl][nr][nc] && building[nl][nr][nc] != '#') {
								deque.offer(new int[] { nl, nr, nc, t + 1 });
								visited[nl][nr][nc] = true;
							}
						}
					}
				}
				if (!escaped) {
					output.append("Trapped!\n");
				}
			}
		}
	}

	static boolean is_in(int l, int r, int c) {
		return l >= 0 && r >= 0 && c >= 0 && l < L && r < R && c < C;
	}

	// REMOVE_START
	private static String src = "3 4 5\r\n" + "S....\r\n" + ".###.\r\n" + ".##..\r\n" + "###.#\r\n" + "\r\n"
			+ "#####\r\n" + "#####\r\n" + "##.##\r\n" + "##...\r\n" + "\r\n" + "#####\r\n" + "#####\r\n" + "#.###\r\n"
			+ "####E\r\n" + "\r\n" + "1 3 3\r\n" + "S##\r\n" + "#E#\r\n" + "###\r\n" + "\r\n" + "0 0 0";
	// REMOVE_END
}
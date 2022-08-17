package algo.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 
 * @author TEQ
 * @since 2022. 8. 17.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class bj_coins_16197 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] delta = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R, C;
	static Deque<int[]> coins;
	static boolean[][][][] visited;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = input.readLine().toCharArray();
		}
		int[] tmp = new int[5];
		int count = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'o') {
					tmp[count++] = r;
					tmp[count++] = c;
				}
			}
		}
		coins = new ArrayDeque<int[]>();
		coins.offer(tmp);
		visited = new boolean[R][C][R][C];
		visited[tmp[0]][tmp[1]][tmp[2]][tmp[3]] = true;
		while (!coins.isEmpty()) {
			int[] temp = coins.poll();
			int first_r = temp[0], first_c = temp[1], time = temp[4];
			int second_r = temp[2], second_c = temp[3];
			if (time > 10) {
				System.out.println(-1);
				return;
			} else if (is_in(first_r, first_c) ^ is_in(second_r, second_c)) {
				System.out.println(time);
				return;
			} else {
				for (int d = 0; d < delta.length; d++) {
					int nfr = first_r + delta[d][0];
					int nfc = first_c + delta[d][1];
					int nsr = second_r + delta[d][0];
					int nsc = second_c + delta[d][1];
					if (!is_in(nfr, nfc) && !is_in(nsr, nsc)) {
						continue;
					} else if (!is_in(nfr, nfc) ^ !is_in(nsr, nsc)) {
						coins.offerFirst(new int[] {nfr, nfc, nsr, nsc, time + 1});
					} else {
						if (map[nfr][nfc] == '#' && map[nsr][nsc] == '#') {
							continue;
						}
						if (map[nfr][nfc] != '#' && map[nsr][nsc] == '#' && !visited[nfr][nfc][second_r][second_c]) {
							coins.offer(new int[] { nfr, nfc, second_r, second_c, time + 1 });
							visited[nfr][nfc][second_r][second_c] = true;
						}
						if (map[nfr][nfc] == '#' && map[nsr][nsc] != '#' && !visited[first_r][first_c][nsr][nsc]) {
							coins.offer(new int[] { first_r, first_c, nsr, nsc, time + 1 });
							visited[first_r][first_c][nsr][nsc] = true;
						}
						if (map[nfr][nfc] != '#' && map[nsr][nsc] != '#' && !visited[nfr][nfc][nsr][nsc]) {
							coins.offer(new int[] { nfr, nfc, nsr, nsc, time + 1 });
							visited[nfr][nfc][nsr][nsc] = true;
						}						
					}
				}
			}
		}
		System.out.println(-1);
	}

	static boolean is_in(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	// REMOVE_START
	private static String src = "5 3\r\n" + 
			"###\r\n" + 
			".o.\r\n" + 
			"###\r\n" + 
			".o.\r\n" + 
			"###";
	// REMOVE_END
}
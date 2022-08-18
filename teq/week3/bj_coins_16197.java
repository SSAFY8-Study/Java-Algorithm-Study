package teq.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

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
	static Deque<int[]> first, second;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		char[][] map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = input.readLine().toCharArray();
		}
		int[] tmp = new int[4];
		int count = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'o') {
					tmp[count++] = r;
					tmp[count++] = c;
				}
			}
		}
		System.out.println(Arrays.toString(tmp));
		first.offer(new int[] { tmp[0], tmp[1], 0 });
		second.offer(new int[] { tmp[2], tmp[3], 0 });
		while (!first.isEmpty() && !second.isEmpty()) {
			int[] first_one = first.poll();
			int first_r = first_one[0], first_c = first_one[1], time = first_one[2];
			int[] second_one = second.poll();
			int second_r = second_one[0], second_c = second_one[1];
			if (time > 10) {
				System.out.println(-1);
				return;
			} else if (is_in(first_r, first_c) ^ is_in(second_r, second_c)) {
				System.out.println(time);
				return;
			}
			
			

		}
	}

	static boolean is_in(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	// REMOVE_START
	private static String src = "6 2\r\n" + ".#\r\n" + ".#\r\n" + ".#\r\n" + "o#\r\n" + "o#\r\n" + "##";
	// REMOVE_END
}
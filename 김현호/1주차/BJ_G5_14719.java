package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/14719
 * @performance 14836 KB 140 ms
 * @category #구현 #시뮬레이션
 * @memo 교집합인 곳은 빼주기.
 * 
 */

public class BJ_G5_14719 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int h = Integer.parseInt(tokens.nextToken());
		int w = Integer.parseInt(tokens.nextToken());
		int[] blocks = new int[w];
		int[][] map = new int[h][w];
		int answer = h * w;

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < w; i++) {
			blocks[i] = Integer.parseInt(tokens.nextToken());
			answer -= blocks[i];
		}
		// map 만들기.
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < blocks[i]; j++) {
				map[h - 1 - j][i] = 1;
			}
		}

		// 왼쪽
		for (int r = 0; r < h; r++) {
			if (map[r][0] == 1) {
				break;
			} else { // 옆으로 뚫린 공간 찾기.
				for (int c = 0; c < w; c++) {
					if (map[r][c] == 0) {
						answer--;
					} else {
						break;
					}
				}
			}
		}
		// 오른쪽
		for (int r = 0; r < h; r++) {
			if (map[r][w - 1] == 1) {
				break;
			} else { // 옆으로 뚫린 공간 찾기.
				for (int c = w - 1; c >= 0; c--) {
					if (map[r][c] == 0) {
						answer--;
					} else {
						break;
					}
				}
			}
		}
		// 동시에 뺀 것 더해주기.
		outer: for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (map[r][c] == 1) {
					break outer;
				}
			}
			answer += w;
		}
		output.append(answer);
		System.out.print(output);
	}

}

package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author
 * @see https://www.acmicpc.net/problem/13565
 * @performance 1. 불필요한 dfs 진행할 때, 214428 KB 1168 ms. 2. 불필요한 dfs 진행하지 않을 때,
 *              214872 KB 268 ms.
 * @category #그래프 이론 #그래프 탐색 #너비 우선 탐색 #깊이 우선 탐색
 * @memo bfs, dfs 기준에 대해서 생각해보기. 불필요한 dfs 진행하지 않기.
 * 
 */

public class BJ_S2_13565 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } }; // 하 좌 우 상

	static int _m, _n;
	static char[][] _grid;
	static boolean _isDeliverd;
	static boolean[][] _isVisited;

	static boolean isIn(int x, int y) {
		return x >= 0 && x < _m && y >= 0 && y < _n;
	}

	static void dfs(int x, int y) {
		if (x == _m - 1) {
			_isDeliverd = true;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + deltas[i][0];
			int ny = y + deltas[i][1];
			if (isIn(nx, ny) && !_isVisited[nx][ny] && _grid[nx][ny] == '0') {
				_isVisited[nx][ny] = true;
				dfs(nx, ny);
				if (_isDeliverd) { // 불필요한 dfs 하지 않기.
					return;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_m = Integer.parseInt(tokens.nextToken());
		_n = Integer.parseInt(tokens.nextToken());

		_grid = new char[_m][];
		for (int i = 0; i < _m; i++) {
			_grid[i] = input.readLine().toCharArray();
		}
		// 입력 끝.

		_isDeliverd = false;
		for (int i = 0; i < _n; i++) {
			if (_grid[0][i] == '0') {
				_isVisited = new boolean[_m][_n];
				_isVisited[0][i] = true;
				dfs(0, i);
				if (_isDeliverd) {
					break;
				}
			}
		}

		if (_isDeliverd) {
			output.append("YES");
		} else {
			output.append("NO");
		}

		System.out.print(output);
	}

}

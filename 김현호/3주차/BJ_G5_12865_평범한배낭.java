package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @memo 존재하지 않는 경우는 호출하기전에 제거한다.
 *
 */

public class BJ_G5_12865 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _k;
	static Thing[] _things;
	static int[][] _memo;
	static boolean[] _isVisited;
	static List<Integer>[] _totalVisited;

	static int ns(int n, int w) {
		if (_memo[n][w] != -1) {
			return _memo[n][w];
		} else {
			// 물건이 0개거나, 무게제한이 0이면 최대가치는 0이다.
			if (n == 0 || w == 0) {
				return _memo[n][w] = 0;
			}
			// 존재할 수 없는 경우의 수 제거.
			if (_things[n].w > w) {
				return _memo[n][w] = ns(n - 1, w);
			}
			// n번째 물건이 담긴 경우와, 담기지 않는 경우 고려.
			return _memo[n][w] = Math.max(ns(n - 1, w), _things[n].v + ns(n - 1, w - _things[n].w));

		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_things = new Thing[_n + 1];
		for (int i = 1; i <= _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			int w = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			_things[i] = new Thing(w, v);
		}

		_memo = new int[_n + 1][_k + 1];
		// memoization을 위해 초기화.
		for (int i = 0; i < _n + 1; i++) {
			Arrays.fill(_memo[i], -1);
		}

		System.out.println(ns(_n, _k));

	}

	static class Thing {
		int w, v;

		public Thing(int w, int v) {
			this.w = w;
			this.v = v;
		}

	}

}
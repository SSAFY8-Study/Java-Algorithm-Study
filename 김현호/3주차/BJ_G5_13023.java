package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @memo 끝나면 방문할 수 있게 해주자.
 */

public class BJ_G5_13023 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _answer;
	static List<Integer>[] _relationship;
	static boolean _exist;

	static void find(int cur, int cnt, boolean[] visited) {
		visited[cur] = true;
		if (cnt == 4) {
			_answer = 1;
			_exist = true;
			return;
		}
		for (Integer i : _relationship[cur]) {
			if (!visited[i]) {
				find(i, cnt + 1, visited);
				if (_exist) {
					return;
				}
			}
		}
		visited[cur] = false;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_relationship = new List[_n];
		for (int i = 0; i < _n; i++) {
			_relationship[i] = new ArrayList<>();
		}

		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			_relationship[a].add(b);
			_relationship[b].add(a);
		}

		_answer = 0;
		_exist = false;
		for (int i = 0; i < _n; i++) {
			find(i, 0, new boolean[_n]);
			if (_exist) {
				break;
			}
		}

		output.append(_answer);
		System.out.println(output);

	}

}
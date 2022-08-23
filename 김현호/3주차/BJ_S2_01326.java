package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author hh
 * @category #bfs #simulation
 * @memo 문제를 잘 읽어보면, 배수만큼 떨어져있으므로, 뒤로도 갈 수 있다.
 */

public class BJ_S2_01326 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _a, _b;
	static int[] _stones;

	static int bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[_n + 1];

		q.offer(_a);
		isVisited[_a] = true;

		int cnt = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				// 만약 index 차이가 징검다리의 배수라면 한번에 갈 수 있음.
				if ((_b - cur) % _stones[cur] == 0) {
					return cnt;
				}
				// 갈 수 없다면 배수만큼 q에 추가. 양의방향.
				for (int j = cur + _stones[cur]; j <= _n; j += _stones[cur]) {
					q.offer(j);
					isVisited[j] = true;
				}
				// 갈 수 없다면 배수만큼 q에 추가. 음의방향.
				for (int j = cur - _stones[cur]; j > 0; j -= _stones[cur]) {
					q.offer(j);
					isVisited[j] = true;
				}

			}
			cnt++;
		}

		return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_stones = new int[_n + 1];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 1; i <= _n; i++) {
			_stones[i] = Integer.parseInt(tokens.nextToken());
		}

		tokens = new StringTokenizer(input.readLine());
		_a = Integer.parseInt(tokens.nextToken());
		_b = Integer.parseInt(tokens.nextToken());

		int answer = bfs();

		output.append(answer);
		System.out.println(output);

	}

}

package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @memo q에 offer하고 방문처리 까먹지 않기.
 *
 */

public class BJ_G5_05014 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _f, _s, _g, _u, _d;

	static boolean isIn(int floor) {
		return floor >= 1 && floor <= _f;
	}

	static int bfs() {
		boolean[] visited = new boolean[_f + 1];
		Queue<Integer> q = new LinkedList<>();

		q.offer(_s);
		visited[_s] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				if (cur == _g) {
					return cnt;
				}
				// up 버튼이 0이 아니고 올라가야하는데, 현재층수와 목표층수의 차이가 up으로 나눠떨어지면 횟수를 더해주기만 하면 됨.
				if (_u != 0 && _g > cur) {
					if (_g - cur % _u == 0) {
						return cnt + (_g - cur) / _u;
					}
				}
				// down도 같은 방식.
				if (_d != 0 && _g < cur) {
					if (cur - _g % _d == 0) {
						return cnt + (cur - _g) / _d;
					}
				}
				// up이 0층이면 할필요가 없고, 한번 위로 누른게 층수 범위 안이고, 방문한 적 없다면 큐에 넣고, 방문처리.
				if (_u != 0 && isIn(cur + _u) && !visited[cur + _u]) {
					q.offer(cur + _u);
					visited[cur + _u] = true;
				}
				// down도 같은 방식.
				if (_d != 0 && isIn(cur - _d) && !visited[cur - _d]) {
					q.offer(cur - _d);
					visited[cur - _d] = true;
				}
			}
			cnt++;
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_f = Integer.parseInt(tokens.nextToken());
		_s = Integer.parseInt(tokens.nextToken());
		_g = Integer.parseInt(tokens.nextToken());
		_u = Integer.parseInt(tokens.nextToken());
		_d = Integer.parseInt(tokens.nextToken());

		int answer = bfs();
		if (answer == -1) {
			output.append("use the stairs");
		} else {
			output.append(answer);
		}
		System.out.println(output);
	}

}

package bj.g4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/4803
 * @performance 81596 KB 632 ms
 * @category #
 * @memo 길이가 0인 cycle. 입력받을때 순서. dfs bfs 어떤걸 쓸지. 자료구조를 뭘 써야하는지.
 * 
 */

public class BJ_G4_4803 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _answer;
	static int[][] _edges;
	static boolean[] _isVisited;

	public static void main(String[] args) throws IOException {
		int tc = 1;
		while (true) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_m = Integer.parseInt(tokens.nextToken());

			if (_n == 0 && _m == 0) {
				break;
			}

			List<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < _n + 1; i++) {
				edges.add(new ArrayList<>());
			}

			for (int i = 0; i < _m; i++) {
				tokens = new StringTokenizer(input.readLine());
				int left = Integer.parseInt(tokens.nextToken());
				int right = Integer.parseInt(tokens.nextToken());
				edges.get(left).add(right); // cycle을 찾기 위해. 11 41
				edges.get(right).add(left);
			}
			// 입력 완료.

			_isVisited = new boolean[_n + 1];
			_answer = 0;

			for (int i = 1; i < edges.size(); i++) {
				boolean isCycle = false;
				if (edges.get(i).isEmpty()) { // 비어있으면 pass.
					continue;
				} else { // 비어있지 않으면,
					Queue<Point> q = new ArrayDeque<>();
					while (!edges.get(i).isEmpty()) {
						int removed = edges.get(i).remove(0);
						edges.get(removed).remove(edges.get(removed).indexOf(i));
						q.offer(new Point(i, removed)); // q에 넣고, 본인 삭제.
						if ((_isVisited[i] && _isVisited[removed]) || i == removed) { // cycle인지 확인.
							isCycle = true;
						}
						_isVisited[i] = true; // 방문 처리.
						_isVisited[removed] = true;
					}
					while (!q.isEmpty()) { // q가 비어있지 않을 동안,
						Point polled = q.poll();
						int x = polled.x;
						int y = polled.y;
						while (!edges.get(y).isEmpty()) { // 위와 동일.
							int removed = edges.get(y).remove(0);
							edges.get(removed).remove(edges.get(removed).indexOf(y));
							q.offer(new Point(y, removed));
							if ((_isVisited[y] && _isVisited[removed]) || y == removed) {
								isCycle = true;
							}
							_isVisited[y] = true;
							_isVisited[removed] = true;
						}
					}
				}
				if (!isCycle) { // cycle이 아니면 answer 1 추가.
					_answer++;
				}
			}

			// 방문 되지 않은 노드들은 정점 하나의 트리.
			for (int i = 1; i < _isVisited.length; i++) {
				if (!_isVisited[i]) {
					_answer++;
				}
			}

			// 출력
			output.append("Case ").append(tc).append(": ");
			if (_answer == 0) {
				output.append("No trees.");
			} else if (_answer == 1) {
				output.append("There is one tree.");
			} else {
				output.append("A forest of ").append(_answer).append(" trees.");
			}
			output.append("\n");

			tc++;
		}
		System.out.print(output);
	}

}
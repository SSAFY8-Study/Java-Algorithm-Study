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
 * @author
 * @see https://www.acmicpc.net/problem/14502
 * @performance 140188 KB 404 ms
 * @category #구현 #그래프 이론 #브루트포스 알고리즘 #그래프 탐색 #너비 우선 탐색
 * @memo
 * 
 */

public class BJ_G4_14502 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

	static int _n, _m, _answer;
	static int[][] _map;
	static List<Point> _virus, _blank;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _m;
	}

	static void bfs(int x, int y, int[][] temp) {
		boolean[][] isVisited = new boolean[_n][_m];
		Queue<Point> q = new ArrayDeque<Point>();
		q.offer(new Point(x, y));
		isVisited[x][y] = true;
		while (!q.isEmpty()) {
			Point polled = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = polled.x + deltas[i][0];
				int ny = polled.y + deltas[i][1];
				if (isIn(nx, ny) && !isVisited[nx][ny] && temp[nx][ny] == 0) {
					q.offer(new Point(nx, ny));
					isVisited[nx][ny] = true;
					temp[nx][ny] = 2;
				}
			}
		}
	}

	static void spreadVirus(Point[] walls) {
		int[][] temp = new int[_n][];
		for (int i = 0; i < _n; i++) {
			temp[i] = Arrays.copyOf(_map[i], _map[0].length);
		}

		// 벽 세우기
		for (int i = 0; i < 3; i++) {
			int x = walls[i].x;
			int y = walls[i].y;
			temp[x][y] = 1;
		}

		// 바이러스 확산
		for (int i = 0; i < _virus.size(); i++) {
			int x = _virus.get(i).x;
			int y = _virus.get(i).y;
			bfs(x, y, temp);
		}

		// 안전 영역 크기 구하기
		int safeArea = 0;
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _m; j++) {
				if (temp[i][j] == 0) {
					safeArea++;
				}
			}
		}

		if (safeArea > _answer) {
			_answer = safeArea;
		}
	}

	static void comb(int cnt, int start, Point[] result, boolean[] isVisited) {
		if (cnt == 3) {
			spreadVirus(result);
			return;
		}

		for (int i = start; i < _blank.size(); i++) {
			if (!isVisited[i]) {
				result[cnt] = _blank.get(i);
				comb(cnt + 1, i + 1, result, isVisited);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new int[_n][_m];
		_virus = new ArrayList<>();
		_blank = new ArrayList<>();
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
				if (_map[i][j] == 0) {
					_blank.add(new Point(i, j));
				} else if (_map[i][j] == 2) {
					_virus.add(new Point(i, j));
				}
			}
		}
		// 입력 끝.

		_answer = 0;
		comb(0, 0, new Point[3], new boolean[_blank.size()]);

		output.append(_answer);
		System.out.print(output);
	}

}

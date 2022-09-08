import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kyulee
 * @link https://www.acmicpc.net/problem/3190
 * @performance 13024	104
 * @category 구현
 * @memo
 */

public class BJ_G4_03190_뱀 {
	static class Time {
		int t;
		char d;

		public Time(int t, char d) {
			this.t = t;
			this.d = d;

		}

		@Override
		public String toString() {
			return "Time [t=" + t + ", d=" + d + "]";
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	}

	static class Snack {
		int hx;
		int hy;
		int d;
		int len = 1;
		Queue<Node> bodys;

		public Snack(int hx, int hy, int d) {
			this.hx = hx;
			this.hy = hy;
			this.d = d;
			bodys = new LinkedList<>();
		}

		void rotateD(char m) {
			if (m == 'L') {
				this.d -= 1;
				if (this.d < 0)
					this.d = 3;
			} else {
				this.d += 1;
				if (this.d > 3) {
					this.d = 0;
				}
			}
		}

		boolean isBody(int nx, int ny) {
			if (!bodys.isEmpty()) {
				for (Node body : bodys) {
					if (body.x == nx && body.y == ny) {
						return true;
					}
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "Snack [hx=" + hx + ", hy=" + hy + ", d=" + d + ", len=" + len + ", bodys=" + bodys + "]";
		}

	}

	static int[][] graph;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//reader = new BufferedReader(new StringReader(str));

		N = Integer.parseInt(reader.readLine());
		int A = Integer.parseInt(reader.readLine());
		graph = new int[N][N];
		for (int a = 0; a < A; a++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			int ax = Integer.parseInt(tokens.nextToken());
			int ay = Integer.parseInt(tokens.nextToken());
			graph[ax - 1][ay - 1] = -1;
		}
		int M = Integer.parseInt(reader.readLine());
		Queue<Time> moves = new LinkedList<>();
		for (int m = 0; m < M; m++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			int mt = Integer.parseInt(tokens.nextToken());
			char md = tokens.nextToken().charAt(0);
			moves.add(new Time(mt, md));
		}
		System.out.println(startGame(moves));
	}

	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private static int startGame(Queue<Time> moves) {
		int time = 0;
		Snack snack = new Snack(0, 0, 0);
		snack.bodys.add(new Node(0, 0));
		while (true) {
			time++;
			int nx = snack.hx + deltas[snack.d][0];
			int ny = snack.hy + deltas[snack.d][1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				break;
			if (snack.isBody(nx, ny))
				break;

			if (graph[nx][ny] != -1) {
				if (!snack.bodys.isEmpty()) {
					snack.bodys.poll();
				}
			}else {
				snack.len++;
			}
			graph[nx][ny] = time;
			snack.hx = nx;
			snack.hy = ny;
			snack.bodys.add(new Node(nx, ny));
			Time move = moves.peek();
			if (move != null && time == move.t) {
				snack.rotateD(move.d);
				moves.poll();

			}
		}
		return time;
	}

	static String str = "6\r\n" + 
			"3\r\n" + 
			"3 4\r\n" + 
			"2 5\r\n" + 
			"5 3\r\n" + 
			"3\r\n" + 
			"3 D\r\n" + 
			"15 L\r\n" + 
			"17 D";
}

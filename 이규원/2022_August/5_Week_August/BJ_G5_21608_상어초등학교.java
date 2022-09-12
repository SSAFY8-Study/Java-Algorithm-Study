import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kyulee
 * @link https://www.acmicpc.net/problem/21608
 * @performance 19356	184
 * @category 구현
 * @memo
 *
 */
public class BJ_G5_21608_상어초등학교 {
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int bcnt;
		int fcnt;

		public Node(int x, int y, int bcnt, int fcnt) {
			super();
			this.x = x;
			this.y = y;
			this.bcnt = bcnt;
			this.fcnt = fcnt;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", bcnt=" + bcnt + ", fcnt=" + fcnt + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (this.fcnt == o.fcnt) {
				if (this.bcnt == o.bcnt) {
					if (this.x == o.x)
						return Integer.compare(this.y, o.y);
					return Integer.compare(this.x, o.x);
				}
				return Integer.compare(this.bcnt, o.bcnt) * -1;
			}
			return Integer.compare(this.fcnt, o.fcnt) * -1;
		}
	}

	static class Friend {
		int no;
		int[] friends;

		public Friend(int no, int[] friends) {
			super();
			this.no = no;
			this.friends = friends;
		}

		@Override
		public String toString() {
			return "Friend [no=" + no + ", friends=" + Arrays.toString(friends) + "]";
		}
	}

	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader = new BufferedReader(new StringReader(str));

		N = Integer.parseInt(reader.readLine());

		Friend[] ClassMates = new Friend[N * N];

		for (int n = 0; n < N * N; n++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			int no = Integer.parseInt(tokens.nextToken());
			int f1 = Integer.parseInt(tokens.nextToken());
			int f2 = Integer.parseInt(tokens.nextToken());
			int f3 = Integer.parseInt(tokens.nextToken());
			int f4 = Integer.parseInt(tokens.nextToken());

			ClassMates[n] = new Friend(no, new int[] { f1, f2, f3, f4 });
		}
//		System.out.println(Arrays.toString(ClassMates));

		makeFair(ClassMates);
	}

	static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private static void makeFair(Friend[] classMates) {
		int[][] graph = new int[N][N];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (Friend friend : classMates) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (graph[i][j] == 0) {
						int fcnt = 0;
						int bcnt = 0;
						for (int d = 0; d < deltas.length; d++) {
							int nx = i + deltas[d][0];
							int ny = j + deltas[d][1];
							if (isIn(nx, ny)) {
								if (graph[nx][ny] == 0)
									bcnt++;
								else {
									for (int f : friend.friends) {
										if (f == graph[nx][ny]) {
											fcnt++;
											break;
										}
									}
								}
							}
						}
						pq.add(new Node(i, j, bcnt, fcnt));
					}
				}
			}
			Node node = pq.poll();
			graph[node.x][node.y] = friend.no;
//			for (int[] row : graph)
//				System.out.println(Arrays.toString(row));
//			System.out.println();
			pq.clear();
		}
		int result = 0;
		for (Friend friend : classMates) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (graph[i][j] == friend.no) {
						int fcnt = 0;
						for (int d = 0; d < deltas.length; d++) {
							int nx = i + deltas[d][0];
							int ny = j + deltas[d][1];
							if (isIn(nx, ny)) {
								for (int f : friend.friends) {
									if (f == graph[nx][ny]) {
										fcnt++;
										break;
									}
								}
							}
						}
						if (fcnt > 0) {
							result += Math.pow(10, fcnt - 1);
						}
						break;
					}
				}
			}
		}
		System.out.println(result);
	}

	private static boolean isIn(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N;
	}

	static String str = "3\n"
			+ "4 2 5 1 7\n"
			+ "2 1 9 4 5\n"
			+ "5 8 1 4 3\n"
			+ "1 2 9 3 4\n"
			+ "7 2 3 4 8\n"
			+ "9 8 4 5 7\n"
			+ "6 5 2 3 4\n"
			+ "8 4 9 2 1\n"
			+ "3 9 2 1 4";
}

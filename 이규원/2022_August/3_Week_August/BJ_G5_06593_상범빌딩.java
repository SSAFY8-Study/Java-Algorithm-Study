package plusAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G5_06593_상범빌딩 {
	static class Node {
		int x;
		int y;
		int l;
		int minute;

		public Node(int x, int y, int l, int minute) {
			super();
			this.x = x;
			this.y = y;
			this.l = l;
			this.minute = minute;
		}
	}
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static char[][][] graph = new char[31][31][31];
	static boolean[][][] visited = new boolean[31][31][31];
	static int L, R, C;
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new StringReader(str));
		while (true) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			L = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			
			if (L + R + C == 0) {
				break;
			}
			
			Node start = null; 
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
					String input = reader.readLine();
					for (int c = 0; c < C; c++) {
						visited[l][r][c] = false;
						graph[l][r][c] = input.charAt(c);
						if (graph[l][r][c] == 'S') {
							start = new Node(r, c, l, 0);
						}
					}
				}
				reader.readLine();
			}
			
			int result = BFS(start);
			if (result > -1) {
				System.out.println(String.format("Escaped in %d minute(s).", result));
			}else {
				System.out.println("Trapped!");
			}
		}
	}
	static int[][] deltas = {{0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};
	private static int BFS(Node start) {
		visited[start.l][start.x][start.y] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			Node node = q.poll();
			for (int d = 0; d < deltas.length; d++) {
				int nl = node.l + deltas[d][0];
				int nx = node.x + deltas[d][1];
				int ny = node.y + deltas[d][2];
				if (isIn(nl, nx, ny) && !visited[nl][nx][ny]) {
					if (graph[nl][nx][ny] == 'E') {
						return node.minute + 1;
					}
					if (graph[nl][nx][ny] == '.') {
						visited[nl][nx][ny] = true;
						q.add(new Node(nx, ny, nl, node.minute + 1));
					}
				}
			}
		}
		return -1;
	}
	
	static boolean isIn(int nl, int nx, int ny) {
		return 0 <= nl && nl < L && 0 <= nx && nx < R && 0 <= ny && ny < C;
	}
	
	static String str = "3 4 5\r\n" + 
			"S....\r\n" + 
			".###.\r\n" + 
			".##..\r\n" + 
			"###.#\r\n" + 
			"\r\n" + 
			"#####\r\n" + 
			"#####\r\n" + 
			"##.##\r\n" + 
			"##...\r\n" + 
			"\r\n" + 
			"#####\r\n" + 
			"#####\r\n" + 
			"#.###\r\n" + 
			"####E\r\n" + 
			"\r\n" + 
			"1 3 3\r\n" + 
			"S##\r\n" + 
			"#E#\r\n" + 
			"###\r\n" + 
			"\r\n" + 
			"0 0 0";
}
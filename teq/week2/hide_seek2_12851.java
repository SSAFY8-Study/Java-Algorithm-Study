package teq.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class hide_seek2_12851 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static Deque<int[]> deque = new ArrayDeque<int[]>();
	static int N, K, answer = 0, shortcut = Integer.MAX_VALUE;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		deque.offer(new int[] { N, 0 });
		visited = new int[100001];
		visited[N] = 0;
		while (!deque.isEmpty()) {
			int[] subins = deque.poll();
			int subin = subins[0];
			int time = subins[1];
			if (time > shortcut) {
				break;
			}
			visited[subin] = time;
			if (subin == K) {
				shortcut = visited[K];
				answer++;
				continue;
			}

			if (subin * 2 <= 100000 && (visited[subin * 2] == time + 1 || (visited[subin * 2] == 0) && subin * 2 != N)) {
				deque.offer(new int[] { subin * 2, time + 1 });
			}
			if (subin + 1 <= 100000 && subin < K
					&& (visited[subin + 1] == time + 1 || (visited[subin + 1] == 0 && subin + 1 != N))) {
				deque.offer(new int[] { subin + 1, time + 1 });
			}
			if (subin - 1 >= 0 && (visited[subin - 1] == time + 1 || (visited[subin - 1] == 0 && subin - 1 != N))) {
				deque.offer(new int[] { subin - 1, time + 1 });
			}
		}
		System.out.println(visited[K]);
		System.out.println(answer);
	}

	// REMOVE_START
	private static String src = "5 17";
	// REMOVE_END
}
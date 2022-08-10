package algo.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class hide_seek3_13549 { 

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static Deque<int[]> deque = new ArrayDeque<int[]>();

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		boolean[] visited = new boolean[100001];
		deque.addFirst(new int[] { N, 0 });
		visited[N] = true;
		while (!deque.isEmpty()) {
			int[] subins = deque.removeFirst();
			int subin = subins[0];
			int time = subins[1];
			if(subin == K) {
				System.out.println(time);
				break;
			}
			if (subin * 2 <= 100000 && !visited[subin * 2]) {
				deque.addFirst(new int[] { subin * 2, time });
				visited[subin * 2] = true;
			}
			if (subin + 1 <= 100000 && subin < K && !visited[subin + 1]) {
				deque.addLast(new int[] { subin + 1, time + 1 });
				visited[subin + 1] = true;
			}
			if (subin - 1 >= 0 && !visited[subin - 1]) {
				deque.addLast(new int[] { subin - 1, time + 1 });
				visited[subin - 1] = true;
			}
		}
	}

	// REMOVE_START
	private static String src = "5 17";
	// REMOVE_END
}
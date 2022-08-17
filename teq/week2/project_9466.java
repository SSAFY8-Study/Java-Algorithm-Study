package teq.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author TEQ
 * @since 2022. 8. 12.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class project_9466 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int T, N, answer;
	static int[] picks;
	static ArrayList<Integer> list;
	static Stack<Integer> stack;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		T = Integer.parseInt(input.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(input.readLine());
			answer = N;
			picks = new int[N + 1];
			tokens = new StringTokenizer(input.readLine());
			for (int i = 1; i <= N; i++) {
				picks[i] = Integer.parseInt(tokens.nextToken());
			}
			boolean[] visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				stack = new Stack<Integer>();
				if (!visited[i]) {
					stack.push(i);
					visited[i] = true;
					System.out.println(stack);
					while (!stack.isEmpty()) {
						int now = stack.peek();
						int next = picks[now];
						if (visited[next]) {
							int team = 0;
							while(!stack.isEmpty()) {
								int tmp = stack.pop();
								team++;
								if(tmp == next) {
									answer -= team;
									System.out.println(answer);
									break;
								}
							}
						} else {
							stack.add(next);
							visited[next] = true;							
							System.out.println(stack);
						}
					}
				}
			}
			System.out.println(answer);
		}
	}

	// REMOVE_START
	static String src = "2\r\n" + "7\r\n" + "3 1 3 7 3 4 6\r\n" + "8\r\n" + "1 2 3 4 5 6 7 8";
	// REMOVE_END
}
package teq.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 
 * @author TEQ
 * @since 2022. 8. 18.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class bj_1987_alphabet {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C, ans = 0;
	static char[][] map;
	static Set<Character> visited;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		map = new char[R][C];
		visited = new TreeSet<>();
		for (int i = 0; i < map.length; i++) {
			map[i] = input.readLine().toCharArray();
		}
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	
	static void dfs(int r, int c, int cnt) {
		if(visited.contains(map[r][c])) {
			ans = Integer.max(ans, cnt);
			return;
		}
		
		visited.add(map[r][c]);
		
		for(int d = 0; d < 4; d++) {
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];
			if(nr >= 0 && nc >= 0 && nr < R && nc < C) {
				dfs(nr, nc, cnt + 1);
			}
		}
		
		visited.remove(map[r][c]);
	}

	// REMOVE_START
	private static String src = "3 6\r\n" + 
			"HFDFFB\r\n" + 
			"AJHGDH\r\n" + 
			"DGAGEH";
	// REMOVE_END
}
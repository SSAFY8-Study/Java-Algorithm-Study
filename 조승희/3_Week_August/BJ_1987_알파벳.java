package study3_08;
/**
 * 13828	1116
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ_1987_알파벳 {

	static char[][] map;
//	static List<Character> visited = new ArrayList<Character>();
	static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static int X, Y, max=0;
	static boolean[] visited = new boolean[26];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		Y = sc.nextInt();
		map = new char[X][Y];
		
		for(int i=0; i<X; i++) {
			map[i] = sc.next().toCharArray();
		}
		List<Character> visited = new ArrayList<Character>();
//		visited.add(map[0][0]);
		dfs(0, 0, 0);
		System.out.println(max);
	}
	static void dfs(int x, int y, int cnt) {
		if(visited[map[x][y]-'A']) {
			max = Math.max(max, cnt);
			return;
		}
		visited[map[x][y]-'A'] = true;
		for(int[] d: dir) {
			int nx = x+d[0];
			int ny = y+d[1];
			if(nx<0 || nx>=X || ny<0 || ny>=Y) continue;
//			if(visited.contains(map[nx][ny])) continue;
			dfs(nx, ny, cnt+1);
//			visited.remove(visited.indexOf(map[nx][ny]));
		}
		visited[map[x][y]-'A'] = false;
	}

}

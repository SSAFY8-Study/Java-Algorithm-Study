

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 양현용
 * @since 2022. 8. 9.
 * @see https://www.acmicpc.net/problem/13565
 * @performance
 * @category #DFS
 * @note
 */
public class BOJ_S2_13565_침투 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int n, m;
	static int[][] arr;
	static int[][] deltas= {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean[][] visited;
	static boolean flag=false;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		tokens = new StringTokenizer(input.readLine());
		n = Integer.parseInt(tokens.nextToken());
		m = Integer.parseInt(tokens.nextToken());

		arr = new int[n][m];
		visited=new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String str = input.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		//시작지점이 맨 윗줄로 고정이기 때문에 column만큼만 돌리기
		for (int i = 0; i < m; i++) {
			// 0이 이동 가능, 1이 막힘
			if (arr[0][i] == 0) {
				visited[0][i]=true;
				dfs(0, i);
				if(flag) break;
			}
		}
		if(flag) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}

	}

	static void dfs(int x, int y) {
		//column이 어디든 마지막 row에만 도착하면 OK
		if(x==n-1) {
			flag=true;
			return;
		}
		for(int i=0;i<deltas.length;i++) {
			int nx=x+deltas[i][0];
			int ny=y+deltas[i][1];
			if(isIn(nx,ny)) {
				if(arr[nx][ny]==0 && !visited[nx][ny]) {
					visited[nx][ny]=true;
					dfs(nx,ny);
				}
			}
		}
	}

	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}
}
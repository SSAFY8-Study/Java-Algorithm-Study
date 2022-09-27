package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, r, c, d, ans = 0;
	static int[][] map, deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new int[N][M];

		tokens = new StringTokenizer(input.readLine());
		r = Integer.parseInt(tokens.nextToken()); // 현재 위치 방향 
		c = Integer.parseInt(tokens.nextToken());
		d = Integer.parseInt(tokens.nextToken());
		
	
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		dfs(r, c, d); //dfs탐색
		System.out.println(ans);

	}

	private static boolean dfs(int r, int c, int d) {
		if (map[r][c] == 0) { // 청소하지 않은 공간이 있다면 청소해주기
			map[r][c] = 2;
			ans++; // 청소한 칸 갯수 증가
		}

		for (int i = 0; i < 4; i++) { // 네 방향 모두 탐색
			d = (4 + (d - 1)) % 4;
			int nx = r + deltas[d][0];
			int ny = c + deltas[d][1];

			if (map[nx][ny] == 0) { // 왼쪽 방향에 청소할 공간이 존재한다면,해당 방향으로 회전 후 한칸 전진한 위치와 방향을 가지고 1번부터 로봇을 다시 작동시키기 위해 dfs 탐색
				if(dfs(nx, ny, d)) return true;
			}

		}
		// 네 방향 모두 청소할 공간이 없다면 후진
		int bx = r + deltas[(d + 2) % 4][0];
		int by = c + deltas[(d + 2) % 4][1];
		//후진한 위치가 벽이면 dfs 종료
		if (map[bx][by] == 1)
			return true;
		else
			// 벽이 아니면 후진한 후, 1번 부터 로봇을 작동시키기 위해 dfs 탐색
			if(dfs(bx, by, d)) return true;
		return false;

	}

}

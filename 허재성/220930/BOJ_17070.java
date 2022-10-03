import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @since 2022. 9. 30.
 * @see
 * @git
 * @performance
 * @category #
 * @note
 */
public class BOJ_17070 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	final static int W = 0, H = 1, D = 2; // 각각 가로, 세로, 대각 이동

	static int N;
	static int sy1 = 0, sx1 = 0; // 막대 첫 번째 부분 초기 좌표 (0, 0)
	static int sy2 = 0, sx2 = 1; // 막대 두 번째 부분 초기 좌표 (0, 0)
	static int cnt = 0;
	
	static int[][] map;

	static boolean isIn(int ny1, int nx1, int ny2, int nx2) {
		boolean ret1 = (0 <= ny1 && ny1 < N) && (0 <= nx1 && nx1 < N) && (0 <= ny2 && ny2 < N) && (0 <= nx2 && nx2 < N);
		
		if(!ret1)
			return ret1;
		
		boolean ret2 = map[ny1][nx1] == 0 && map[ny2][nx2] == 0;
		return ret2;
	}

	static boolean isWidth(int ny1, int nx1, int ny2, int nx2) { // 가로로 배치됐는지 확인
		return (ny1 == ny2 && nx2 == nx1 + 1);
	}

	static boolean isHeight(int ny1, int nx1, int ny2, int nx2) { // 세로로 배치됐는지 확인
		return (nx1 == nx2 && ny2 == ny1 + 1);
	}

	static boolean isDiagonal(int ny1, int nx1, int ny2, int nx2) { // 대각으로 배치됐는지 확인
		return (ny2 == ny1 + 1 && nx2 == nx1 + 1);
	}

	static boolean canMoveWH(int ny, int nx) {
		return map[ny][nx] == 0;
	}
	
	static boolean canMoveD(int ny, int nx) {
		return isIn(ny - 1, nx, ny, nx - 1);
	}
	
	static void dfs(int cy1, int cx1, int cy2, int cx2) {
		if (cy2 == N - 1 && cx2 == N - 1) {
			cnt++;
			return;
		}

		// 현재 배치가 가로일 경우
		if (isWidth(cy1, cx1, cy2, cx2)) {
			// 오른쪽으로 이동
			int ny1 = cy1;
			int nx1 = cx1 + 1;
			int ny2 = cy2;
			int nx2 = cx2 + 1;

			if (isIn(ny1, nx1, ny2, nx2) && canMoveWH(ny2, nx2))
				dfs(ny1, nx1, ny2, nx2);

			// 오른쪽 대각선 아래로 이동
			ny1 = cy1;
			nx1 = cx1 + 1;
			ny2 = cy2 + 1;
			nx2 = cx2 + 1;

			if (isIn(ny1, nx1, ny2, nx2) && canMoveD(ny2, nx2))
				dfs(ny1, nx1, ny2, nx2);
		}
		// 현재 배치가 세로일 경우
		else if (isHeight(cy1, cx1, cy2, cx2)) {
			// 아래로 이동
			int ny1 = cy1 + 1;
			int nx1 = cx1;
			int ny2 = cy2 + 1;
			int nx2 = cx2;

			if (isIn(ny1, nx1, ny2, nx2) && canMoveWH(ny2, nx2))
				dfs(ny1, nx1, ny2, nx2);

			// 오른쪽 대각선 아래로 이동
			ny1 = cy1 + 1;
			nx1 = cx1;
			ny2 = cy2 + 1;
			nx2 = cx2 + 1;

			if (isIn(ny1, nx1, ny2, nx2) && canMoveD(ny2, nx2))
				dfs(ny1, nx1, ny2, nx2);
		}
		// 현재 배치가 대각선일 경우
		else if (isDiagonal(cy1, cx1, cy2, cx2)) {
			// 오른쪽으로 이동
			int ny1 = cy1 + 1;
			int nx1 = cx1 + 1;
			int ny2 = cy2;
			int nx2 = cx2 + 1;

			if (isIn(ny1, nx1, ny2, nx2) && canMoveWH(ny2, nx2))
				dfs(ny1, nx1, ny2, nx2);

			// 아래쪽으로 이동
			ny1 = cy1 + 1;
			nx1 = cx1 + 1;
			ny2 = cy2 + 1;
			nx2 = cx2;

			if (isIn(ny1, nx1, ny2, nx2) && canMoveWH(ny2, nx2))
				dfs(ny1, nx1, ny2, nx2);

			//	대각선으로 이동
			ny1 = cy1 + 1;
			nx1 = cx1 + 1;
			ny2 = cy2 + 1;
			nx2 = cx2 + 1;

			if (isIn(ny1, nx1, ny2, nx2) && canMoveD(ny2, nx2))
				dfs(ny1, nx1, ny2, nx2);
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		dfs(sy1, sx1, sy2, sx2);
		System.out.println(cnt);
	}
}
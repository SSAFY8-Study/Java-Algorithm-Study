import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gold4_2206_벽뿌수고이동하기 {

	static int[][] deltas = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static int [][] map;
	static int N,M;
	static int answer = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N+1][M+1];
		for(int r = 1; r <= N; r++) {
			String line = br.readLine();
			for(int c = 1; c <= M; c++) {
				map[r][c] = line.charAt(c-1) - '0';
			}
		}//입력 완료 
		
		go(1,1, 1, new boolean[N+1][M+1], 1);
		
		if(answer == 2147483647) {
			System.out.println(-1);
			return;
		}
		System.out.println(answer);
	}
	
	
	public static void go(int r, int c, int b, boolean [][] visited, int cnt) {
		if (answer == M + N -1 || answer <= cnt) return;
		if (r == N && c == M) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for(int[] delta : deltas) {
			if(isIn(r + delta[0], c + delta[1]) && (!visited[r + delta[0]][c + delta[1]])) {
				if((map[r + delta[0]][c + delta[1]] == 0) ) {
					visited[r + delta[0]][c + delta[1]] = true;
					go(r + delta[0],c + delta[1], b, visited, cnt+1);
					visited[r + delta[0]][c + delta[1]] = false;
				}
				else {
					if(b > 0) {
						visited[r + delta[0]][c + delta[1]] = true;
						go(r + delta[0],c + delta[1], b-1, visited, cnt+1);
						visited[r + delta[0]][c + delta[1]] = false;
					}
					
				}
			}
		}
		//더이상 갈 곳이 없음
		//근데 여기가 M * N인지
		//맞으면 최솟값 바꾸기
		if(r == N && c == M) {
			answer = Math.min(answer, cnt);
		}
		return;
	}
	
	public static boolean isIn(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= M;
	}

}

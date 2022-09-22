package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234_인구이동 {

	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, L, R, cnt, ans=0;
	static int[][] country; // 인구수
	static boolean check = true; // 인구 이동 가능 여부
	static boolean[][] visited;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Queue<int[]> move = new LinkedList<int[]>(); // 연합된 나라 위치
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		N = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());

		country = new int[N][N];

		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				country[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		while (check) {
			ans++; // 인구 이동 발생 기간
			visited = new boolean[N][N]; 
			check=false;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(!visited[r][c]) {
						cnt = bfs(r, c); // 이동 후 인구 수 return받음
						if(move.size()>1) check=true; // 인구 이동이 발생한다면 check = true. 즉 연합된 나라가 두 개 이상이라면
						while(move.size()>0) { //연합된 나라의 인구를 이동 후 인구 수로 갱신
							int[] position = move.poll();
							country[position[0]][position[1]]=cnt;
						}						
					}
				}
			}
		}
		// 만약 인구 이동이 이루어지지 않는다면 while문 종료되고, 이동이 이루어지지 않는 날까지 ans를 ++해주었으므로 -1 처리
		System.out.println(ans-1);

	}

	// 연합된 나라의 갯수와 총 인구수 구하기
	private static int bfs(int r, int c) {
		
		Queue<int[]> q = new LinkedList<int[]>(); 
		visited[r][c] = true;
		int cnt = 1; // 연합된 나라
		int sum = country[r][c]; // 총 인구수
		q.add(new int[]{r,c});
		move.add(new int[]{r,c});
		
		while (q.size() > 0) {
			int[] position = q.poll();

			for (int d = 0; d < deltas.length; d++) {
				int nx = position[0]+deltas[d][0];
				int ny = position[1]+deltas[d][1];
				
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				if (!visited[nx][ny]) {
					int diff = Math.abs(country[position[0]][position[1]] - country[nx][ny]); // 인접한 나라의 인구 차이
					if (L <= diff && diff <= R) { // 인구 차이가 L 이상, R 이하라면
						sum += country[nx][ny]; // 총 인구수
						cnt++; // 연합된 나라
						visited[nx][ny] = true;
						q.add(new int[] {nx,ny});
						move.add(new int[] {nx,ny});
					}
				}
			}
		}

		return sum/cnt; // 이동 후 인구 수 return
	}

}

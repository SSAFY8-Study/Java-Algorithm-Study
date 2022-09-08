/**
 * @author seungheecho
 * @number bj2667
 * @see https://www.acmicpc.net/problem/2667
 * @performance 14640	156
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2667_단지번호붙이기 {

	static int[][] apt;	// 단지 지도
	static boolean[][] visited;	// 방문 여부
	static int[] aptCnt;  // 정답
	static int n, cnt;	// 지도 크기, 각 단지 집 수
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static void dfs(int i, int j, int idx) {
		visited[i][j] = true;	// 방문표시
		int n = apt[0].length;
		aptCnt[idx]++;			// 단지 집 개수 + 1
		for(int[] d:dir) {
			int x = i+d[0];	//x값 이동
			int y = j+d[1];	//y값 이동

			if(x<0 || x>=n || y<0 || y>=n) {
				continue;
			}
			if(visited[x][y] || apt[x][y]==0) {
				continue;
			}
			dfs(x, y, idx);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		apt = new int[n][n];
		visited = new boolean[n][n];
		aptCnt = new int[n*n];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				apt[i][j] = Integer.parseInt(s.split("")[j]);
			}
		}
		int total = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(apt[i][j]==1 && !(visited[i][j])) { //1이고 방문안했으면 탐색시작
					aptCnt[total++] = 0;
					dfs(i, j, total-1);
				}
			}
		}
		
		Arrays.sort(aptCnt);
		System.out.println(total);
		
		for(int i=aptCnt.length-total; i<aptCnt.length; i++) { //오름차순 정렬해서 앞에 0이있음
			System.out.println(aptCnt[i]);
		}

	}

}

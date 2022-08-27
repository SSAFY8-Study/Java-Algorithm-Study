package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_연습문제_15172_헌터 {
	static Map<Integer, int[]> cords;
	static int[][] map;
	static int[] result;
	static boolean[] visited, visitedC;
	static int N, monsterCnt, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			monsterCnt = 0;
			
			map = new int[N][N];
			cords = new HashMap<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					monsterCnt = Math.max(monsterCnt, map[i][j]);
					
					if(map[i][j] != 0) {
						cords.put(map[i][j], new int[] {i, j});
					}
				}
			}
			
			visited = new boolean[monsterCnt+1];
			visitedC = new boolean[monsterCnt+1];
			result = new int[monsterCnt*2];
			
			recur(0);
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void recur(int nth) {
		if(nth == monsterCnt*2) {
			int time = 0;
			
			time += calcDist(new int[] {0, 0}, cords.get(result[0]));
			
			for(int i = 0; i < monsterCnt*2-1; i++) {
				time += calcDist(cords.get(result[i]), cords.get(result[i+1]));
				
				if(time > ans) return;
			}
			
			ans = Math.min(ans, time);
			return;
		}
		
		for(int i = -monsterCnt; i <= monsterCnt; i++) {
			if(i == 0) continue;
			
			if(i < 0) {
				if(visited[-i] && !visitedC[-i]) {
					visitedC[-i] = true;
					result[nth] = i;
					recur(nth+1);
					visitedC[-i] = false;
				}
			}
			else {
				if(!visited[i]) {
					visited[i] = true;
					result[nth] = i;
					recur(nth+1);
					visited[i] = false;
				}
			}
		}
	}
	
	static int calcDist(int[] arr1, int[] arr2) {
		return Math.abs(arr2[0] - arr1[0]) + Math.abs(arr2[1] - arr1[1]);
	}
}

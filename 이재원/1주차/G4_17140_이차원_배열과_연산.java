package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class G4_17140_이차원_배열과_연산 {
	static int R = 3, C = 3;
	static int[][] arr = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			if(arr[r][c] == k) {
				System.out.println(ans);
				break;
			}
			
			if(ans == 100) {
				System.out.println(-1);
				break;
			}
			
			if(R >= C) {
				operR(); // R 연산
			}
			else {
				operC(); // C 연산
			}

			ans++;
		}
	}
	
	static void operR() {
		int max = 0;
		
		for(int r = 0; r < R; r++) {
			Map<Integer, Integer> counter = new HashMap<>();
			
			// 개수 세기, 0이면 건너 뜀
			for(int c = 0; c < C; c++) {
				if(arr[r][c] == 0) {
					continue;
				}
				
				if(!counter.containsKey(arr[r][c])) {
					counter.put(arr[r][c], 1);
				}
				else {
					counter.put(arr[r][c], counter.get(arr[r][c]) + 1);
				}
			}
			
			// 숫자가 몇 개 있는지 배열로
			int[][] temp = new int[counter.size()][2];
			int idx = 0;
			
			for(Entry<Integer, Integer> entry: counter.entrySet()) {
				temp[idx][0] = entry.getKey();
				temp[idx][1] = entry.getValue();
				idx++;
			}
			
			// 수의 등장 횟수가 커지는 순, 수가 커지는 순으로 정렬
			Arrays.sort(temp, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
			
			// 값 arr에 대입하고 뒷 부분은 모두 0으로
			int len = temp.length;
			for(int c = 0; c < len; c++) {
				arr[r][2*c] = temp[c][0];
				arr[r][2*c+1] = temp[c][1];
			}
			for(int c = len*2; c < C; c++) {
				if(arr[r][c] == 0) continue;
				else arr[r][c] = 0;
			}
			
			if(len*2 > max) max = len*2;
		}
		
		// 가장 긴 행의 길이 C에 대입 -> 다음 R 연산때 C까지 탐색 
		C = max;
	}
	
	static void operC() {
		int max = 0;
		
		for(int c = 0; c < C; c++) {
			Map<Integer, Integer> counter = new HashMap<>();
			
			for(int r = 0; r < R; r++) {
				if(arr[r][c] == 0) {
					continue;
				}
				
				if(!counter.containsKey(arr[r][c])) {
					counter.put(arr[r][c], 1);
				}
				else {
					counter.put(arr[r][c], counter.get(arr[r][c]) + 1);
				}
			}
			
			int[][] temp = new int[counter.size()][2];
			int idx = 0;
			
			for(Entry<Integer, Integer> entry: counter.entrySet()) {
				temp[idx][0] = entry.getKey();
				temp[idx][1] = entry.getValue();
				idx++;
			}
			
			Arrays.sort(temp, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
			
			int len = temp.length;
			for(int r = 0; r < len; r++) {
				arr[2*r][c] = temp[r][0];
				arr[2*r+1][c] = temp[r][1];
			}
			for(int r = len*2; r < R; r++) {
				if(arr[r][c] == 0) continue;
				else arr[r][c] = 0;
			}
			
			if(len*2 > max) max = len*2;
		}
		
		R = max;
	}
}

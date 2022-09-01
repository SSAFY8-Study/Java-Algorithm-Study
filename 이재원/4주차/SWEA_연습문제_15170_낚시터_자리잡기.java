package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_연습문제_15170_낚시터_자리잡기 {
	static int[][] orders = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
	static int[][] gateInfo = new int[3][2];
	static boolean[] visited;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			
			for(int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				gateInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
				gateInfo[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < 6; i++) {
				enter(i);
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void enter(int c) {
		visited = new boolean[N];
		recur(c, 0, 0);
	}
	
	static void recur(int c, int o, int d) {
		if(o == 3) {
			ans = Math.min(ans, d);
			return;
		}
		
		Queue<Integer> route = new LinkedList<>();
		int gate = gateInfo[orders[c][o]][0];
		int people = gateInfo[orders[c][o]][1];
		int dist = 0;
		int cnt = 0;
		
		if(!visited[gate]) {
			visited[gate] = true;
			route.add(gate);
			dist++;
			cnt++;
			
			if(cnt == people) {
				recur(c, o+1, d+dist);
				visited[gate] = false;
				
				return;
			}
		}
	
		int step = 1;
		
		while(true) {
			int left = gate - step;
			int right = gate + step;
			
			if(!isIn(left) && !isIn(right)) break;
			
			if(isIn(left) && !visited[left] && isIn(right) && !visited[right]) {
				if(people >= cnt + 2) {
					visited[left] = true;
					route.add(left);
					visited[right] = true;
					route.add(right);
					dist += 2 * (step + 1);
					cnt += 2;
				}
				else {
					dist += step + 1;
					cnt++;
					
					visited[left] = true;
					recur(c, o+1, d+dist);
					visited[left] = false;
					visited[right] = true;
					recur(c, o+1, d+dist);
					visited[right] = false;
					
					while(!route.isEmpty()) {
						visited[route.poll()] = false;
					}
					
					return;
				}
			}
			else if(isIn(left) && !visited[left]) {
				visited[left] = true;
				route.add(left);
				dist += step + 1;
				cnt++;
			}
			else if(isIn(right) && !visited[right]) {
				visited[right] = true;
				route.add(right);
				dist += step + 1;
				cnt++;
			}
			
			if(cnt == people) {
				recur(c, o+1, d+dist);
				
				while(!route.isEmpty()) {
					visited[route.poll()] = false;
				}
				
				return;
			}
			
			step++;
		}
	}
	
	static boolean isIn(int x) {
		return 0 <= x && x < N;
	}
}

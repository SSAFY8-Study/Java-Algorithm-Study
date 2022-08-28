package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class swea_ssafy문제풀이_헌터 {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder out = new StringBuilder();
	static StringTokenizer st;

	static int minTime;
	static List<Node> points = new ArrayList<>();
	
	// seq : 방문할 points의 인덱스들이 저장될 순열, n : 몬스터
	public static void permutations(int idx,  int[] seq, int y, int x, int curTime, boolean[] visited) {
		
		// 가지치기
		if(curTime >= minTime)	return;
		
		int size = seq.length;
		if(idx == size) {
			minTime = Math.min(minTime, curTime);
			return;
		}
		
		int nxtY, nxtX;
		int half = size/2;		// 몬스터 개수 == 고객 개수
		
		for(int i=0; i<size; i++) {
			if(!visited[i]) {
				// 1번 몬스터, 2번 몬스터, 3번몬스터, 1번 고객, 2번 고객, 3번 고객 => 1번 고객 인덱스 - 1번 몬스터 인덱스 == half
				// 고객이 요청하는 몬스터가 아직 잡히지 않은 경우엔, 해당 고객 방문 불가
				if(i>=half && !visited[i-half])	continue;
				
				visited[i] = true;
				seq[idx] = i;
				
				// 다음 방문할 위치
				nxtY = points.get(i).r;
				nxtX = points.get(i).c;
				permutations(idx+1, seq, nxtY, nxtX, curTime + Math.abs(y - nxtY) + (Math.abs(x - nxtX)), visited);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(in.readLine());
		int n, map[][];
		List<Node> customers = new ArrayList<>();
		
		for(int t=1; t<=T; t++) {
			
			n = Integer.parseInt(in.readLine());		// 맵의 크기
			
			map = new int[n][n];						// 맵 초기화
			
			points.clear();								// 몬스터 좌표와, 고객 좌표가 들어갈 리스트
			customers.clear();							// 고객좌표 넣을 임시 리스트
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) {					// 몬스터일 경우, points 리스트에 삽입
						points.add(new Node(i, j, map[i][j], true));
					} else if(map[i][j] < 0) {			// 고객일 경우, customers 리스트에 삽입
						customers.add(new Node(i, j, map[i][j], false));
					}
				}
			}

			Collections.sort(points);			// 번호의 절댓값 순으로 정렬
			Collections.sort(customers);		// 번호의 절댓값 순으로 정렬
			
			points.addAll(customers);			// points뒤에 customers 이어붙임 -> ex. 1, 2, 3번 몬스터, 1, 2 ,3번 고객
			
			minTime = Integer.MAX_VALUE;
			permutations(0, new int[points.size()], 0, 0, 0, new boolean[points.size()]);
			
			out.append("#"+t+" "+minTime+"\n");
		}
		System.out.print(out);
	}
	
	static class Node implements Comparable<Node>{
		int r, c;
		int num;
		boolean flag;		// true: monster / false: customer
		
		public Node(int r, int c, int num, boolean flag) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.flag = flag;
		}
		
		public int compareTo(Node n) {
			return flag ? Integer.compare(this.num, n.num) : Integer.compare(this.num, n.num)*-1;
		}
	}
}
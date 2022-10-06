//  12488KB	100ms


import java.util.*;
import java.io.*;

public class Main {
	
	static int T;
	static int endX, endY = -1;
	static ArrayList<Point> stores = new ArrayList<>();
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(input.readLine());
		for(int i=0; i<T; i++) {
			stores.clear(); // 테케마다 상점 배열 초기화
			int n = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());
			
			// 시작 위치 저장
			int startX = Integer.parseInt(tokens.nextToken());
			int startY = Integer.parseInt(tokens.nextToken());
			
			// 편의점 위치 저장
			for(int j=0; j<n; j++) {
				tokens = new StringTokenizer(input.readLine());
				stores.add(new Point(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken())));
			}
			
			tokens = new StringTokenizer(input.readLine());
			
			// 도착 위치 저장
			endX = Integer.parseInt(tokens.nextToken());
			endY = Integer.parseInt(tokens.nextToken());
			
			// bfs 반환 결과에 따라 happy, sad 출력
			if(bfs(startX, startY)) {
				output.append("happy"+"\n");
			} else {
				output.append("sad"+"\n");
			}
	
		}
		System.out.println(output);
	}
	
	
	private static boolean bfs(int startX, int startY) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startX,startY)); // 출발점 먼저 넣기
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {				
				Point head = q.poll();
				
				// 만약 현재 좌표에서부터 도착지점까지의 거리가 1000이하라면? -> 갈 수 있음! happy~
				if(Math.abs(head.x - endX) + Math.abs(head.y - endY) <= 1000)
					return true;
				
				// 아니라면 현재 좌표에서부터 거리가 1000이하인 편의점들을 큐에 넣어주고 리스트에서 지우기
				for(int j=0; j<stores.size(); j++) {
					if(Math.abs(stores.get(j).x - head.x) + Math.abs(stores.get(j).y - head.y) <=1000) {
						q.add(new Point(stores.get(j).x,stores.get(j).y));
						stores.remove(j);
					}
				}
			}
		}
		return false;
	}


	static class Point {
	    int x;
	    int y;
	 
	    Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}

}
//  12772KB	112ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 6.
@see
@performance
@difficulty 
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static ArrayList<Store> store;
	static int startX, startY, endX, endY;
	static boolean flag = false;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			flag = false;
			
			store = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N];
			
			tokens = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(tokens.nextToken());
			startY = Integer.parseInt(tokens.nextToken());
			
			//50미터마다 한걸음씩
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				store.add(new Store(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken())));	
			}
			
			Collections.sort(store);
			
			tokens = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(tokens.nextToken());
			endY = Integer.parseInt(tokens.nextToken());
			
			if(bfs()) {
				sb.append("happy"+"\n");
			}else {
				sb.append("sad"+"\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(startX, startY));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int diff = Math.abs(endX - node.x) + Math.abs(endY - node.y);
			if(diff<=1000) {
				return true;
			}
			
			for(int i=0; i<store.size(); i++) {
				diff = Math.abs(store.get(i).x - node.x) + Math.abs(store.get(i).y - node.y);
				
				if(diff<=1000 && !visited[i]) {
					visited[i] = true;
					queue.add(new Node(store.get(i).x,store.get(i).y));
				}
			}
		}
		return false;
	}
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static class Store implements Comparable<Store>{
		int x, y;
		public Store(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Store o) {
			if(this.x == o.x) {
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.x, o.x);
		}
		
	}
}
package Programmers.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 6.
@see https://school.programmers.co.kr/learn/courses/30/lessons/67259
@performance
@difficulty 
@category #
@note */
public class 경주로건설 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};//방향 순서 직각
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		bfs();
		//dfs(new boolean[N][N],new Node(0,0,0,-1));
		System.out.println(answer);
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		int[][][] visited = new int[N][N][2]; //이동한 방향별로 겹쳐서 방문할 수 있는 곳들이 존재하기 때문에
		queue.offer(new Node(0,0,0,-1));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.x == N-1 && node.y == N-1) {
				//System.out.println(node.cost);
				answer = Math.min(answer, node.cost);
			}
			
			for(int i=0; i<deltas.length; i++) {
				int a = node.x+deltas[i][0];
				int b = node.y+deltas[i][1];
				
				if(isIn(a,b) && map[a][b]!=1) {
					if(check(i+1) == node.dir || check(i-1) == node.dir) {
						//직각(코너도로) : 500 + 100 (코너비용 +직선비용)
						if(visited[a][b][0] >= node.cost+600 || visited[a][b][0] ==0) { //작은 비용으로 갱신하기 위해
							queue.offer(new Node(a,b,node.cost+600,i));
							visited[a][b][0] = node.cost+600;
						}
						
					}
					else {
						if(visited[a][b][1] >= node.cost+100 || visited[a][b][1] ==0 ) {
							queue.offer(new Node(a,b,node.cost+100,i));
							visited[a][b][1] = node.cost+100;
						}
					}
					//System.out.println(a+" : "+b+" : "+node.cost);
				}
			}
		}
	}
	
	static void dfs(boolean[][] visited, Node node) {
		if(node.cost>answer) {
			return;
		}
		
		if(node.x == N-1 && node.y == N-1) {
			answer = Math.min(answer, node.cost);
			return;
		}
		
		for(int i=0; i<deltas.length; i++) {
			int a = node.x+deltas[i][0];
			int b = node.y+deltas[i][1];
			
			if(isIn(a,b) && map[a][b]!=1 && !visited[a][b]) {
				visited[a][b] = true;
				if(check(i+1) == node.dir || check(i-1) == node.dir) {
					//직각(코너도로) : 500 + 100 (코너비용 +직선비용)
					dfs(visited, new Node(a,b,node.cost+600,i));
				}
				else {
					dfs(visited, new Node(a,b,node.cost+100,i));
				}
				//System.out.println(a+" : "+b+" : "+node.cost);
				visited[a][b] = false;
			}
		}
	}
	static class Node{
		int x,y,cost,dir;
		//cost : 비용
		//dir:현재바라보는 방향

		public Node(int x, int y, int cost, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.dir = dir;
		}
		
	}
	
	static int check(int a) {
		if(a==4) return 0;
		else if(a==-1) return 3;
		else return a;
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}
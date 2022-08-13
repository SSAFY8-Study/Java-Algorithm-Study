package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 9.
@see https://www.acmicpc.net/problem/2206 벽 부수고 이동하기
@performance
@difficulty Gold4
@category #최단경로
@note 최단경로, 미로찾기 -> bfs 
사이클 판별 -> dfs
*/
public class BOJ_02206 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] graph, clone;
	static int cnt=0;
	static int N,M;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static int oneCnt=2;
	static int min=Integer.MAX_VALUE;
	static int block; //부셔야 하는 block순서
	// 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new int[N+1][M+1];
		clone = new int[N+1][M+1];
		
		
		for(int i=1; i<N+1; i++) {
			String[] str = br.readLine().split("");
			for(int j=1; j<M+1; j++) {
				graph[i][j]=Integer.parseInt(str[j-1]);
				if(graph[i][j]==1) graph[i][j] = oneCnt++;
				clone[i][j]=graph[i][j];
			}
		}
		//어떤 graph[i][j]==1를 부실건가 . 몇전째 oneCnt를 부실건가!!
		combination(2, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else
			System.out.println(min-oneCnt);

//		for(int i=1; i<N+1; i++) {
//		for(int j=1; j<M+1; j++) {
//			System.out.print(graph[i][j]);
//		}
//		System.out.println();
//	}
	}
	
	
	static int bfs(int startX, int startY) { //정점 노드
		Queue<Node> queue = new LinkedList<>();
		Node root = new Node(startX, startY);
		queue.offer(root);
		int cnt = oneCnt;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();

			if(clone[node.getX()][node.getY()]==0 || (graph[node.getX()][node.getY()]==block)) {
				clone[node.getX()][node.getY()]=cnt++; //방문처리
				for(int i=0; i<deltas.length; i++) {
					int a = node.getX()+deltas[i][0];
					int b = node.getY()+deltas[i][1];
					if(isIn(a,b)) {
						queue.offer(new Node(a,b));
					}
				}
			} //단순 길찾기
			
			/*
			else {//block을 사용할 수 있는지 없는지? 
				if(block) {
					boolean use=false;
					for(int i=0; i<deltas.length; i++) {
						int a = node.getX()+deltas[i][0];
						int b = node.getY()+deltas[i][1];
						if(isIn(a,b) && graph[a][b]==0) {
							use = true;
							queue.offer(new Node(a,b));
						}
					}
					if(use) {//갈수있는 길이 있다!
						block=false;//사용처리
						graph[node.getX()][node.getY()]=2; //방문처리
					}
				}
			}
			*/
		}
		return clone[N][M]-1;
	}
	
	static void combination(int start, int nth) {
		if(nth==1) {
			int value = bfs(1,1);
			if(value!=-1) {
				min = Math.min(min, value);
			}
			
//			for(int i=1; i<N+1; i++) {
//				for(int j=1; j<M+1; j++) {
//					System.out.print(clone[i][j]+ " ");
//				}
//				System.out.println();
//			}
//			System.out.println("======");
			
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<M+1; j++) {
					clone[i][j]=graph[i][j];
				}
			}
			
			
			return;
		}
		
		for(int i=start; i<oneCnt; i++) {
			block = i;
			combination(i+1,nth+1);
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=1 && a<N+1 && b>=1 && b<M+1;
	}
	
	static class Node{
		int x, y;
		Node(int x, int y){
			this. x = x;
			this. y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
		
	}

}
/*
 * 6 6
010001
010101
010101
010101
010110
000110
*/
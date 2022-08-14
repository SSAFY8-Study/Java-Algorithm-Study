package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2022. 8. 15.
@see https://www.acmicpc.net/problem/2206 벽 부수고 이동하기
@performance 220988	1556
@difficulty G4
@category #
@note 다시 풀어보기 */
public class BOJ_02206 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[][] graph;
	static boolean[][][] visited;
	static int[][] deltas = {{-1,0},{0,-1},{0,1},{1,0}};

	//최단거리는 벽을 부셨거나 부시지 않았을 때 둘 중 하나의 값
	//visited가 여러군데 겹칠 수가 있음 
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][2]; //[][][0]은 블록을 안부심 [][][1]은 블록 부심
		
		for(int i=1; i<N+1; i++) {
			String[] str = br.readLine().split("");
			for(int j=1; j<M+1; j++) {
				graph[i][j]=Integer.parseInt(str[j-1]);
			}
		}
		bfs(1,1);
		
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y,1,0));
		visited[x][y][0] = true; //방문표시
		visited[x][y][1] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int nextCnt = node.cnt+1;
			
			if(node.x == N && node.y == M) {
				System.out.println(node.cnt);
				return;
			}
			
			for(int i=0; i<deltas.length; i++) {
				int a = node.x + deltas[i][0];
				int b = node.y + deltas[i][1];
				
				if(isIn(a,b)) {
					if(graph[a][b]==1) {
						if(node.wallCnt == 0 && !visited[a][b][1]) { //벽을 부신적이 없다면
							queue.offer(new Node(a,b,nextCnt,1));//부신다.
							visited[a][b][1] = true;
						}
						//부신적 있다면 더이상 부시면 안됨
					}
					else {
						if(node.wallCnt == 1 && !visited[a][b][1]) { //부신적이 있따면
							visited[a][b][1] = true;
							queue.offer(new Node(a,b,nextCnt,1));
						}
						else if(node.wallCnt == 0 && !visited[a][b][0]) {//부신적이 없다.
							visited[a][b][0]=true;
							queue.offer(new Node(a,b,nextCnt,0));
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
	//자식들마다 각자의 길을 간다.
	
	static boolean isIn(int a, int b) {
		return a>=1 && a<N+1 && b>=1 && b<M+1;
	}
	
	static class Node{
		int x, y, cnt, wallCnt;
		public Node(int x, int y, int cnt, int wallCnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.wallCnt = wallCnt;
		}
	}
}
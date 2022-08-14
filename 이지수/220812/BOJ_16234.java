package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 14.
@see https://www.acmicpc.net/problem/16234 인구 이동
@performance 295988	584
@difficulty G5
@category #
@note */
public class BOJ_16234 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,L,R;
	static int[][] map, temp;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
	static int result=0;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][N];
		temp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				temp[i][j] = map[i][j];
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			int cnt = 0;

			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			if(cnt == N*N) break; //연합이 없다.
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = temp[i][j]; //map에 값 집어넣기
				}
			}
			result++;
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(result);
	}
	
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		ArrayList<Node> list = new ArrayList<>();
		queue.offer(new Node(x,y));
		list.add(queue.peek());
		visited[x][y]= true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int i=0; i<deltas.length; i++) {
				int a = node.x + deltas[i][0];
				int b = node.y + deltas[i][1];
				

				//차이가 L~R 사이면 연결되어 있다고 간주
				if(isIn(a,b) && Math.abs(map[a][b]-map[node.x][node.y])>=L 
						&& Math.abs(map[a][b]-map[node.x][node.y])<=R
						&& !visited[a][b]) {
					queue.offer(new Node(a,b));
					list.add(new Node(a,b));
					visited[a][b]=true;
				}
			}
		}

		if(list.size()>1) { //연합은 2개 이상 국가
			int sum = 0;
			for(int i=0; i<list.size(); i++) {
				sum += map[list.get(i).x][list.get(i).y];
			}
			//(연합의 인구수) / (연합을 이루고 있는 칸의 개수)
			int union = sum / list.size(); 
			
			for(int i=0; i<list.size(); i++) {
				temp[list.get(i).x][list.get(i).y] = union;
				//일단 temp에 저장 -> 아직 map전체를 다 안돌았을 수도 있기 때문
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 13.
@see https://www.acmicpc.net/problem/2667 단지번호붙이기
@performance 12076	84
@difficulty Silver 1
@category #
@note 이코테 0510 음료수 얼려먹기와 동일한 문제
dfs 배열 탐색을 배웠다.
*/
public class BOJ_02667 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
	static int cnt=2;
	static int houseCnt = 0;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
//				if(map[i][j]==1 && dfs(i,j,true)) {
//					cnt++;
//					list.add(houseCnt);
//					houseCnt = 0;
//				}
				
				if(map[i][j]==1 && !visited[i][j]) {
					bfs(i,j);
					cnt++;
					list.add(houseCnt);
					houseCnt=0;
				}
			}
		}
		
		list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1, o2);
			}
		});
		
		sb.append((cnt-2)+"\n");
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static boolean dfs(int x, int y, boolean flag) {
		
		if(map[x][y] == 1) {
			map[x][y] = cnt;
			houseCnt++;
		}
		
		for(int i=0; i<deltas.length; i++) {
			int a = x+deltas[i][0];
			int b = y+deltas[i][1];
			
			if(isIn(a,b) && map[a][b]==1) {
				dfs(a,b,true);
			}
		}
		return flag;
		
		/*
		if(!isIn(x,y)) {
			return false;
		}
		
		if(map[x][y]==1) {
			map[x][y]=cnt;//방문표시
			dfs(x+1,y);
			dfs(x-1,y);
			dfs(x,y+1);
			dfs(x,y-1);
			return true;
		}
		return false;
		*/
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y));
		visited[x][y] = true;
		houseCnt++;
		
		while(!queue.isEmpty()) {
			
			Node node = queue.poll();
			
			for(int j=0; j<deltas.length; j++) {
				int a = node.x+deltas[j][0];
				int b = node.y+deltas[j][1];
				
				if(isIn(a,b) && !visited[a][b] && map[a][b]==1) {
					queue.offer(new Node(a,b));
					houseCnt++;
					visited[a][b]=true;
				}
			}
			
		}
	}
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}
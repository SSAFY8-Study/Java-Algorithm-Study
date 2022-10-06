// 19728KB	156ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int T, N;
	static int start = 0, end;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(bf.readLine());
			end = N + 1;
			posArr = new Pos[N + 2];
			visited = new boolean[N + 2];
			
			graph = new ArrayList<ArrayList<Node>>();
			for(int i = 0; i < N + 2; i++)
				graph.add(new ArrayList<Node>());
			
			//	좌표 입력
			for(int i = 0; i < N + 2; i++) {
				int y, x;
				tokens = new StringTokenizer(bf.readLine());
			
				y = Integer.parseInt(tokens.nextToken());
				x = Integer.parseInt(tokens.nextToken());
			
				posArr[i] = new Pos(y, x);
			}
			
			//	그래프 생성
			for(int i = 0; i < N + 1; i++) {
				for(int j = i + 1; j < N + 2; j++) {
					int dist = mDist(posArr[i], posArr[j]);
					
					graph.get(i).add(new Node(j, dist));
					graph.get(j).add(new Node(i, dist));
				}
			}
			
			if(bfs())
				sb.append("happy\n");
			else
				sb.append("sad\n");
			
		}
		
		System.out.print(sb);
	}
	
	static boolean bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[0] = true;
		q.offer(0);
		
		while(!q.isEmpty()) {
			int curNum = q.poll();	//	현재 정점
			
			for(int i = 0; i < graph.get(curNum).size(); i++) {
				Node nextNode = graph.get(curNum).get(i);
				int nextNum = nextNode.num;
				int nextDist = nextNode.cost;
				
				if(visited[nextNum] || nextDist > 1000)
					continue;
				
				if(nextNum == N + 1)	//	도착지점일 경우
					return true;
				
				visited[nextNum] = true;
				q.offer(nextNum);
			}
		}
		
		return false;
	}
	
	static class Pos {
		int y;
		int x;
		
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Node {
		int num;
		int cost;
		
		Node(int vertex, int cost) {
			this.num = vertex;
			this.cost = cost;
		}
	}
	
	static boolean[] visited;
	
	static Pos[] posArr;
	
	static ArrayList<ArrayList<Node>> graph;
	
	static int mDist(Pos p1, Pos p2) {
		return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
	}
}
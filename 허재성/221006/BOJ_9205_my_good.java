//  13248KB	116ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static List<ArrayList<Integer>> graph;

	static int T, N;
	static Point[] pList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());

		for (int t = 0; t < T; t++) {
			int y, x;
			
			N = Integer.parseInt(bf.readLine());
			pList = new Point[N + 2];
			visited = new boolean[N + 2];

			tokens = new StringTokenizer(bf.readLine());
			y = Integer.parseInt(tokens.nextToken());
			x = Integer.parseInt(tokens.nextToken());
			pList[0] = new Point(0, y, x);

			for (int i = 1; i <= N; i++) {
				tokens = new StringTokenizer(bf.readLine());
				y = Integer.parseInt(tokens.nextToken());
				x = Integer.parseInt(tokens.nextToken());
				pList[i] = new Point(i, y, x);
			}

			tokens = new StringTokenizer(bf.readLine());
			y = Integer.parseInt(tokens.nextToken());
			x = Integer.parseInt(tokens.nextToken());
			pList[N + 1] = new Point(N + 1, y, x);

			makeGraph();
			bfs();
		}
		
		System.out.println(sb);
	}

	static void bfs() {
		int pNum = pList[0].pNum; // 시작 정점 번호
		Queue<Integer> q = new LinkedList<Integer>();

		visited[pNum] = true; // 시작 정점 방문
		q.offer(pNum);

		while (!q.isEmpty()) {
			int curNum = q.poll(); // 현재 정점 번호

			for (int i = 0; i < graph.get(curNum).size(); i++) {
				int nextNum = graph.get(curNum).get(i); // 다음 정점 번호

				if(visited[nextNum])
					continue;
				
				if (nextNum == N + 1) {	//	다음 정점이 도착 정점일 경우
					sb.append("happy\n");
					return;
				}
				
				visited[nextNum] = true;
				q.offer(nextNum);
			}
		}
		
		sb.append("sad\n");
	}

	static void makeGraph() {
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 2; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < N + 1; i++) {
			for (int j = i + 1; j < N + 2; j++) {
				Point p1 = pList[i];
				Point p2 = pList[j];

				if (mDist(p1, p2) <= 1000) {
					graph.get(p1.pNum).add(p2.pNum);
					graph.get(p2.pNum).add(p1.pNum);
				}
			}
		}
	}

	static class Point {
		int pNum;
		int y;
		int x;

		Point(int pNum, int y, int x) {
			this.pNum = pNum;
			this.y = y;
			this.x = x;
		}
	}

	static int mDist(Point p1, Point p2) {
		return (int) (Math.abs(p1.y - p2.y)) + (int) (Math.abs(p1.x - p2.x));
	}
}
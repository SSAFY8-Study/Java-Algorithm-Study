package sw_a_practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @since 2022. 10. 7.
 * @see
 * @git
 * @performance
 * @category #
 * @note
 */
public class BOJ_17472 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M; // 지도 크기(N * M)
	static int[][] map; // 주어진 지도(0 : 바다, 1 : 육지)
	static int cnt; // 섬의 개수(섬 번호 : 1 ~ cnt)
	static int[][] visited; // 각 칸이 바다인지 섬인지(섬일 경우 섬 번호)
	static int[][] dist; // dist[i][j] : 섬 i와 j 사이의 거리
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new int[N][M];
		visited = new int[N][M];

		// map 입력
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && visited[i][j] == 0) {
					cnt++;
					bfs(i, j, cnt);
				}
			}
		}
		
//		for(Island i : islandList)
//			System.out.println(i);
		
		parents = new int[cnt + 1];
		for(int i = 0; i <= cnt; i++)
			parents[i] = i;

		dist = new int[cnt + 1][cnt + 1];
		for (int i = 0; i <= cnt; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0;
		}

		for (int i = 0; i < islandList.size() - 1; i++) {
			for (int j = i + 1; j < islandList.size(); j++) {
				Island i1 = islandList.get(i);
				Island i2 = islandList.get(j);

				calcDistOfTwoIslands(i1, i2);
			}
		}
		
//		System.out.println("섬들 사이 거리 정보");
//		for(int i = 1; i <= cnt; i++) {
//			for(int j = 1; j <= cnt; j++)
//				System.out.print(dist[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println("+++++++++++++++++++++++++++++++");
		
		for(int i = 1; i <= cnt - 1; i++) {
			for(int j = i + 1; j <= cnt; j++) {
				if(dist[i][j] == Integer.MAX_VALUE)	//	두 섬 i, j 잇는 간선 없음
					continue;	
				edgeList.add(new Edge(i, j, dist[i][j]));
			}
		}
//		System.out.println("간선들");
//		System.out.println(edgeList);
		
		if(edgeList.size() >= cnt - 1) {
			kruskal();
			System.out.println(ans);
		}
		else
			System.out.println(-1);
	}
	
	static class Edge implements Comparable<Edge> {
		int v1;	//	섬1 번호
		int v2;	//	섬2 번호
		int dist;	//	두 섬 사이 다리 거리
		
		Edge(int v1, int v2, int dist) {
			this.v1 = v1;
			this.v2 = v2;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.dist, e.dist);
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "(" + v1 + ", " + v2 + ", (" + dist + "))";
		}
	}
	
	static List<Edge> edgeList = new ArrayList<Edge>();
	
	static int[] parents;
	
	static int findParent(int x) {
		if(x == parents[x])
			return x;
		
		return parents[x] = findParent(parents[x]);
	}
	
	static void unionParents(int x1, int x2) {
		x1 = findParent(x1);
		x2 = findParent(x2);
		
		if(x1 == x2)
			return;
		
		if(x1 > x2)
			parents[x1] = x2;
		else
			parents[x2] = x1;
	}
	
	static void kruskal() {
		int numOfEdges = 0;
		Collections.sort(edgeList);
		
		for(Edge e : edgeList) {
			int v1 = e.v1;
			int v2 = e.v2;
			int dist = e.dist;
			
			if(findParent(v1) != findParent(v2)) {
				unionParents(v1, v2);
				numOfEdges++;
				ans += dist;
			}
			
			if(numOfEdges == cnt - 1)
				break;
		}
		
		if(numOfEdges != cnt - 1)
			ans = -1;
	}

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}

	// 섬의 첫 육지를 발견하면 섬 객체 생성하여 리스트에 삽입
	static void bfs(int y, int x, int num) {
//		System.out.println("bfs(" + y + ", " + x + ", " + num + ")");
		
		Queue<int[]> q = new LinkedList<int[]>();
		visited[y][x] = num;
		q.offer(new int[] { y, x });

		Island island = new Island(num);
		island.addLand(new Pos(y, x));

		while (!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.peek()[1];
			q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];

				if (!isIn(ny, nx) || map[ny][nx] == 0 ||  visited[ny][nx] != 0) // 범위 밖이거나 바다거나 이미 다른 섬에 포함되거나 현재 섬에 포함된 경우
					continue;

				visited[ny][nx] = num; // (ny, nx)가 num 섬에 포함됨
				q.offer(new int[] { ny, nx });
				island.addLand(new Pos(ny, nx));
			}
		}

		islandList.add(island); // 섬을 섬 리스트에 삽입
	}

	static class Pos { // 좌표 정보
		int y;
		int x;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		public String toString() {
			return "(" + y + ", " + x + ") ";
		}
	}

	static class Island {
		int num; // 섬의 번호
		List<Pos> posList; // 섬에 포함된 육지 좌표 정보

		Island(int num) {
			this.num = num;
			posList = new ArrayList<Pos>();
		}

		void addLand(Pos p) {
			posList.add(p);
		}
		
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append(String.format("섬 번호 = %d\n섬에 포함된 육지 : ", num));
			for(Pos p : posList)
				str.append(p);
			str.append("\n");
			
			return str.toString();
		}
	}
 
	static List<Island> islandList = new ArrayList<Island>();

	// 두 섬 i1과 i2 사이의 최단거리 구함
	static void calcDistOfTwoIslands(Island i1, Island i2) {
		int num1 = i1.num;
		int num2 = i2.num;
//		System.out.println("두 섬 " + num1 + ", " + num2 + " 사이 거리 구하기");
		
		List<Pos> posList1 = i1.posList;
		List<Pos> posList2 = i2.posList;
		int size1 = posList1.size();
		int size2 = posList2.size();

		Pos p1 = null, p2 = null;
		for (int i = 0; i < size1; i++) {
			p1 = posList1.get(i);
			for (int j = 0; j < size2; j++) {
				p2 = posList2.get(j);

				int y1 = p1.y;
				int x1 = p1.x;
				int y2 = p2.y;
				int x2 = p2.x;

				if (y1 != y2 && x1 != x2)
					continue;

				if (y1 == y2) {
					int minX = Integer.min(x1, x2);
					int maxX = Integer.max(x1, x2);
					boolean ok = true;

					for (int x = minX + 1; x <= maxX - 1; x++) {
						if (map[y1][x] == 1) { // 중간에 방해지역 있을 경우
							ok = false;
							break;
						}
					}

					if (!ok)
						continue;
					
					if(maxX - minX - 1 <= 1)
						continue;

//					dist[num1][num2] = Math.min(dist[num1][num2], maxX - minX - 1);
//					dist[num2][num1] = dist[num1][num2];                         
					
					if(dist[num1][num2] > maxX - minX - 1) {
						dist[num1][num2] = maxX - minX - 1;
//						System.out.println("p1 = " + p1 + ", p2 = " + p2 + " 사이 거리가 최소 거리 = " + dist[num1][num2]);
						dist[num2][num1] = dist[num1][num2];
					}
				} else if (x1 == x2) {
					int minY = Integer.min(y1, y2);
					int maxY = Integer.max(y1, y2);
					boolean ok = true;

					for (int y = minY + 1; y <= maxY - 1; y++) {
						if (map[y][x1] == 1) { // 중간에 방해지역 있을 경우
							ok = false;
							break;
						}
					}

					if (!ok)
						continue;
					
					if(maxY - minY - 1 <= 1)
						continue;

//					dist[num1][num2] = Math.min(dist[num1][num2], maxY - minY - 1);
//					dist[num2][num1] = dist[num1][num2];
					if(dist[num1][num2] > maxY - minY - 1) {
						dist[num1][num2] = maxY - minY - 1;
//						System.out.println("p1 = " + p1 + ", p2 = " + p2 + " 사이 거리가 최소 거리 = " + dist[num1][num2]);
						dist[num2][num1] = dist[num1][num2];
					}
				}
			}
		}
	}
}
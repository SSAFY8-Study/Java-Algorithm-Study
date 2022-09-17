import java.util.*;

public class programmers_L3_등산코스정하기 {
	static class Node implements Comparable<Node> {
		int n;
		int cost;

		public Node(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}

		public String toString() {
			return "n : " + n + " cost : " + cost;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static ArrayList<Node>[] graph;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int[] answer = {};

		graph = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int[] path : paths) {
			int i = path[0];
			int j = path[1];
			int w = path[2];
			graph[i].add(new Node(j, w));
			graph[j].add(new Node(i, w));
		}
		Arrays.sort(summits);
		int[] result = new int[2];
		result[1] = Integer.MAX_VALUE;

		for (int gate : gates) {
			int[] distance = dijkstra(gate, gates, summits, n);

			for (int summit : summits) {
				if (distance[summit] != Integer.MAX_VALUE) {
					if (result[1] == distance[summit]) {
						result[0] = Math.min(result[0], summit);
					} else if (result[1] > distance[summit]) {
						result[0] = summit;
						result[1] = distance[summit];
					}
				}
			}
		}

		return result;
	}

	static int[] dijkstra(int start, int[] other, int[] summits, int n) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n + 1];

		int[] distance = new int[n + 1];

		Arrays.fill(distance, Integer.MAX_VALUE);

		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node node = pq.poll();

			for (int summit : summits) {
				if (node.n == summit) {
					return distance;
				}
			}

			for (Node next : graph[node.n]) {
				boolean isGate = false;
				int cost = Math.max(next.cost, node.cost);
				for (int o : other) {
					if (next.n == o) {
						isGate = true;
						break;
					}
				}
				if (!isGate && distance[next.n] > cost) {
					distance[next.n] = cost;
					pq.add(new Node(next.n, cost));
				}
			}
		}
		return distance;
	}
}

//출입구, 쉼터, 산봉우리가 존재
//양방향 통행
//등산 코스에서 쉼터 혹은 산봉우리를 방문할 때마자 휴식을 취할 수 있다.
//휴식 없이 이동해야 하는 시간 중 가장 긴 시간을 해당 등산콧의 intensity라고 부른다.

//출발해서 산봉우리를 도착하고 원래의 출입구로 돌아오는 등산 코스를 정하려고 한다.
//paths의 길이가 길다.. n도 크다..
//간선마다의 가중치가 존재
//BFS? DFS? 백트레킹이 불가능할 거 같은데 다익스트라?

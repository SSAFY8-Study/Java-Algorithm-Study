package study5;
/**
 * 15908	180
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3190_뱀 {
	static StringTokenizer st;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		List<Node> apple = new LinkedList<>();
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			apple.add(new Node(a - 1, b - 1));
		}
		int C = Integer.parseInt(br.readLine());
		Map<Integer, String> direction = new HashMap<>();
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String b = st.nextToken();
			direction.put(a, b);
		}
		Queue<Node> q = new LinkedList<>();
		int cnt = 0;
		int d = 0;
		Node cur = new Node(0, 0);
		q.add(cur);
		while(true) {
			cnt++;
			int nx = cur.x+dir[d][0];
			int ny = cur.y+dir[d][1];
			if(nx<0 || nx>=N || ny<0 || ny>=N) break;
			cur = new Node(nx, ny);
			if (q.contains(cur)) break;
			
			q.add(cur);
			boolean check = true;
			for(int i=0; i<apple.size(); i++) {
				if(apple.get(i).equals(cur)) {
					apple.remove(i);
					check = false;
					
				}
			}
			if(check) {
				q.poll();
			}
			
			if(direction.containsKey(cnt)) {
				if(direction.get(cnt).equals("D")) {
					d++;
				}else {
					d--;
				}
				if(d<0) {
					d = 3;
				} else if(d>3) {
					d = 0;
				}
			}
		}
		System.out.println(cnt);
	}
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		
	}
	
}

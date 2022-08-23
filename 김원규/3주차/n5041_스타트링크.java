import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n5041_스타트링크 {

	static int f, s, g, u, d;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
	
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		visited = new boolean[f+1];
		
		int res = BFS();
		if(res != -1) System.out.println(res);
		else System.out.println("use the stairs");
	}
	
	static int BFS() {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(s);
		visited[s] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			
			for(int l = 0; l < len; l++) {
				int temp = q.poll();
				
				if(temp == g) return cnt;
				
				int case1 = temp + u;
				int case2 = temp - d;
				
				if(case1 <= f && !visited[case1]) {
					visited[case1] = true;
					q.add(case1);
				}
				if(0 < case2 && !visited[case2]) {
					visited[case2] = true;
					q.add(case2);
				}
			}
			cnt++;
		}
		
		return -1;
	}

}

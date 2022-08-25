import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n1326_폴짝폴짝 {

	static int n, a, b;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st;

		n = Integer.parseInt(str);
		
		arr = new int[n+1];
		visited = new boolean[n+1];
		
		str = bf.readLine();
		st = new StringTokenizer(str, " ");
		
		for(int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		str = bf.readLine();
		st = new StringTokenizer(str, " ");
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		System.out.println(BFS());
	}
	
	static int BFS() {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(a);
		visited[a] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			
			for(int l = 0; l < len; l++) {
				int temp = q.poll();
				
				if(temp == b) return cnt;
				
				for(int i = temp; i <= n; i+=arr[temp]) {
					if(!visited[i]) {
						visited[i] = true;
						q.add(i);
					}
				}
				for(int i = temp; i >= 1; i-=arr[temp]) {
					if(!visited[i]) {
						visited[i] = true;
						q.add(i);
					}
				}
			}
			cnt++;
		}
		
		return -1;
	}

}

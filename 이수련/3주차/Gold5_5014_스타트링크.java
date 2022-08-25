import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gold5_5014_스타트링크 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int F = Integer.parseInt(st.nextToken()); // 총 층수 
		int S = Integer.parseInt(st.nextToken()); // 강호씨 위치 
		int G = Integer.parseInt(st.nextToken()); // 스타트링크 층수 
		int U = Integer.parseInt(st.nextToken()); // up
		int D = Integer.parseInt(st.nextToken()); // down 

		boolean [] visited = new boolean[F+1];

		//bfs
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {S,0}); // 입력위치 , 엘베 카운트 
		visited[S] = true;
		
		while(!q.isEmpty()) {
			int [] current = q.poll();
			
			if(current[0] == G) { // 스타트링크 있는 곳이면 
				System.out.println(current[1]);
				return;
			}
			
			int nFloor = current[0]+U; // 올라가는 경우 
			if(nFloor < F && !visited[nFloor]) {
				visited[nFloor] = true;
				q.offer(new int[] {nFloor, current[1]+1});
			}
			nFloor = current[0] - D; // 내려가는 경우 
			if(nFloor >= 0 &&!visited[nFloor]) {
				visited[nFloor] = true;
				q.offer(new int[] {nFloor, current[1]+1});
			}
			
		}
		System.out.println("use the stairs");
	}

}

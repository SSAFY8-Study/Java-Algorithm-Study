import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Silver_1326_폴짝폴짝 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());//징검다리 수 
		int [] stones = new int [N+1]; 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <N+1; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); //개구리 있는 곳 
		int b = Integer.parseInt(st.nextToken()); //가고 싶은 곳  
		//입력 완료

		boolean[] visited = new boolean[N+1]; 
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {a,1});
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			if(current[0] == b) {
				System.out.println(current[1]-1);
				return;
			}
			if((b - current[0]) % stones[current[0]] == 0) {
				System.out.println(current[1]);
				return;
			}
			
			int nStone = current[0] + stones[current[0]];
			while(nStone < stones.length) {
				if(!visited[nStone]) {
					visited[nStone] = true;
					q.offer(new int[] {nStone,current[1]+1});
					nStone+= stones[current[0]];
				}
			}
			nStone = current[0] - stones[current[0]];
			while(nStone > 0) {
				if(!visited[nStone]) {
					visited[nStone] = true;
					q.offer(new int[] {nStone,current[1]+1});
					nStone-= stones[current[0]];
				}
			}
			
			
		}
		System.out.println(-1);
		
		
	}
}


/*
5
1 2 2 1 2
1 5
*/


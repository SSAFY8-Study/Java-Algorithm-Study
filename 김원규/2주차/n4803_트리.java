import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n4803_트리 {

	static int n, m, count;
	static int[] visit;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		
		int t = 1;
		while(true) {
			str = bf.readLine();
			st = new StringTokenizer(str, " ");
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if(n == 0 && m == 0) break;
			
			arr = new int[n+1][n+1];
			visit = new int[n+1];
			for(int i = 0; i < m; i++) {
				str = bf.readLine();
				st = new StringTokenizer(str, " ");
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr[a][b] = 1;
				arr[b][a] = 1;
			}
			
			count = 0;
			
			for(int i = 1; i <= n; i++) {
				if(visit[i] == 0) {
					visit[i] = 1;
					if(checkTree(i, i) == 1) count++;
				}
			}
			
			if(count > 1) System.out.println("Case " + t + ": A forest of " + count + " trees.");
			else if(count == 1) System.out.println("Case " + t + ": There is one tree.");
			else System.out.println("Case " + t + ": No trees.");
			t++;
		}

	}
	
	static int checkTree(int num, int from) {
		for(int i = 1; i <= n; i++) {
			if(arr[num][num] == 1) return -1;
			if(arr[num][i] == 1 && i != from) {
//				System.out.println(num + " " + i);
				if(visit[i] == 0) {
					visit[i] = 1;
					visit[i] = checkTree(i, num);
					if(visit[i] == -1) return -1;
				}
				else return -1;
			}
		}
		
		return 1;
	}

}

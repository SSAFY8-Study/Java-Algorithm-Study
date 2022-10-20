import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02660 {
	
	static final int INF = 987654321; 
	public static void main(String[] args) throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());

		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i!=j) map[i][j] = INF;
			}
		}
		
		while(true) {
			StringTokenizer tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken())-1;
			int b = Integer.parseInt(tokens.nextToken())-1;
			
			if(a==-2 && b==-2) break;
			
			map[a][b] =1;
			map[b][a] =1;
			
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][k] + map[k][j] < map[i][j]){
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int min = INF;
		int[] answer = new int[N];
		for(int i=0; i<N; i++) {
			int result =0;
			for(int j=0; j<N; j++) {
				result = Math.max(result, map[i][j]);
			}
			answer[i] = result;
			min = Math.min(min, result);
		}
		
		
		int cnt =0;
		StringBuilder output= new StringBuilder();
		for(int i=0; i<N; i++) {
			if(answer[i] == min) {
				cnt++;
				output.append((i+1) +" ");
			}
		}
		
		System.out.println(min +" " + cnt);
		System.out.println(output);
		
	}
}


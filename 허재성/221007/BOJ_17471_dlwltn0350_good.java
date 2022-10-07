import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 7.
@see https://www.acmicpc.net/problem/17471 게리맨더링
@performance	12720KB	96ms
@difficulty G4
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] area;
	static int result = Integer.MAX_VALUE;
	static boolean[] check;	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		area = new int[N+1];
		
		tokens = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			area[i] = Integer.parseInt(tokens.nextToken());
		}
		
		//그래프 초기화 1~N
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=1; i<N+1; i++) {
			tokens = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(tokens.nextToken());
			
			for(int k=0; k<n; k++) {
				graph.get(i).add(Integer.parseInt(tokens.nextToken()));
			}
		}
		
		subset(1, new boolean[N+1]);
		
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else
			System.out.println(result);
	}
	
	static void subset(int nth, boolean[] choosed) {
		if(nth == choosed.length) {
			//true가 A지역, false가 B지역
			
			
			int startA = -1, startB = -1;
			int populationA = 0, populationB = 0;
			
			////////bfs 시작점 찾기
			for(int i=1; i<choosed.length; i++) {
				if(choosed[i] && startA == -1) {
					startA = i;
				}else if(!choosed[i] && startB == -1) {
					startB = i;
				}
			}
			
			if(startA == -1 || startB == -1) return;
			
			check = new boolean[N+1];
			check[startA] = true;
			dfs(startA, choosed); //A지역
			
			for(int i=1; i<check.length; i++) {
				if(choosed[i] != check[i])
					return;
			}
			
		
			for(int i=1; i<choosed.length; i++) {
				if(choosed[i]) {
					populationA += area[i];
					choosed[i] = false;
				}
				else {
					populationB += area[i];
					choosed[i] = true;
				}
			}
			
			check = new boolean[N+1];
			check[startB] = true;
			dfs(startB, choosed); //B지역
			
			for(int i=1; i<check.length; i++) {
				if(choosed[i] != check[i]) {
					for(int k=1; k<choosed.length; k++) {
						choosed[k] = choosed[k] ? false : true; // 복구
					}
					return;
				}
			}
			
			result = Math.min(result, Math.abs(populationB-populationA));
			
			for(int i=1; i<choosed.length; i++) {
				choosed[i] = choosed[i] ? false : true; // 복구
			}
			
			
//			for(int i=1; i<choosed.length; i++) {
//				System.out.print(choosed[i]+" ");
//			}
//			System.out.println();
			
			return;
		}
		
		choosed[nth] = true;
		subset(nth+1, choosed);
		choosed[nth] = false;
		subset(nth+1, choosed);
	}
	
	static void dfs(int index, boolean[] choosed) {
		
		for(int i=0; i<graph.get(index).size(); i++) {
			int a = graph.get(index).get(i);
			if(choosed[a] && !check[a]) {
				check[a] = true;
				dfs(a,choosed);
			}
		}
	}

}
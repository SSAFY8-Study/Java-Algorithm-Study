package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9466_텀프로젝트 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int T, N, cnt=0;
	static int[] team;
	static boolean[] visited, isTeam;
	public static int dfs(int root, int select) {
		if(visited[select]) {
			isTeam[root]=true;
			return select;
		}
		visited[root]=true;
		visited[select]=true;
		int check = dfs(select,team[select]);
		if(check != select ) {
			isTeam[root] = true;
			return check;
		}else return root;
		
	}
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(input.readLine());
		for(int t=0;t<T;t++) {
			cnt =0;
			N = Integer.parseInt(input.readLine());
			tokens=new StringTokenizer(input.readLine());
			team = new int[N];
			for(int i=0;i<N;i++) team[i]=Integer.parseInt(tokens.nextToken())-1; 
			visited = new boolean[N];
			isTeam = new boolean[N];
			for(int i=0;i<N;i++) {
				if(!isTeam[i]) dfs(i, team[i]); 				
			}
			for(int i=0;i<N;i++) if(!isTeam[i]) cnt++;
			output.append(cnt+"\n");
		}
		System.out.println(output);
	}

}

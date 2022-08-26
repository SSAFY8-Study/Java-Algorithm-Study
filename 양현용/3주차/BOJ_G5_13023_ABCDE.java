package on0821;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_13023_ABCDE {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M,A,B;
	static List<Integer>[] lists;
	static boolean[] visited;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		
		lists=new ArrayList[N];
		for(int i=0;i<N;i++) {
			lists[i]=new ArrayList<>();
		}
	
		for(int i=0;i<M;i++) {
			tokens=new StringTokenizer(input.readLine());
			A=Integer.parseInt(tokens.nextToken());
			B=Integer.parseInt(tokens.nextToken());
			lists[A].add(B);
			lists[B].add(A);
		}
		
		visited=new boolean[N];
		for(int i=0;i<N;i++) {
			flag=false;
			visited=new boolean[N];
			visited[i]=true;
			dfs(i,1);
			if(flag) {
				System.out.println(1);
				break;
			}
		}
		if(!flag) System.out.println(0);
		
	}
	
	static void dfs(int idx, int cnt) {
		if(flag)
			return;
		if(cnt==5) {
			flag=true;
			return;
		}
			for(int i=0;i<lists[idx].size();i++) {
				if(!visited[lists[idx].get(i)]) {
				visited[lists[idx].get(i)]=true;
				dfs(lists[idx].get(i),cnt+1);
				visited[lists[idx].get(i)]=false;
			}
		}
	}
	
}
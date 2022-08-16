import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 14.
@see https://www.acmicpc.net/problem/2644
@performance 11684, 84
@category #dfs
@note dfs 인접리스트 사용 
*/
public class BOJ_S2_2644_촌수계산{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M,num1,num2,X,Y;
	static List<Integer>[] list;
	static boolean[] visited;
	static int result=0;
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		N=Integer.parseInt(input.readLine());
		tokens=new StringTokenizer(input.readLine());
		num1=Integer.parseInt(tokens.nextToken());		
		num2=Integer.parseInt(tokens.nextToken());		
		M=Integer.parseInt(input.readLine());
		list=new LinkedList[N+1];
		visited=new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i]=new LinkedList<>();
		}
		
		for(int i=1;i<=M;i++) {
			tokens=new StringTokenizer(input.readLine());
			X=Integer.parseInt(tokens.nextToken());
			Y=Integer.parseInt(tokens.nextToken());
			list[X].add(Y);
			list[Y].add(X);
		}
		
		dfs(num1,0);

//		for(List l:list) {
//			System.out.print(l+" ");
//		}
//		System.out.println();
//		System.out.println("result: "+result);
		
		if(result==0) {
			System.out.println(-1);
		}
		else {
			System.out.println(result);
		}
	}
	
	//num1에서 시작, num2에 도착 시 누적된 cnt 저장
	static void dfs(int start,int cnt) {
		if(start==num2) {
			result=cnt;
			return;
		}
		visited[start]=true;
		for(int num:list[start]) {
			if(!visited[num]) {
				dfs(num,cnt+1);
			}
		}
	}
}
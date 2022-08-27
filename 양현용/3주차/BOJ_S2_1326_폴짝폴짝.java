package on0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 24.
@see
@git
@youtube
@performance
@category #
@note */
public class BOJ_S2_1326_폴짝폴짝 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N,A,B;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		//input = new BufferedReader(new StringReader(src));
		input = new BufferedReader(new FileReader("./input.txt"));
		N=Integer.parseInt(input.readLine());
		arr=new int[N+1];
		tokens=new StringTokenizer(input.readLine());
		for(int i=1;i<N+1;i++) {
			arr[i]=Integer.parseInt(tokens.nextToken());
		}
		tokens=new StringTokenizer(input.readLine());
		A=Integer.parseInt(tokens.nextToken());
		B=Integer.parseInt(tokens.nextToken());
		
		visited=new boolean[N+1];
		System.out.println(bfs(A));
		
	}
	
	static int bfs(int start) {
		Queue<Integer> queue=new LinkedList<>();
		queue.offer(start);
		visited[start]=true;
		int depth=0;
		while(!queue.isEmpty()) {
			int size=queue.size();
			while(size-->0) {
				int now=queue.poll();
				if(now==B) return depth;
				for(int i=now;i<N+1;i+=arr[now]) {
					if(!visited[i]) {
						visited[i]=true;
						queue.offer(i);
					}
				}
				for(int i=now;i>0;i-=arr[now]) {
					if(!visited[i]) {
						visited[i]=true;
						queue.offer(i);
					}
				}
			}
			depth++;
		}
		return -1;
	}
}

//private static String src = "";	
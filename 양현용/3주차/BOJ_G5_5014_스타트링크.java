package on0821;

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
@since 2022. 8. 21.
@see	https://www.acmicpc.net/problem/5014
@performance	57596	180
@category #BFS
@note */
public class BOJ_G5_5014_스타트링크 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int F,S,G,U,D;
	static Queue<Integer> queue;
	static boolean[] visited;
	static int[] arr;
	static int floor;
	static int[] move;

	public static void main(String[] args) throws IOException {
		//input = new BufferedReader(new StringReader(src));
		input = new BufferedReader(new FileReader("./input.txt"));
		tokens=new StringTokenizer(input.readLine());
		F=Integer.parseInt(tokens.nextToken());
		S=Integer.parseInt(tokens.nextToken());
		G=Integer.parseInt(tokens.nextToken());
		U=Integer.parseInt(tokens.nextToken());
		D=Integer.parseInt(tokens.nextToken());
		
		visited=new boolean[1000001];
		arr=new int[F+1];
		move=new int[2];
		
		//시작 지점과 도착 지점이 같을 때
		if(S==G) {
			output.append(0);
		}
		//다를때
		else {
			bfs(S);
			
			if(arr[G]!=0) {
				output.append(arr[G]);
			}
			else {
				output.append("use the stairs");
			}
		}
		
		System.out.println(output);
	}
	
	static void bfs(int start) {
		queue=new LinkedList<Integer>();
		queue.offer(start);
		visited[start]=true;
		arr[start]=0;
		
		while(!queue.isEmpty()) {
//			System.out.println(queue);
//			System.out.println(Arrays.toString(arr));
//			System.out.println();
			int now=queue.poll();
			move[0]=-1*D;
			move[1]=U;
			
			for(int i=0;i<move.length;i++) {
				int nx=now+move[i];
				if(isIn(nx) &&!visited[nx]) {
					visited[nx]=true;
					arr[nx]=arr[now]+1;
					queue.offer(nx);
				}
			}
		}
	}
	static boolean isIn(int x) {
		return 0<x && x<=F;
	}
private static String src = "2 2 1 0 1";
}

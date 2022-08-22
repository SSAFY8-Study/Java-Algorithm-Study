package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_12851_숨바꼭질2 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N,K;
	static int[] visited=new int[100001]; // 최단 시간으로 찾는 방법의 수를 구해야함. 최단 시간일 때, 해당 노드를 두 가지 방식으로 지나갈 수 있으므로 이미 방문했다고 하더라도 또 다시 탐색해야함.
	                                      // 따라서 visited를 boolean이 아닌 int형으로 해서 해당 노드를 거쳐갈 때의 시간을 저장. 이후 다시 해당 노드를 탐색했을 때의 시간이 visited에 저장된 시간보다 크면 탐색 못 하도록!
	static Queue<int[]> q = new LinkedList<int[]>();
	static int min = Integer.MAX_VALUE,result=0; // 최단 시간, 방법의 수
	
	public static void main(String[] args) throws Exception {
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		K=Integer.parseInt(tokens.nextToken());
		
		q.add(new int[] {N,0});
		while(q.size()>0) {
			int[] info = q.poll();
			N=info[0];
			visited[N]=info[1];
			if(min<info[1]) break;  // 최단 시간보다 긴 방법은 더 이상 보지않음
			if(N==K) { // 수빈이가 동생을 찾았을 경우
				result++; // 방법의 수 증가
				min=info[1]; // 최단 거리 저장			
			}
			
			if(N*2<100001&&(visited[N*2]==0||visited[N*2]==info[1]+1)) // 해당 노드를 처음 방문하거나, 해당 노드를 이미 방문했다면 그때 방문한 시간과 현재 해당 노드를 방문했을 때(+1)의 시간을 비교하여 같으면 탐색
				q.add(new int[] {N*2,info[1]+1});                   // 또한 N*2가 K+2보다 크다면 
			if(N+1<100001&&(visited[N+1]==0||visited[N+1]==info[1]+1))
				q.add(new int[] {N+1,info[1]+1});
			if(N-1>=0&&(visited[N-1]==0||visited[N-1]==info[1]+1))
				q.add(new int[] {N-1,info[1]+1});
		}
		System.out.println(min+"\n"+result);
	}
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 15.
@see
@performance
@category #
@note */
class Node{
	int idx, weight;
	public Node(int idx, int weight) {
		this.idx=idx;
		this.weight=weight;
	}
}
public class BOJ_G4_1967_트리의지름{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, num1, num2, W;
	static List<Node>[] list;
	static int max=Integer.MIN_VALUE;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		N=Integer.parseInt(input.readLine());
		list=new LinkedList[N+1];
		for(int t=1;t<=N;t++) {
			list[t]=new LinkedList<>();
		}
		for(int t=1;t<=N-1;t++) {
			tokens=new StringTokenizer(input.readLine());
			num1=Integer.parseInt(tokens.nextToken());
			num2=Integer.parseInt(tokens.nextToken());
			W=Integer.parseInt(tokens.nextToken());
			list[num1].add(new Node(num2,W));
			list[num2].add(new Node(num1,W));
		}
		
		//모든 노드를 기준으로 경로의 합을 구하는 dfs 돌리기
		for(int i=1;i<=N;i++) {
			visited=new boolean[N+1];
			visited[i]=true;
//			System.out.print("i: "+i);
//			System.out.println();
			dfs(i,0);
//			System.out.println();
		}
		System.out.println(max);
		
	}
	
	//지나가는 노드들의 가중치의 합을 sum에 누적
	//sum 값이 max값 보다 클 경우만 값 저장
	static void dfs(int idx, int sum) {
		if(max<sum) {
			max=sum;
//			System.out.println(idx+" sum "+sum);
		}
		
		for(Node node:list[idx]) {
			if(!visited[node.idx]) {
				visited[node.idx]=true;
//				System.out.print(idx+" ");
				dfs(node.idx,sum+node.weight);

			}
		}
	}
	
}
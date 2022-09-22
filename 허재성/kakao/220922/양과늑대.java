import java.util.ArrayList;

/**
 * 
 * @author koreii
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/92343
 * @difficuly LEVEL3
 * @performance 86.6MBMB   2.98ms
 * @category # DFS, 완전탐색
 * @memo # 이진트리의 노드의 개수가 최대 17개(숫자가 매우 작으므로 완전탐색을 생각해볼 수 있음)
 * @memo # 각 정점을 방문할 때마다 해당 정점이 양인지, 늑대인지 확인 후 방문 가능한지 check
 * @memo # 방문 가능할 경우 다음 방문할 노드에서 현재 방문 노드는 제거하고, 현재 방문 노드의 자식 노드들을 방문할 노드에 추가
 * @memo # 각 노드에 방문할 때마다 그 노드에 대하여 다음에 방문할 수 있는 노드를 따로 유지해야 함.
 * @memo # ArrayList에서 int element를 제거할 경우 Integer로 변환 후 찾아서 제거해야 한다.  
 * 
 */

class Solution {
	int maxS = -1;
	public int solution(int[] info, int[][] edges) {
		ArrayList<Integer> nextVisit = new ArrayList<>();
		nextVisit.add(0);
		
		dfs(info, edges, 0, 0, 0, nextVisit);
		return maxS;
	}
	
	void dfs(int[] info, int[][] edges, int curNode, int numS, int numW, ArrayList<Integer> nextVisit) {
		if(info[curNode] == 0)	//	방문한 노드가 양일 경우
			numS++;
		else					//	늑대일 경우
			numW++;
		
		if(numS <= numW)	//	늑대가 양보다 많거나 같을 경우
			return;
		maxS = Integer.max(maxS, numS);
		
		ArrayList<Integer> tmp = (ArrayList<Integer>)nextVisit.clone();	//	다음에 방문할 정점들
		
		//	그냥 curNode로 넣으면 int로 받아서 index가 curNode인 것을 제거함.
		// Integer node = curNode;
        tmp.remove(new Integer(curNode));	//	현재 방문한 정점은 제거
		// tmp.remove(curNode);

		for(int i = 0; i < edges.length; i++) {
			int parent = edges[i][0];	//	부모 정점
			int child = edges[i][1];	//	자식 정점
			
			if(parent == curNode)		//	부모 정점이 현재 정점일 경우
				tmp.add(child);		//	자식 정점들을 다음에 방문할 수 있음
		}
		
		for(int next : tmp)
			dfs(info, edges, next, numS, numW, tmp);
	
	}
}

public class 양과늑대 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
		
		System.out.println(sol.solution(info, edges));

		info = new int[] { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 };
		edges = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 6, 9 },
				{ 9, 10 } };

		System.out.println(sol.solution(info, edges));
	}
}

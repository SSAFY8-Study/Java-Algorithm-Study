import java.util.ArrayList;

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
		tmp.remove(Integer.valueOf(curNode));	//	현재 방문한 정점은 제거
		
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

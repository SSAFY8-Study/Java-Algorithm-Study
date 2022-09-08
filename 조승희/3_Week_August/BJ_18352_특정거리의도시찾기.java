package study3_08;
/**
 * 386048	3368
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BJ_18352_특정거리의도시찾기 {

	static int N, M, K, X;
	static Map<Integer, List<Integer>> map;
//	static boolean[] visited;
	static List<Integer> answer = new ArrayList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		X = sc.nextInt();
		
		map = new HashMap<Integer, List<Integer>>();
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			List<Integer> newValue = new ArrayList<Integer>();
			newValue.add(b);
			if(map.containsKey(a)) {
				newValue.addAll(map.get(a));
			}
			map.put(a, newValue);
		}
		int[] visited = new int[N+1];
		for(int i=0; i<visited.length; i++) {	
			visited[i] = -1;	// -1 : 방문하지 않았다는 표시
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(X);
		visited[X] = 0;	//시작 지점은 0으로 표시
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(!map.containsKey(cur)) continue;	//map에 없으면 넘어가기
			for(int d: map.get(cur)) {
				if(visited[d]==-1) {	//방문하지 않았으면
					visited[d] = visited[cur]+1;	// 거리 표시
					if(visited[d]==K) answer.add(d);	//K와 같으면 answer에 추가
					q.add(d);	//다음에 방문하기 위해 q에 추가
				}
			}
		}
		
//		dfs(X, 0);
		if(answer.size()==0) {	// answer에 아무것도 없으면 -1출력
			System.out.println(-1);
			return;
		}
		answer.sort(Comparator.naturalOrder());//오름차순 정렬
		for(int a: answer) {	
			System.out.println(a);
		}

	}
	/*
	static void dfs(int x, int cnt) {
		visited[x] = true;
		if(cnt==K) {
			System.out.println("cnt "+cnt);
			answer.add(x);
			return;
		}
		for(int d:map.get(x)) {
			System.out.println(d);
			if(!visited[d]) {
				dfs(d, cnt+1);
			}
		}
	}*/

}

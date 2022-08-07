import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author seungheecho
 * @number bj11724
 * @see https://www.acmicpc.net/problem/11724
 * @performance 312832	1312
 * 
 */
public class BJ_11724_연결요소의개수 {
	
	static boolean[] visited;
	static HashMap<Integer, List<Integer>> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		map = new HashMap<Integer, List<Integer>>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// java.lang.UnsupportedOperationException 오류로 arraylist생성
			List<Integer> aList = new ArrayList<>(Arrays.asList(a));
			List<Integer> bList = new ArrayList<>(Arrays.asList(b));
			if(map.containsKey(a)) {//key가 이미 있을때
				map.get(a).addAll(bList);
			}else {
				map.put(a, bList);
			}
			if(map.containsKey(b)){//key가 이미 있을때
				map.get(b).addAll(aList);
			}else {
				map.put(b, aList);
			}
		}
		
		int cnt = 0;
		for(int key: map.keySet()) {
			if(!visited[key]) {	//방문하지 않았으면
				cnt++;
				dfs(key);
			}
		}
//		if(n!=0 && m==0) cnt = 1;
		for(int i=1; i<n+1; i++) {	//아무와도 연결되지않은 노드
			if(!map.containsKey(i)) {
				cnt++;
			}
		}
		System.out.println(cnt);
		
	}
	static void dfs(int k) {
		visited[k] = true;	//방문표시
		for(int v: map.get(k)) {	// 연결된 value가져오기
			if(!visited[v]) {
				dfs(v);
			}
		}
	}

}

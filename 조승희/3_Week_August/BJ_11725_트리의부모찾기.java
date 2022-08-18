package study3_08;
/**
 * 194724	2480
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ_11725_트리의부모찾기 {

	static List<Integer>[] tree;
	static boolean[] visited;
	static int[] ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		tree = new ArrayList[n+1];
		visited = new boolean[n+1];
		ans = new int[n+1];
		for (int i = 1; i < n+1; i++) {
            tree[i] = new ArrayList<>();
        }
		
		for(int i=0; i<n-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			tree[a].add(b);
			tree[b].add(a);
		}
		
		dfs(1);
		
		for(int i=2; i<n+1; i++) {
			System.out.println(ans[i]);
		}
		

	}

	static void dfs(int x) {
		visited[x] = true;
		for(int t: tree[x]) {
			if(visited[t]) continue;
			ans[t] = x;
			dfs(t);
		}
	}
}

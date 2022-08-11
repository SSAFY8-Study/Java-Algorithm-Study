package study2_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author seungheecho
 * @see https://www.acmicpc.net/problem/4803
 * @performance 
 * @memo 조합으로도 풀어보기
 * 
 */

public class BJ_4803_트리 {
	static boolean[] visited;
	static ArrayList<Integer>[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 0;
		while(true) {
			T++;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if(n==0 && m==0) {
				break;
			}
			
            arr = new ArrayList[n+1];
            visited = new boolean[n+1];
            for (int i = 1; i < n+1; i++) {
                arr[i] = new ArrayList<>();
            }

            for (int i = 1; i < m+1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a].add(b);
                arr[b].add(a);
            }

            int cnt = 0;
            for (int i = 1; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (dfs(0, i)) cnt++;
                }
            }
			System.out.print("Case "+T+": ");
			if(cnt==0) {
				System.out.println("No trees.");
			}else if(cnt==1) {
				System.out.println("There is one tree.");
			}else {
				System.out.println("A forest of "+cnt+" trees.");
			}
		}
			
	}

	public static boolean dfs(int start, int num) {
        for (int i : arr[num]) {
            if (i == start) continue;
            if (visited[i]) return false;
            visited[i] = true;
            if (!dfs(num, i)) return false;
        }
        return true;
    }
	
}

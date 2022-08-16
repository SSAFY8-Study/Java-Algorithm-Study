package on0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author 양현용
 * @since 2022. 8. 10.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class BOJ_G4_4803_트리 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int n, m, start, end, cnt,line,result,tc=1;
	static boolean[] visited;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		while (true) {
			tokens = new StringTokenizer(input.readLine());
			n = Integer.parseInt(tokens.nextToken());
			m = Integer.parseInt(tokens.nextToken());
			if(n==0 && m==0) break;
			visited = new boolean[n+1];
			list=new ArrayList[n+1];
			result=0;
			for(int i=1;i<=n;i++) {
				list[i]=new ArrayList<>();
			}
			for (int i = 1; i <= m; i++) {
				tokens=new StringTokenizer(input.readLine());
				start=Integer.parseInt(tokens.nextToken());
				end=Integer.parseInt(tokens.nextToken());
				list[start].add(end);
				list[end].add(start);
			}
			for(int i=1;i<=n;i++) {
				cnt=0;
				line=0;
				dfs(i);
				if(line==(cnt-1)*2) {
					result++;
				}
			}
			output.append(String.format("Case %d: ", tc));
			if(result==0) {
				output.append("No trees.\n");
			}
			else if(result==1) {
				output.append("There is one tree.\n");
			}
			else {
				output.append(String.format("A forest of %d trees.\n", result));
			}
			tc++;
		}
		System.out.println(output);
	}
	
	static void dfs(int num) {
		visited[num] = true;
		cnt++;
		line+=list[num].size();
		for (int j : list[num]) {
			if (!visited[j]) {
				dfs(j);
			}
		}

	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static List<Integer>[] list;
	static boolean[] visit;
	static boolean ccc;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new List[n];
		
		for(int i = 0; i < n; i++) list[i] = new ArrayList<>();
		
		for(int i = 0; i < m; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i = 0; i < n; i++) {
			visit = new boolean[n];
			check(i, 1);
			
			if(ccc) {
				break;
			}
		}		
		
		if(ccc) System.out.println(1);
		else System.out.println(0);

	}
	
	static void check(int num, int cnt) {
		if(cnt == 5) {
			ccc = true;
			return;
		}
		visit[num] = true;
		for(int i = 0; i < list[num].size(); i++) {
			if(!visit[list[num].get(i)]) {
				visit[list[num].get(i)] = true;
				check(list[num].get(i), cnt+1);
				visit[list[num].get(i)] = false;
			}
		}
	}

}
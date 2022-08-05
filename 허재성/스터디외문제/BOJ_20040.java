package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040 {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int output = 0;
	
	static int[] parents;
	
	static int findParent(int x) {
		if(x == parents[x])
			return x;
		
		return parents[x] = findParent(parents[x]);
	}
	
	static void unionXY(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x > y)
			parents[y] = x;
		else
			parents[x] = y;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		parents = new int[N];
		for(int i = 0; i < N; i++)
			parents[i] = i;
		
		for(int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			if(findParent(a) == findParent(b)) {
				output = i + 1;
				break;
			}
			unionXY(a, b);
		}
		
		System.out.println(output);
	}
}

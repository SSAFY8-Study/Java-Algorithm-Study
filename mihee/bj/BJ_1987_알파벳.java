package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ_1987_알파벳{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C, result = Integer.MIN_VALUE;
	static char [][]alphabet;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};//상,하,좌,우
	static boolean [][]visited;
	static boolean []usedAlphabet = new boolean [26];

	public static void dfs(int r, int c, int cnt, boolean[][] visited, boolean[] usedAlphabet) {

		for(int d=0;d<deltas.length;d++) {
			int nx = r+deltas[d][0];
			int ny = c+deltas[d][1];
			if(nx<0||nx>=R||ny<0||ny>=C) continue;
			if(usedAlphabet[alphabet[nx][ny]-'A']) {
				result=Math.max(result, cnt);
			}
			if(!visited[nx][ny]&&!usedAlphabet[alphabet[nx][ny]-'A']) {
				visited[nx][ny]=true;
				usedAlphabet[alphabet[nx][ny]-'A']=true;
				dfs(nx, ny, cnt+1, visited, usedAlphabet);
				visited[nx][ny]=false;
				usedAlphabet[alphabet[nx][ny]-'A']=false;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R=Integer.parseInt(tokens.nextToken());
		C=Integer.parseInt(tokens.nextToken());
		alphabet = new char[R][];
		visited = new boolean[R][C];
		
		for(int r=0;r<R;r++) alphabet[r]=input.readLine().toCharArray();
		visited[0][0]=true;
		usedAlphabet[alphabet[0][0]-'A']=true;
		dfs(0, 0, 1, visited, usedAlphabet);
		System.out.println(result);
	}
}

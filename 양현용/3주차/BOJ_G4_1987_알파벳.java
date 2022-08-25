package on0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 18.
@see
@git
@youtube
@performance
@category #
@note */
public class BOJ_G4_1987_알파벳 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int R,C;
	static char[][] map;
	static boolean[] alpha;
	static int cnt, result;
	static int[][] deltas= {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
//		input = new BufferedReader(new FileReader("./input.txt"));
		tokens=new StringTokenizer(input.readLine());
		R=Integer.parseInt(tokens.nextToken());
		C=Integer.parseInt(tokens.nextToken());
		map=new char[R][C];
		for(int i=0;i<R;i++) {
			String str=input.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		result = 0;
		alpha = new boolean[26];
		dfs(0, 0, 0);
		System.out.println(result);
	}
	
	static void dfs(int x,int y,int cnt) {
		if(alpha[map[x][y]-'A']) {
			result=Math.max(result, cnt);
			return;
		}
		
		for(int i=0;i<deltas.length;i++) {
			int nx=x+deltas[i][0];
			int ny=y+deltas[i][1];
			if(isIn(nx,ny)) {
				alpha[map[x][y]-'A']=true;
				dfs(nx,ny,cnt+1);
				alpha[map[x][y]-'A']=false;
			}
		}
	}
	
	static boolean isIn(int x,int y) {
		return 0<=x && x<R && 0<=y && y<C;
	}
	
	private static String src = "3 6\r\n" + 
			"HFDFFB\r\n" + 
			"AJHGDH\r\n" + 
			"DGAGEH";
}


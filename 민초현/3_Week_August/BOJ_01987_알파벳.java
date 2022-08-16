package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21852KB		884ms
// 지나온 알파벳과 좌표에 따른 dp 적용하면 더 빨라질 것으로 예상됨
public class BOJ_01987_알파벳 {

	static int r, c;
	static int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static char[][] board;
	
	public static int dfs(int y, int x, int path) {
		
		int nxtBit = 0;
		int[] nxtPos = new int[2];
		
		int ans = 0;
		for(int[] del: deltas) {
			nxtPos[0] = y + del[0];
			nxtPos[1] = x + del[1];

			if(0<=nxtPos[0] && nxtPos[0]<r && 0<=nxtPos[1] && nxtPos[1]<c) {
				
				nxtBit = 1 << ( board[nxtPos[0]][nxtPos[1]]-'A' );
				if( (path & nxtBit )==0 ) {
					ans = Math.max(ans, dfs(nxtPos[0], nxtPos[1], path|nxtBit));
				}
			}
		}
		return ans+1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		board = new char[r][];
		for(int i = 0; i<r; i++)
			board[i] = in.readLine().toCharArray();
		
		System.out.println(dfs(0, 0, (1<<board[0][0]-'A')));
	}

}

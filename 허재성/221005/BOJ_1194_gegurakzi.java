//	14272KB	108ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
	
	public static int R, C;
    public static int[][] delta = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public static char[][] map;
    public static boolean[][][] visited;
    public static Queue<int[]> q;
    public static int[] start;
    
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
        R= Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        start = new int [4];
    	map = new char[R][C];
    	for(int r=0; r<R; r++) {
    		String s = br.readLine();
    		for(int c=0; c<C; c++) {
    			map[r][c] = s.charAt(c);
    			if(map[r][c] == '0') {
    				start[0] = r;
    				start[1] = c;
    			}
    		}    	
    	}
    	q = new LinkedList<>();
    	visited = new boolean[R][C][1<<6];
    	q.offer(start);
    	visited[start[0]][start[1]][0] = true;
    	
    	while(!q.isEmpty()) {
    		int[] p = q.poll();
    		if(map[p[0]][p[1]] == '1') {
    			System.out.println(p[3]);
    			return;
    		}
    		// key
    		if(map[p[0]][p[1]] >= 'a' && map[p[0]][p[1]] <= 'f') {
    			p[2] |= 1 << (map[p[0]][p[1]]-'a');
    		}
    		for(int[] d : delta) {
    			if(!isOut(p[0]+d[0], p[1]+d[1], p[2])) {
    				q.offer(new int[] {p[0]+d[0], p[1]+d[1], p[2], p[3]+1});
    				visited[p[0]+d[0]][p[1]+d[1]][p[2]] = true;
    			}
    		}
    	}
    	System.out.println(-1);
	}
	
    public static boolean isOut(int r, int c, int key) {
    	return  (r < 0 || r >= R || c < 0 || c >= C ||
    			map[r][c] == '#' ||
    			visited[r][c][key] ||
    			((map[r][c] >= 'A' && map[r][c] <= 'F') && !((key & 1<<(map[r][c]-'A')) > 0 )));
    }
    
}
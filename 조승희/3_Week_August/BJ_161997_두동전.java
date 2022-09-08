package study3_08;
/**
 * 	14736	264
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_161997_두동전 {

	static int N, M, min=Integer.MAX_VALUE;
	static char[][] map;
	static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
//	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		int[][] coin = new int[2][2];	// 두 동전의 위치
		int idx = 0;	//coin 배열 입력을 위한 idx
		for(int i=0; i<N; i++) {
//			map[i] = br.readLine().toCharArray();
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='o') {
					coin[idx++] = new int[]{i, j};
//					System.out.println(coin[cnt-1][0]+" "+coin[cnt-1][1]);
				}
			}
		}

		dfs(coin[0][0],coin[0][1],coin[1][0],coin[1][1],0);
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	
	static void dfs(int x1, int y1, int x2, int y2, int cnt) {
		if(cnt>10) return;	//10번을 초과한 경우
		if(!isIn(x1, y1) && !isIn(x2, y2)) return;	// 둘 다 벗어난 경우
		if((isIn(x1, y1)&&!isIn(x2, y2)) || (!isIn(x1, y1)&&isIn(x2, y2))) { //둘중 하나만 떨어진 경우
			min = Math.min(min, cnt);
			return;
		}
		
		for(int[] d: dir) {
			int nx1 = x1+d[0];
			int ny1 = y1+d[1];
			int nx2 = x2+d[0];
			int ny2 = y2+d[1];
			
//			if(!isIn(nx1, ny1) || !isIn(nx2, ny2)) continue;
			
			if(isIn(nx1, ny1) && map[nx1][ny1] == '#') {	//벽을 만나면 이동하지 않음
				nx1 = x1;
				ny1 = y1;
			}
			if(isIn(nx2, ny2) && map[nx2][ny2] == '#') {
				nx2 = x2;
				ny2 = y2;
			}
			dfs(nx1, ny1, nx2, ny2, cnt+1);
		}
		
	}
	
	static boolean isIn(int x, int y) {		// map 안에 있는지 확인
		return x>=0 && x<N && y>=0 && y<M;
	}

}

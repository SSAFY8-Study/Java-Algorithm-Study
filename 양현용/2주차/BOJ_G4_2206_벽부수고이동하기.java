import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 9.
@see
@git
@youtube
@performance
@category #
@note */

class Walker {
	public int x,y,cnt,wall;
	public Walker(int x, int y, int cnt, int wall) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.wall = wall;
	}
}
public class BOJ_G4_2206_벽부수고이동하기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] deltas= {{0,1},{1,0},{0,-1},{-1,0}};
	static Queue<Walker> queue;
	static Walker temp;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		map=new int[N][M];
		for(int i=0;i<N;i++) {
			String str=input.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}
		visited=new boolean[N][M][2];
		flag=false;
		temp=new Walker(0,0,1,1);
		
		bfs();
		
		if(flag) {
			System.out.println(temp.cnt);			
		}
		else {
			System.out.println(-1);
		}
	}
	
	static void bfs() {
		queue=new LinkedList<>();
		queue.offer(temp);
		visited[0][0][0]=true;
		visited[0][0][1]=true;
		
		while(!queue.isEmpty()) {
			temp=queue.poll();
			
			if(temp.x==N-1 && temp.y==M-1) {
				flag=true;
				break;
			}
			
			for(int i=0;i<deltas.length;i++) {
				int nx=temp.x+deltas[i][0];
				int ny=temp.y+deltas[i][1];
				if(isIn(nx,ny)&&!visited[nx][ny][temp.wall]) {
					if(map[nx][ny]==0) {
						visited[nx][ny][temp.wall]=true;
						queue.offer(new Walker(nx,ny,temp.cnt+1,temp.wall));
					}
					if(map[nx][ny]==1) {
						if(temp.wall==1) {
							visited[nx][ny][1]=true;
							queue.offer(new Walker(nx,ny,temp.cnt+1,0));
						}
					}
				}
			}
		}
	}
	

	
	
	static boolean isIn(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
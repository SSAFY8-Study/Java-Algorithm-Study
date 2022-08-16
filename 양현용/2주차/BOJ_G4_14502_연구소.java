import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 15.
@see
@performance 296532, 840
@category #bfs	#완전탐색
@note */

class Point{
	int x,y;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
public class BOJ_G4_14502_연구소{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[][] arr, arrC;
	static int[][] deltas= {{-1,0},{0,1},{1,0},{0,-1}};
	static Queue<Point> queue;
	static int cnt,result=0;
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		arr=new int[N][M];
		for(int i=0;i<N;i++) {
			tokens=new StringTokenizer(input.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j]=Integer.parseInt(tokens.nextToken());
			}
		}

		recur(0);
		
		System.out.println(result);
	}
	
	//0인 지점에 벽을 3번 세우고 bfs 시작
	static void recur(int cnt) {
		if(cnt==3) {
			bfs();
			return;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr[i][j]==0) {
					arr[i][j]=1;
					recur(cnt+1);
					arr[i][j]=0;
				}
			}
		}

	}
	
	//arr의 2인 지점 queue에 저장
	//queue에 저장된 좌표를 기준으로 사방탐색 실행
	//0인 지점을 만나면 바이러스 확산
	//확산이 끝나고 남은 0 지점 카운트 및  max값 설정
	static void bfs() {
		queue=new LinkedList<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr[i][j]==2) {
					queue.add(new Point(i,j));
				}
			}
		}
		
		arrC = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arrC[i][j]=arr[i][j];
			}
		}
		
		while(!queue.isEmpty()) {
			Point p=queue.poll();
			int x=p.x;
			int y=p.y;
			for(int i=0;i<deltas.length;i++) {
				int nx=x+deltas[i][0];
				int ny=y+deltas[i][1];
				if(isIn(nx,ny) && arrC[nx][ny]==0) {
					queue.add(new Point(nx,ny));
					arrC[nx][ny]=2;
				}
			}
		}
		
		cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arrC[i][j]==0) {
					cnt++;
				}
			}
		}
		result=Math.max(result, cnt);
	}

	static boolean isIn(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
	
}
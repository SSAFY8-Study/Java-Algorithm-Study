package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 5.
@see https://www.acmicpc.net/problem/21610 마법사 상어와 비바라기
@performance 26344	260
@category #구현
@note */
public class BOJ_21610 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int[][] deltas = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}}; //←, ↖, ↑, ↗, →, ↘, ↓, ↙ 
	static int[][] diagonal = {{-1,-1},{-1,1},{1,1},{1,-1}};
	static List<cloud> cloud = new ArrayList<>();
	static boolean[][] check; //구름이 생길 수 있는지 없는지 여부
	static int d,s;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][N+1]; //가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)
		check = new boolean[N+1][N+1]; //구름이 생겼는지 아닌지 체크
		
		for(int i=1; i<=N; i++) {
			tokens = new StringTokenizer(br.readLine()); 
			for(int j=1; j<=N; j++) {
				map[i][j]=Integer.parseInt(tokens.nextToken());
				check[i][j]=false;//초기화
			}
		}
		
		// (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름
		cloud cloud1 = new cloud(N,1);
		cloud cloud2 = new cloud(N,2);
		cloud cloud3 = new cloud(N-1,1);
		cloud cloud4 = new cloud(N-1,2);
		
		cloud.add(cloud1);
		cloud.add(cloud2);
		cloud.add(cloud3);
		cloud.add(cloud4);

		for(int i=0; i<M; i++) {
			//1. 모든 구름이 di 방향으로 si칸 이동한다.
			tokens = new StringTokenizer(br.readLine());
			d = Integer.parseInt(tokens.nextToken());
			s = Integer.parseInt(tokens.nextToken());
			
			for(int j=0; j<cloud.size(); j++) {
				int nr = cloud.get(j).getX()+(deltas[d-1][0]*s);
				int nc = cloud.get(j).getY()+(deltas[d-1][1]*s);
				if(isIn(nr,nc)) {
					cloud.get(j).setX(nr);
					cloud.get(j).setY(nc);
				}
				else {
					cloud.get(j).setX(isIn(nr));
					cloud.get(j).setY(isIn(nc));
				}
				//System.out.println(cloud.get(j).getX()+" : "+cloud.get(j).getY());
				check[cloud.get(j).getX()][cloud.get(j).getY()] = true; //구름이 있던 자리 표시
				
				//2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
				map[cloud.get(j).getX()][cloud.get(j).getY()]++;
				
			}
			
			//4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 
			//물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
			
			for(int j=0; j<cloud.size(); j++) {
				int cnt=0;
				for(int k=0; k<diagonal.length; k++) {
					int nr = cloud.get(j).getX() + diagonal[k][0];
					int nc = cloud.get(j).getY() + diagonal[k][1];
					
					if(isIn(nr,nc) && map[nr][nc]!=0) {
						cnt++;
					}
				}
				map[cloud.get(j).getX()][cloud.get(j).getY()]+=cnt;
			}
			
			//3.구름이 모두 사라진다.
			cloud.clear();
			
			//바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
			//이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
			for(int j=1; j<=N; j++) {
				for(int k=1; k<=N; k++) {
					if(!check[j][k] && map[j][k]>=2) {
						map[j][k]-=2;
						cloud.add(new cloud(j,k));//구름 생성!
					}
					if(check[j][k]) check[j][k]=false;
				}
			}
		}
		
		int result = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}
	
	static boolean isIn(int a, int b) {
		return a>=1 && a<=N && b>=1 && b<=N;
	}
	
	static int isIn(int a) {
		if(a>N) {
			if(a%N==0) return N;
			else return a%N;
		}
		else if(a<1){
			//System.out.println(a+ " : "+s);
			//return Math.abs(a+(N*(s/N))+1);
			if(Math.abs(a)%N==0) return N;
			else return N-(Math.abs(a)%N);
		}
		else return a;
	}
	
	static class cloud{
		int x;
		int y;
		cloud(int x, int y){
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
		
	}
}
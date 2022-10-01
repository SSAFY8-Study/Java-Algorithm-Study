import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_17142 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;	//	N은 연구소 한 변 길이, M은 활성화시킬 바이러스 개수
	static int nWall;	//	벽의 개수
	static int nVirus;	//	초기 바이러스 개수
	static int nTarget;	//	바이러스가 퍼져야 하는 개수
	
	static int[][] lab;		//	연구소
	static int[][] timeArr;	//	time[y][x] : lab[y][x]에 활성 바이러스가 도달하는 시간
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static int minTime = -1;	//	바이러스가 모두 퍼지는데 걸리는 최소 시간(-1이면 모두 퍼질 수 없음)
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N && 0 <= x && x < N);
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		lab = new int[N][N];
		timeArr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(tokens.nextToken());
				
				switch(lab[i][j]) {
				case 2:	//	바이러스일 경우
					virusList.add(new Virus(i, j));
					break;
				case 0:	//	바이러스가 퍼져야 하는 빈칸일 경우
					nTarget++;
					break;
				default:
					break;
				}
			}
		}
		
		comb(0, 0, new int[M]);
		System.out.println(minTime);
	}
	
	static void printTime() {
		System.out.println("//////////////////////////////////////////////");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(timeArr[i][j] == -1)
					System.out.print("x ");
				else
					System.out.print(timeArr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("//////////////////////////////////////////////");
	}
	
	static void bfs(int[] selected) {
//		System.out.println("활성화시킨 바이러스");
//		for(int i = 0; i < selected.length; i++)
//			System.out.print(virusList.get(selected[i]) + " ");
//		System.out.println();
		
		Queue<Virus> q = new LinkedList<>();
		int time = 0;
		int cnt = 0;	//	바이러스가 퍼진 빈칸 개수
		
		for(int i = 0; i < selected.length; i++)
			q.add(virusList.get(selected[i]));
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				Virus v = q.poll();
				int cy = v.y;
				int cx = v.x;
				int cur = lab[cy][cx];
				
				timeArr[cy][cx] = time;
				if(cur == 0)	//	현재 칸이 빈칸일 경우
					cnt++;		//	바이러스가 한 칸 퍼짐
				
				if(cnt == nTarget) {	//	전부 퍼졌을 경우
					if(minTime == -1)
						minTime = time;
					else if(minTime > time)
						minTime = time;
				}
				
				for(int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					
					if(!isIn(ny, nx) || lab[ny][nx] == 1 || timeArr[ny][nx] != -1)	//	범위를 벗어나거나 벽이거나 이미 바이러스가 거쳐간 경우
						continue;
					
					//	빈칸이거나 비활성화된 바이러스를 만날 경우 활성화된 바이러스가 됨
					timeArr[ny][nx] = Integer.MAX_VALUE;	//	일단 들어감을 표시
					q.add(new Virus(ny, nx));
				}
				
			}	//	for-qsize end
//			printTime();
			time++;
		
		}	//	while-end
	}	//	bfs-end
	
	static void comb(int nth, int start, int[] selected) {
		if(nth == M) {	
//			System.out.println("combResult : ");
//			System.out.println(Arrays.toString(selected));
			for(int i = 0; i < N; i++)
				Arrays.fill(timeArr[i], -1);
			
			bfs(selected);
			return;
		}
		
		for(int i = start; i < virusList.size(); i++) {
			selected[nth] = i;
			comb(nth + 1, i + 1, selected);
		}
	}
	
	static class Virus {
		int y;
		int x;
		
		Virus(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public String toString() {
			return "Virus [y=" + y + ", x=" + x + "]";
		}
	}
	
	static List<Virus> virusList = new ArrayList<>();
}

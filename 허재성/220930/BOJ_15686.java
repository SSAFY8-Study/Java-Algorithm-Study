import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

public class BOJ_15686 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;					//	도시 크기 N, 남길 치킨집 개수 M
	static int[][] city;				//	도시 정보 
	static int nH = 0, nC = 0;			//	각각 집 개수, 치킨집 개수
	
	static int minChickenDist = Integer.MAX_VALUE;	//	도시의 최소 치킨 거리
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		city = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(tokens.nextToken());
				
				switch(city[i][j]) {
				case 1:
					nH++;
					homeList.add(new Pos(i, j));
					break;
				case 2:
					nC++;
					chickenList.add(new Pos(i, j));
					break;
				default:
					break;
				}
			}
		}
		
		selectM(0, 0, new int[M]);
		System.out.println(minChickenDist);
	}
	
	static int mDist(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2); 
	}
	
	static void selectM(int nth, int start, int[] selected) {
		if(nth == M) {	//	M개 뽑는 한 가지 경우의 수 완성
			int chickenDist = 0;	//	이번 경우의 치킨 거리
			
			for(int i = 0; i < nH; i++) {
				int hY = homeList.get(i).y;
				int hX = homeList.get(i).x;
				int mDist = Integer.MAX_VALUE;	//	i번째 집과 가장 가까운 치킨집까지의 거리
				
				for(int j = 0; j < M; j++) {
					int idx = selected[j];
					int cY = chickenList.get(idx).y;
					int cX = chickenList.get(idx).x;
					
					mDist = Integer.min(mDist, mDist(hY, hX, cY, cX));
				}
				chickenDist += mDist;
			}
			
			if(minChickenDist > chickenDist)
				minChickenDist = chickenDist;
			
			return;
		}
		
		for(int i = start; i < nC; i++) {
			selected[nth] = i;
			selectM(nth + 1, i + 1, selected);
		}
	}
	
	static class Pos {
		int y;
		int x;
		
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + "]";
		}
	}
	
	static List<Pos> homeList = new ArrayList<>();
	static List<Pos> chickenList = new ArrayList<>();
	
}

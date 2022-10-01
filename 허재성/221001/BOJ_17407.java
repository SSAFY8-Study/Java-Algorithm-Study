import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_17406 {
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M, K;
	static int[][] originArr;	//	회전시키기 전 원본 배열
	static int[][] tmp;			//	회전 순서에 따라 회전시키기 위한 배열
	
	static int minValue = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		originArr = new int[N][M];
		tmp = new int[N][M];
		rotationList = new Rotation[K];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++)
				originArr[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int i = 0; i < K; i++) {
			int r, c, s;
			tokens = new StringTokenizer(bf.readLine());
		
			r = Integer.parseInt(tokens.nextToken());
			c = Integer.parseInt(tokens.nextToken());
			s = Integer.parseInt(tokens.nextToken());
		
			rotationList[i] = new Rotation(r, c, s);
		}
		
		perm(0, new boolean[K], new int[K]);
		System.out.println(minValue);
	}
	
	//	배열 값 구하는 함수
	static void calcValue() {
		int value = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++)
				sum += tmp[i][j];
			
			if(sum < value)
				value = sum;
		}
		
		if(minValue > value)
			minValue = value;
	}
	
	//	회전 명령 클래스
	static class Rotation {
		int r;
		int c;
		int s;
		
		Rotation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Rotation [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
	}
	
	static Rotation[] rotationList;
	
	static void reset() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				tmp[i][j] = originArr[i][j];
		}
	}
	
	static void printArr() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				System.out.print(tmp[i][j] + " ");
			System.out.println();
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	static void rotate(Rotation rot) {
		int r = rot.r - 1;
		int c = rot.c - 1;
		int s = rot.s;
		
		//	회전시킬 부분을 따로 떼어낸 target 배열 
		int[][] target = new int[2 * s + 1][2 * s + 1];
		
		//	회전시킬 부분을 target에 복사
		for(int i = 0; i < 2 * s + 1; i++) {
			for(int j = 0; j < 2 * s + 1; j++) 
				target[i][j] = tmp[r - s + i][c - s + j];
		}
		
//		System.out.println("회전시키기 전 부분 배열");
//		for(int i = 0; i < 2 * s + 1; i++) {
//			for(int j = 0; j < 2 * s + 1; j++)
//				System.out.print(target[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println("--------------------------------------------------------");
		
		//	target을 회전시키기
		int size = 2 * s + 1;
		int cnt = s;
		
		for(int t = 0; t < cnt; t++) {
			int luCorner = target[t][t];
			
			for(int i = t + 1; i < size - t; i++) 
				target[i - 1][t] = target[i][t];
			
			for(int i = t + 1; i < size - t; i++) 
				target[size - 1 - t][i - 1] = target[size - 1 - t][i];
		
			for(int i = size - 1 - t; i > t; i--)
				target[i][size - 1 - t] = target[i - 1][size - 1 - t];
			
			for(int i = size - t - 1; i > t; i--)
				target[t][i] = target[t][i - 1];
			
			target[t][t + 1] = luCorner;
		}
		
//		System.out.println("회전시킨 부분 배열");
//		for(int i = 0; i < 2 * s + 1; i++) {
//			for(int j = 0; j < 2 * s + 1; j++)
//				System.out.print(target[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println("--------------------------------------------------------");
		
		//	target을 회전시킨 후 원래 배열에 복사
		for(int i = 0; i < 2 * s + 1; i++) {
			for(int j = 0; j < 2 * s + 1; j++)
				tmp[r - s + i][c - s + j] = target[i][j];
		}
	}
	
	static void perm(int nth, boolean[] isUsed, int[] selected) {
		if(nth == K) {	//	회전순서가 정해짐
			reset();	//	tmp 배열에 원본 배열 복사
			
//			System.out.println("원본 배열");
//			printArr();
			
			//	회전순서에 맞게 회전시킴
			for(int i = 0; i < K; i++) {
				rotate(rotationList[selected[i]]);
//				System.out.println(i + "번째 회전 " + rotationList[selected[i]] + " 후 복붙 후");
//				printArr();
			}
			
			//	회전시킨 배열 최솟값 계산해서 비교
			calcValue();
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(!isUsed[i]) {
				selected[nth] = i;
				isUsed[i] = true;
				perm(nth + 1, isUsed, selected);
				isUsed[i] = false;
			}
		}
	}
}

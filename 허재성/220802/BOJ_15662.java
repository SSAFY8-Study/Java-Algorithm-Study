import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/15662
 * @difficuly Gold5
 * @performance 18216KB   140ms
 * @category # 단순 구현, 시뮬레이션
 * @memo 톱니바퀴를 배열로 표현하는 것, 하나의 톱니바퀴의 회전을 모듈로 구현하는 것->모듈 구현 시 배열 할당 대신 임시변수로 효율적으로 회전 구현
 * @memo 하나의 톱니바퀴를 회전시켰을 때 인접한 톱니바퀴를 시작으로 다른 톱니바퀴들도 회전시킬지 결정해야함
 * @memo 14891과 달리 톱니바퀴 개수가 최대 1000개라 반복문으로 처리가 필요함. 이전 문제도 그렇게 풀어서 어렵진 않았음.
 * @memo indexing, 문제 조건 등 주의, 톱니바퀴는 0~T-1번임. 1~T번이 아님에 주의
 *  
 */
public class BOJ_15662 {
	static int T, K;	//	톱니바퀴 수, 회전 횟수
	static final int CCW = -1, STOP = 0, CW = 1;	//	각각 반시계방향, 무회전, 시계방향
	
	static char[][] gears;	//	gears[i][j] : i번째 톱니바퀴의 j번째 톱니
	
	//	입력 처리
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static void printGears() {
		System.out.println("********************************");
		for(int i = 0; i < T; i++) {
			for(int j = 0; j < 8; j++)
				System.out.print(gears[i][j]);
			System.out.println();
		}
		System.out.println("********************************");
	}
	
	static int answer() {
		int res = 0;
		
		for(int i = 0; i < T; i++) {
			if(gears[i][0] == '1')
				res++;
		}
		
		return res;
	}
	
	//	톱니바퀴 gNum을 dir 방향으로 회전시킴
	static void rotate(int gNum, int dir) {
		if(dir == STOP)	//	무회전일 경우 무시
			return;	
		
		if(dir == CW) {	//	시계방향 회전일 경우
			char tmp = gears[gNum][7];
			for(int i = 6; i >= 0; i--)
				gears[gNum][i + 1] = gears[gNum][i];
			gears[gNum][0] = tmp;
		}
		else {	//	반시계방향 회전일 경우
			char tmp = gears[gNum][0];
			for(int i = 0; i < 7; i++) 
				gears[gNum][i] = gears[gNum][i + 1];
			gears[gNum][7] = tmp;
		}
	}
	
	//	톱니바퀴 gNum을 시작으로 다르 톱니바퀴들을 회전시킬 지 결정 후 회전시킴
	static void rotateAllGears(int gNum, int dir) {
		int[] directions = new int[T];
		directions[gNum] = dir;
		
		//	gNum 기준 왼쪽 톱니바퀴들 검사
		int d = -dir;
		//	next : 왼쪽 톱니바퀴
		for(int next = gNum - 1; next >= 0; next--) {
			//	회전을 결정할 next와 그 오른쪽 톱니바퀴 비교
			if(gears[next + 1][6] != gears[next][2]) {	//	방향 다를 경우 반대방향으로 회전시킴
				directions[next] = d;
				d *= -1;
			}
			else break;
		}
		
		//	gNum 기준 오른쪽 톱니바퀴들 검사
		d = -dir;
		//	next : 오른쪽 톱니바퀴
		for(int next = gNum + 1; next < T; next++) {
			if(gears[next][6] != gears[next - 1][2]) {
				directions[next] = d;
				d *= -1;
			}
			else break;
		}
		
		//	모든 톱니바퀴들 회전 방향 결정됐으므로 모두 회전시킴
		for(int i = 0; i < T; i++) 
			rotate(i, directions[i]);
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		
		gears = new char[T][8];
		
		for(int i = 0; i < T; i++) {
			String line = bf.readLine();
			
			for(int j = 0; j < 8; j++)
				gears[i][j] = line.charAt(j);
		}
		
//		printGears();
		K = Integer.parseInt(bf.readLine());
		
		for(int k = 0; k < K; k++) {
			int gNum, dir;	//	회전시킬 톱니바퀴 번호, 회전 방향
			tokens = new StringTokenizer(bf.readLine());
			
			gNum = Integer.parseInt(tokens.nextToken());
			dir = Integer.parseInt(tokens.nextToken());
			
			//	톱니바퀴 번호 0~T-1
			rotateAllGears(gNum - 1, dir);
//			printGears();
		}
		
		System.out.println(answer());
	}
}

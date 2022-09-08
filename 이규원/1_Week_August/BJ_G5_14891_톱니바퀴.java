import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_14891_톱니바퀴 {
	// 톱니 클래스
	public static class Gear{
		int left;
		int right;
		int North;
		char[] tooths;
		
		// 초기값 설정
		Gear(){
			this.left = 6;
			this.right = 2;
			this.North = 0;
			this.tooths = new char[8];
		}
		
		// true - 시계 방향, false - 반시계방향
		public void rotate(int rotate) {
			if (rotate == 1) { // 시계방향으로 돈 경우
				this.left = (this.left - 1) < 0? 7: this.left - 1;
				this.right = (this.right - 1) < 0? 7: this.right - 1;
				this.North = (this.North - 1) < 0? 7: this.North - 1;
			}else if (rotate == -1){ // 반시계방향으로 돈 경우
				this.left = ((this.left + 1) % 8);
				this.right = ((this.right + 1) % 8);
				this.North = ((this.North + 1) % 8);
			}
		}
		
		// 톱니의 정왼쪽 값
		public int leftValue() {
			return tooths[left];
		}
		
		// 톱니의 정오른쪽값
		public int rightValue() {
			return tooths[right];
		}
		
		// 톱니의 맨 위값
		public int NorthValue() {
			return tooths[North];
		}

		@Override
		public String toString() {
			return "Gear [left=" + left + ", right=" + right + ", North=" + North + ", tooths="
					+ Arrays.toString(tooths) + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		reader = new BufferedReader(new StringReader(str));
		
		// gear를 입력 받음 이때 객체를 생성 thooths를 통해서 char배열의 형태로 값을 받음
		Gear[] gears = new Gear[4];
		for (int i = 0; i < 4; i++) {
			gears[i] = new Gear();
			gears[i].tooths = reader.readLine().toCharArray();
		}
		
		// 회전의 수
		int M = Integer.parseInt(reader.readLine());
		
		// 회전
		for (int m = 0; m < M; m++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			// g : 몇 번의 gear, d : 방향 
			int g = Integer.parseInt(tokens.nextToken()) - 1;
			int d = Integer.parseInt(tokens.nextToken());
			
			// 한번의 회전을 위해서 다음과 같은 배열을 두고 회전
			int[] Move = new int[4];
			// 처음 기어에 대한 정보 저장
			Move[g] = d;
			// 오른쪽을 하나씩 확인하면서 기어 회전에 대해 저장
			for (int i = g, tmp = d; i < gears.length - 1; i++) {
				if (gears[i].rightValue() != gears[i + 1].leftValue()) {
					tmp *= -1;
					Move[i + 1] = tmp;
				}else {
					break;
				}
			}
			// 왼쪽을 하나씩 보며 기어 회전에 대해 저장
			for (int i = g, tmp = d; i >= 1; i--) {
				if (gears[i].leftValue() != gears[i - 1].rightValue()) {
					tmp *= -1;
					Move[i - 1] = tmp;
				}else {
					break;
				}
			}
//			System.out.println(g + " " + d);
//			System.out.println(Arrays.toString(Move));
			
			// 기어 회전
			for (int i = 0; i < gears.length; i++) {		
				gears[i].rotate(Move[i]);
			}
			
//			for (Gear ge : gears) {
//				System.out.println(ge.toString());
//			}
		}
		
		// 결과값을 위해서 North의 값이 1인지 확인
		int sum = 0;
		for (int i = 0; i < gears.length; i++) {
			if (gears[i].NorthValue() == '1') {
				sum += Math.pow(2, i);
			}
		}
		System.out.println(sum);
	}
	
//	public static String str = "11111111\r\n" + 
//			"11111111\r\n" + 
//			"11111111\r\n" + 
//			"11111111\r\n" + 
//			"3\r\n" + 
//			"1 1\r\n" + 
//			"2 1\r\n" + 
//			"3 1";
}

// 8개의 톱니를 가진 톱니바퀴 4개
// 톱니바퀴를 총 k번 회전
// 회전은 시계, 반시계
// 맡닿는 극에 따라 옆에 있는 톱니가 회전
// 극이 다르면 회전방향과 반대로 회전
// 초기상태와 톱니바퀴를 회전시키는 방법을 제공
// 최종적인 톱니바퀴의 상태를 출력

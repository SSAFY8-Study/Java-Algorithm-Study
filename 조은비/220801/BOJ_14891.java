import java.util.Scanner;

public class Gear {

	static int [][] gear = new int[4][8]; // 톱니바퀴 상태를 저장할 2차원 배열
	static int rotate_cnt; // 회전횟수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 배열 입력받기
		for(int r=0; r<4; r++) {
			for(int c=0; c<8; c++) {
				gear[r][c] = sc.nextInt();
			}
		}
		
		// 회전횟수 입력받기
		rotate_cnt = sc.nextInt();
		
		// 회전횟수만큼 반복
		for(int i=0; i<rotate_cnt; i++) {
			int gear_num = sc.nextInt(); // 회전시킬 바퀴
			int dir = sc.nextInt(); // 회전 방향
			
			// 톱니바퀴 선택 -> 양쪽으로 탐색
			
			// 왼쪽 탐색 (왼쪽 톱니바퀴가 있다면, 선택한 톱니바퀴 6번과 왼쪽 바퀴 2번 인덱스와 비교 -> 같으면 왼쪽 탐색 멈추기, 다르면 계속하기)
			
			
			// 오른쪽 탐색 (오른쪽 톱니바퀴가 있다면, 선택한 톱니바퀴 2번과 오른쪽 바퀴 6번 인덱스와 비교 -> 같으면 오른쪽 탐색 멈추기, 다르면 계속하기) 
			
			
			// 탐색 끝나면 톱니바퀴 번호별로 이동해야할 방향으로 바꾸기??
		}

	}

}
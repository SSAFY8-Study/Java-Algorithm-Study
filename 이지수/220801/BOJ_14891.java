package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14891 {

	static int[][] wheel;
	static int number;
	static int direction;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheel = new int[4][8];
		//12시방향부터 시계방향으로 주어진다.
		for(int i=0;i<4;i++) { //이차원배열에 톱니바퀴 저장
			String[] str = br.readLine().split("");
			for(int j=0;j<8;j++) {
				wheel[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		int k = Integer.parseInt(br.readLine()); //회전시킨 횟수
		
		for(int tc=0; tc<k; tc++) {
			String[] str = br.readLine().split(" ");
			number = Integer.parseInt(str[0]); //회전시킬 톱니바퀴 번호
			direction = Integer.parseInt(str[1]); //방향
			
			//1이면 시계방향(->) -1이면 반시계방향(<-)
			//두 톱니바퀴가 맞닿아 있는 곳 : 2번째, 6번째
			
			//1. number 톱니바퀴 회전 (배열에선 number-1행) : 한칸씩 direction 방향으로 이동한다.
			swap(number-1, direction);
			
			
			//2.number와 인접한 톱니바퀴부터 회전 ("""근데 모두 동시에 회전이 이루어져야 함...""")
			boolean left = true, right=true;
			int leftDir = direction, rightDir=direction;
			
			for(int i=0; i<=2; i++) {
				int x = number-i-2; //왼쪽 톱니바퀴
				int y = number+i; //오른쪽 톱니바퀴
				
				//왼쪽 톱니바퀴 회전확인
				if(x>=0 && left) {
					if(wheel[x][2]!=wheel[x+1][6+leftDir]) { //극이 다르다면 회전한 방향과 반대로 회전
						swap(x,leftDir*-1);
						leftDir*=-1;
					}
					else {
						left = false;
					}
				}
				
				//오른쪽 톱니바퀴 회전 확인
				if(y<4 && right) { //배열 경계 확인
					if(wheel[y][6]!=wheel[y-1][2+rightDir]) { //극이 다르다면 회전한 방향과 반대로 회전
						swap(y,rightDir*-1);
						rightDir*=-1;
					}
					else { //극이 같다면 회전할 필요가 없고 더이상 이 방면(오른쪽)으로 회전할 필요가 없다.
						right = false;
					}
				}
				
			}
//			for(int s=0;s<4;s++) {
//				for(int j=0;j<8;j++) {
//					System.out.print(wheel[s][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println("------------------");
		}
		
		/*
		 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
		 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
		 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
		 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점*/
		//n극은 이미 값이 0이다.
		
		//3. 12시방향(0열)값 확인
		System.out.println(wheel[0][0]*1+wheel[1][0]*2+wheel[2][0]*4+wheel[3][0]*8);
		
	}
	
	static void swap(int x, int dir) {
		int tmp;
		if(dir==1) {
			tmp=wheel[x][7];
			
			for(int j=7; j>=0; j--) { //뒤에서부터 변경
				if(j+(dir*-1)>=0 && j+(dir*-1)<8) {
					wheel[x][j]=wheel[x][j+(dir*-1)];
				}
				else {
					wheel[x][j]=tmp;
				}
			}
			
//			for(int j=0; j<8; j++) {
//				System.out.print(wheel[x][j]+" ");
//			}
//			System.out.println();
		}
		else {
			tmp=wheel[x][0];
			
			for(int j=0; j<8; j++) { //앞에서부터 변경
				if(j+(dir*-1)>=0 && j+(dir*-1)<8) {
					wheel[x][j]=wheel[x][j+(dir*-1)];
				}
				else {
					wheel[x][j]=tmp;
				}
			}
		}
	}

}

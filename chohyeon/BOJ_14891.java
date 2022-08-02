import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	/*
	 * @url 	https://www.acmicpc.net/problem/14891
	 * @memo 	탐색하면서 회전하면 안됨. 탐색 다하고, 이후에 회전해야 함. 안그러면 회전된 상태에서 옆의 바퀴와 비교함.
	 * */
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static int[] rotate(int[] wheel, int dir) {
		
		if(dir == 1) {	// 시계방향
			return new int[] {wheel[7], wheel[0], wheel[1], wheel[2], wheel[3], wheel[4], wheel[5], wheel[6]};
		} else {		// 반시계방향
			return new int[] { wheel[1], wheel[2], wheel[3], wheel[4], wheel[5], wheel[6], wheel[7], wheel[0]};
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		// wheels : 4개의 톱니바퀴 상태
		int[][] wheels = new int[4][8];
		String line = "";
		for(int i=0; i<4; i++) {
			line = input.readLine();
			for(int j=0; j<8; j++) {
				wheels[i][j] = line.charAt(j)-'0';
			}
		}
		
			
		int k = Integer.parseInt(input.readLine());
		for(int i=0; i<k; i++) {
			String[] info = input.readLine().split(" ");
			int wNum = Integer.parseInt(info[0])-1;
			int dir = Integer.parseInt(info[1]);
			
			// 각 톱니바퀴가 이번 차례에 움직일 방향 
			int[] moveDir = new int[8];
			moveDir[wNum] = dir;
			
			
			// wNum번째 기준 오른쪽 톱니바퀴들 움직임 탐색
			int prev = wNum;
			for(int right = wNum+1; right<4; right++) {
				
				// 극이 같다면 움직이지 않음
				if(wheels[prev][2] == wheels[right][6])
					break;

				moveDir[right] = moveDir[prev]*-1;
				prev++;
				
			}
			
			// wNum번째 톱니바퀴의 왼쪽 톱니바퀴 중 움직여질 톱니바퀴 탐색
			prev = wNum;
			for(int left = wNum-1; left>=0; left--) {
				
				// 극이 같다면 움직이지 않음
                if(wheels[left][2] == wheels[prev][6])
					break;
				
				moveDir[left] = moveDir[prev]*-1;
				prev--;
			}
			
			// 톱니바퀴 회전	
			for(int j=0; j<4; j++) {
				if(moveDir[j] == 0) continue;
				wheels[j] = rotate(wheels[j], moveDir[j]);
			}
		}
		
		int ans = 0;
		for(int i=0; i<4; i++) {
			ans += (Math.pow(2, i))*wheels[i][0];
		}
		System.out.println(ans);
	}
}
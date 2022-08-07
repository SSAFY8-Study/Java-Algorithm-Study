package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/14891
 * @performance 11644 KB	84 ms
 * @category #구현 #시뮬레이션
 * @memo 함수인자 전달할 때, 참조형으로 원래 값을 바꿀 수 있는지 판단해보기.
 * 
 */

public class BJ_G5_14891 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static void moveClockWise(char[][] gears, int gearNum) {
		char[] newGear = new char[8];
		for (int i = 0; i < 7; i++) {
			newGear[i + 1] = gears[gearNum][i];
		}
		newGear[0] = gears[gearNum][7];
		gears[gearNum] = newGear;
//		System.out.println("gear:" + Arrays.toString(gear));
	}

	static void moveCounterClockWise(char[][] gears,int gearNum) {
		char[] newGear = new char[8];
		for (int i = 1; i <= 7; i++) {
			newGear[i - 1] = gears[gearNum][i];
		}
		newGear[7] = gears[gearNum][0];
		gears[gearNum] = newGear;
//		System.out.println("gear:"+Arrays.toString(gear);
	}

	static void move(char[][] gears, int gearNum, int dir) {
		if (dir == 1) {
			moveClockWise(gears,gearNum);
		} else if (dir == -1) {
			moveCounterClockWise(gears,gearNum);
		}
	}

	public static void main(String[] args) throws IOException {

		char[][] gears = new char[5][];

		for (int i = 1; i <= 4; i++) {
			tokens = new StringTokenizer(input.readLine());
			gears[i] = tokens.nextToken().toCharArray();
		}

//		// input 출력해보기.
//		for(int i=0;i<5;i++) {
//			System.out.println(Arrays.toString(gears[i]));
//		}

		int k = Integer.parseInt(input.readLine());
		for (int tc = 0; tc < k; tc++) {
			tokens = new StringTokenizer(input.readLine());
			int gearNum = Integer.parseInt(tokens.nextToken());
			int dir = Integer.parseInt(tokens.nextToken()); // 1:시계, -1:반시계

			int[] haveToMove = new int[5];
			haveToMove[gearNum] = dir;
			int saveDir = dir;
			int saveGearNum = gearNum;

			// 오른쪽
			while (true) {
//				System.out.println("1");//d
				int gearRight = gearNum + 1;
				if (gearRight <= 4) {
					if (gears[gearNum][2] != gears[gearRight][6]) {
						dir = dir * -1;
						haveToMove[gearRight] = dir;
						gearNum = gearRight;
					} else {
						break;
					}
				} else {
					break;
				}
			}

			// 오염된 gearNum, dir 복구
			gearNum = saveGearNum;
			dir = saveDir;

			// 왼쪽
			while (true) {
//				System.out.println("21");//d
				int gearLeft = gearNum - 1;
//				System.out.println("gearLeft: "+gearLeft);//d
				if (gearLeft >= 1) {
					if (gears[gearNum][6] != gears[gearLeft][2]) {
						dir = dir * -1;
						haveToMove[gearLeft] = dir;
						gearNum = gearLeft;
//						System.out.println("in"+gearNum);//d
					} else {
						break;
					}
				} else {
					break;
				}
			}

			for (int i = 1; i <= 4; i++) {
				if (haveToMove[i] == 1) {
					move(gears, i, 1);
//					for (int t = 1; t <= 4; t++) {
//						System.out.println(Arrays.toString(gears[t]));
//					}
//					System.out.println();
				} else if (haveToMove[i] == -1) {
					move(gears, i, -1);
//					for (int t = 1; t <= 4; t++) {
//						System.out.println(Arrays.toString(gears[t]));
//					}
//					System.out.println();
				}
			}
//			for (int t = 1; t <= 4; t++) {
//				System.out.println(Arrays.toString(gears[t]));
//			}

		} // tc
		int answer = 0;
		for (int i = 1; i <= 4; i++) {
			if (gears[i][0] == '1') {
				answer += Math.pow(2, i - 1);
			}
		}
		output.append(answer);
		System.out.print(output);
	}

}

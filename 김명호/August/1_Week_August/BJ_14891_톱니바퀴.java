import java.lang.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class BJ_14891_톱니바퀴 {
	public static int[][] arr = new int[4][8];
	public static int cnt = 0;
	public static int answer = 0;
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			arr[i] = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int X = sc.nextInt() - 1;
			int Y = sc.nextInt();
			int[] isSpin = new int[4];
			isSpin[X] = Y;// 선택된건 돈다.
			// 돌지말지 체크 먼저 하자. 기준점부터 오른쪽 먼저 보고 왼쪽 봐야할듯.
			for (int j = X; j < 3; j++) {
				if (arr[j][2] != arr[j + 1][6]) {// 다음께 돌까?
					// 돈다면, 방향 맞춰서 넣어주자
					isSpin[j + 1] = isSpin[j] == 1 ? -1 : 1;
				} else {
					break;
				}
			}
			for (int j = X; j > 0; j--) {
				if (arr[j][6] != arr[j - 1][2]) {// 다음께 돌까?
					isSpin[j - 1] = isSpin[j] == 1 ? -1 : 1;
				} else {
					break;
				}
			}
			for (int j = 0; j < 4; j++) {//돌리는 작업
				if(isSpin[j] == 1) {
					int temp = arr[j][7];//시계방향이면 11시꺼를 저장해놓는다.
					int []tempArr = new int[8];
					 System.arraycopy(arr[j], 0, tempArr, 1, 7);
					 tempArr[0] = temp;
					 System.arraycopy(tempArr,0,arr[j],0,8);
				}
				else if(isSpin[j]==-1) {//반시계방향이면 
					int temp = arr[j][0];
					int []tempArr = new int[8];
					 System.arraycopy(arr[j], 1, tempArr, 0, 7);
					 tempArr[7] = temp;
					 System.arraycopy(tempArr,0,arr[j],0,8);
				}
			}

		}
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if(arr[i][0]==1) {
				sum += Math.pow(2, i);
			}
		}
		System.out.println(sum);
	}
}
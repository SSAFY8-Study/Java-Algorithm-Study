import java.util.Scanner;

public class n14888_연산자_끼워넣기 {
	
	static int[] arr;
	// 순서는 + - x /
//	static char[] calcul = {'+', '-', 'x', '/'};
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		arr = new int[n];	
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] cal = new int[4];
		for(int i = 0; i < 4; i++) {
			cal[i] = sc.nextInt();
		}
		
		getMaxAndMin(1, arr[0], cal);
		System.out.println(max);
		System.out.println(min);
	}
	
	
	// 백트랙킹을 이용하여 모든 경우의 수를 살펴보고 그 중 최댓값, 최솟값을 저장
	public static void getMaxAndMin(int n, int res, int[] cal) {
		if(n >= arr.length) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(cal[i] != 0) {
				cal[i]--;
				if(i == 0) {
					getMaxAndMin(n+1, res+arr[n], cal);
				}else if(i == 1) {
					getMaxAndMin(n+1, res-arr[n], cal);
				}else if(i == 2) {
					getMaxAndMin(n+1, res*arr[n], cal);
				}else {
					getMaxAndMin(n+1, res/arr[n], cal);
				}
				cal[i]++;
			}
		}
	}
}

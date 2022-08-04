import java.util.Scanner;

public class n14719_빗물 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int arr[] = new int[m];
		
		int[][] map = new int[n][m];
		
		for(int i = 0; i < m; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i = 0; i < m; i++) {
			for(int j = n-1; j > n-arr[i]-1; j--) {
				map[j][i] = 1;
			}
		}
		
		int sum = 0;
		int sumA = 0;
		boolean checkL = false;
		for(int i = 0; i < n; i++) {
			checkL = false;
			sumA = 0;
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					if(!checkL) checkL = true;
					else {
						sum += sumA;
						sumA = 0;
					}
				}else{
					if(checkL) {
						sumA++;
						map[i][j] = 2;
					}
				}
			}
		}
		
		System.out.println(sum);
		
	}
}

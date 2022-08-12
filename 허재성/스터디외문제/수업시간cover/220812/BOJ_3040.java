package boj_b2;

import java.util.Scanner;

public class BOJ_3040 {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[9];
		int sum = 0;
		
		for(int i = 0; i < 9; i++) {
			nums[i] = sc.nextInt();
			sum += nums[i];
		}
		
		int not1 = -1, not2 = -1;
		boolean find = false;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(i == j) continue;
				
				if(sum - nums[i] - nums[j] == 100) {
					not1 = i;
					not2 = j;
					find = true;
					break;
				}
			}
			if(find)
				break;
		}
		
		for(int i = 0; i < 9; i++) {
			if(i != not1 && i != not2)
				System.out.println(nums[i]);
		}
	}

}

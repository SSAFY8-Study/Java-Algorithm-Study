package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_14719_빗물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] arr = new int[W];
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] > max) {
				max = arr[i];
			}
			if(arr[i] < min) {
				min = arr[i];
			}
		}
		
		int amount = 0;
		for(int h = min; h <= max; h++) {
			int start = -1;
			for(int i = 0; i < W; i++) {
				if(arr[i] > h && start == -1) {
					start = i;
				}
				else if(arr[i] > h) {
					amount += i - start - 1;
					start = i;
				}
			}
		}
		
		System.out.println(amount);
	}
}

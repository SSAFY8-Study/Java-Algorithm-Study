package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_연습문제_15173_Shuffle_O_Matic {
	static int[] cards, result;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			
			cards = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}		
			
			if(check(cards)) {
				sb.append(String.format("#%d %d\n", t, 0));
				continue;
			}
			
			result = new int[5];
			
			recur(0);
			
			sb.append(String.format("#%d %d\n", t, ans == Integer.MAX_VALUE ? -1 : ans));
		}
		
		System.out.println(sb);
	}
	
	static void recur(int nth) {
		if(nth == 5) {
			int[] temp = cards.clone();
			
			for(int i = 1; i <= 5; i++) {
				if(i > ans) return;
				
				temp = shuffle(temp, result[i-1]);
				if(check(temp)) {
					ans = Math.min(ans, i);
					return;
				}
			}
			
			return;
		}
		
		for(int i = 0; i < N; i++) {
			result[nth] = i;
			recur(nth+1);
		}
	}
	
	static int[] shuffle(int[] arr, int x) {
		int[] res = new int[arr.length];
		int idx = 0, l = 0, r = arr.length / 2;
		
		if(x < r) {
			for(int i = 0; i < arr.length / 2 - 1 - x; i++) {
				res[idx++] = arr[l++];
			}
			
			while(l < arr.length / 2 && r < arr.length) {
				res[idx++] = arr[l++];
				res[idx++] = arr[r++];
			}
			
			while(r < arr.length) {
				res[idx++] = arr[r++];
			}
		}
		else {
			for(int i = 0; i < x - arr.length / 2; i++) {
				res[idx++] = arr[r++];
			}
			
			while(l < arr.length / 2 && r < arr.length) {
				res[idx++] = arr[r++];
				res[idx++] = arr[l++];
			}
			
			while(l < arr.length / 2) {
				res[idx++] = arr[l++];
			}
		}
		
		return res;
	}
	
	static boolean check(int[] arr) {
		boolean flagAsc = true, flagDesc = true;
		
		for(int i = 0; i < arr.length-1; i++) {
			if(arr[i] > arr[i+1]) flagAsc = false;
			if(arr[i] < arr[i+1]) flagDesc = false;
		}
		
		return flagAsc || flagDesc;
	}
}

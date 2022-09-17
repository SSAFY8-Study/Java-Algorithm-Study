package sw_day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_01254 {
	
	/*
	 * @url 	https://www.acmicpc.net/problem/1254
	 * @memo 	
	 * */
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		char[] word = input.readLine().toCharArray();
		
		int size = word.length;
		
		int minLength = Integer.MAX_VALUE;		// 가장 짧은 펠린드롬 길이
		
		for(int i=size/2; i<size; i++) {
			// 전체 글자숫가 짝수인 펠린드롬이 가능한지 검사
			boolean possible = true;
			for(int d=1; i+d-1<size; d++) {
				if(i<d || word[i-d] != word[i+d-1]) {
					possible = false;
					break;
				}
			}
			if(possible) {
				minLength = i*2;
				break;
			}
			
			possible = true;
			// 전체 글자수가 홀수인 펠린드롬이 가능한지 검사
			for(int d=1; i+d<size; d++) {
				if(i<d || word[i-d] != word[i+d]) {	// 불가능
					possible = false;
					break;
				}
			}
			if(possible) {
				minLength = i*2+1;
				break;
			}
			
		}
		
		System.out.println(minLength);
	}

}

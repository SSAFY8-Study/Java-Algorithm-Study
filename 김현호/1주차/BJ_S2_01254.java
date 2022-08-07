package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/1254
 * @performance 14216 KB 124 ms
 * @category #문자열 #브루트포스
 * @memo for문에서 i++, i-- 잘 구분하기. palindrome 항상 끝까지 봐야됨.
 * 
 */

public class BJ_S2_01254 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String s = input.readLine();
		int answer = 2 * s.length() - 1;
		int length = Integer.MAX_VALUE;
		// 길이가 홀수일 때,
		if (s.length() % 2 == 1) {
			// aba palindrome
			for (int i = s.length() - 1; i >= s.length() / 2; i--) {
				for (int j = 1; i + j < s.length(); j++) {
					if (s.charAt(i + j) != s.charAt(i - j)) {
						break;
					}
					if (i + j == s.length() - 1) {
						length = s.length() + (s.length() - 1 - 2 * j);
					}
				}
				if (length < answer) {
					answer = length;
				}
			}
			// aa palindrome
			for (int i = s.length() - 1; i >= (s.length() / 2) + 1; i--) {
				for (int j = 0; i + j < s.length(); j++) {
					if (s.charAt(i + j) != s.charAt(i - (j + 1))) {
						break;
					}
					if (i + j == s.length() - 1) {
						length = s.length() + (s.length() - 2 * (j + 1));
					}
				}
				if (length < answer) {
					answer = length;
				}
			}
		} else { // 길이가 짝수일 때,
			// aba palindrome
			for (int i = s.length() - 1; i >= s.length() / 2; i--) {
				for (int j = 1; i + j < s.length(); j++) {
					if (s.charAt(i + j) != s.charAt(i - j)) {
						break;
					}
					if (i + j == s.length() - 1) {
						length = s.length() + (s.length() - 1 - 2 * j);
					}
				}
				if (length < answer) {
					answer = length;
				}
			}
			// aa palindrome
			for (int i = s.length() - 1; i >= s.length() / 2; i--) {
				for (int j = 0; i + j < s.length(); j++) {
					if (s.charAt(i + j) != s.charAt(i - (j + 1))) {
						break;
					}
					if (i + j == s.length() - 1) {
						length = s.length() + (s.length() - 2 * (j + 1));
					}
				}
				if (length < answer) {
					answer = length;
				}
			}
		}

		System.out.println(answer);

	}

}

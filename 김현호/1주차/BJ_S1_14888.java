package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/14888
 * @performance
 * @category #브루트포스 알고리즘 #백트래킹
 * @memo 자바로 순열 구현하기. nPr 뽑을 배열과 output배열,방문했는지 체크하는 배열이 필요하고, 뽑을배열의 길이만큼 순회하면서 방문이
 *       안 되어있으면 방문처리후 output의 depth 인덱스를 업데이트해주고 재귀로 depth+1을 호출. 끝나면 다시 방문처리를
 *       해제해준다. 종료조건은 depth가 r이 됐을때.
 * 
 */

public class BJ_S1_14888 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[] operators;
	static int n;
	static int[] nums;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;
	static char[] pOutput;
	static char[] charOp;

	static void permutate(int depth) {

		if (depth == n - 1) {
			int result = calculate();
			if (result > max) {
				max = result;
			}
			if (result < min) {
				min = result;
			}
		}

		for (int i = 0; i < n - 1; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				pOutput[depth] = charOp[i];
				permutate(depth + 1);
				visited[i] = false;
			}
		}
	}

	static int calculate() {
		int result = nums[0];
		for (int i = 0; i < n - 1; i++) {
			if (pOutput[i] == '+') {
				result = result + nums[i + 1];
			} else if (pOutput[i] == '-') {
				result = result - nums[i + 1];
			} else if (pOutput[i] == '*') {
				result = result * nums[i + 1];
			} else if (pOutput[i] == '/') {
				result = result / nums[i + 1];
			}
		}
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(input.readLine());
		nums = new int[n];
		tokens = new StringTokenizer(input.readLine());
		operators = new int[4];
		charOp = new char[n - 1];
		pOutput = new char[n - 1];
		visited = new boolean[n - 1];

		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(tokens.nextToken());
		}

		int idx = 0;
		for (int i = 0; i < 4; i++) {
			while (operators[i] != 0) {
				if (i == 0) {
					charOp[idx] = '+';
					idx++;
				} else if (i == 1) {
					charOp[idx] = '-';
					idx++;
				} else if (i == 2) {
					charOp[idx] = '*';
					idx++;
				} else if (i == 3) {
					charOp[idx] = '/';
					idx++;
				}
				operators[i]--;
			}
		}

//		System.out.println(Arrays.toString(charOp));// d

		//
		permutate(0);

		output.append(max).append("\n").append(min).append("\n");
		System.out.print(output);
	}

}

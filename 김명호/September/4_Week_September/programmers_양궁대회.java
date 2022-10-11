import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
	static int arr[] = new int[11];
	static int answer[] = new int[11];
	static boolean visited[] = new boolean[11];
	static int max, ap;

	public static int[] solution(int n, int[] info) {
		max = 0;
		ap = max;
		answer = info;
		comb(info, 10, n, 0);
		if (ap == max)
			answer = new int[] { -1 };

		return answer;
	}

	private static void comb(int[] info, int start, int n, int cnt) {
		if (cnt == 11) {
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < visited.length; i++) {
				if (visited[i])
					sum1 += 10 - i;
				else if (!visited[i] && info[i] != 0)
					sum2 += 10 - i;
			}
			if (sum1 > sum2 && (sum1 - sum2) > max) {
				max = sum1 - sum2;
				answer = Arrays.copyOf(arr, 11);
			} else if (sum1 > sum2 && (sum1 - sum2) == max) {
				for (int i = 0; i < arr.length; i++) {
					if (answer[10 - i] < arr[10 - i]) {
						answer = Arrays.copyOf(arr, 11);
						break;
					} else if (answer[10 - i] > arr[10 - i]) {
						break;
					}
				}
			}

			return;
		}
		for (int i = start; i >= 0; i--) {
			if (i == 0) {
				visited[10 - i] = true;
				arr[10 - i] = n;
				comb(info, start - 1, n - info[10 - i] - 1, 11);
				visited[10 - i] = false;
				arr[10 - i] = 0;
			}
			if (!visited[10 - i] && n > info[10 - i]) {
				visited[10 - i] = true;
				arr[10 - i] = info[10 - i] + 1;
				comb(info, start - 1, n - info[10 - i] - 1, cnt + 1);
				visited[10 - i] = false;
				arr[10 - i] = 0;
			}
		}
	}
}
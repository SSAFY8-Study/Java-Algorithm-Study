package bj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S1_02156 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _wines;
	static int[][] _memo;

	static int dp(int n, int cnt) { // n: 현재 포도주가 몇번째 잔인지, cnt: 연속으로 몇잔 마신 상태인지.
		if (n > _n) {
			return 0;
		}

		if (_memo[n][cnt] != -1) {
			return _memo[n][cnt];
		}

		if (cnt == 2) { // 현재 잔 마실 수 없음.
			return _memo[n][cnt] = dp(n + 1, 0); // 현재 잔 건너뜀.
		} else { // cnt가 0또는 1일 경우,
			return _memo[n][cnt] = Math.max(dp(n + 1, cnt + 1) + _wines[n], dp(n + 1, 0)); // 마신 경우와 안 마신 경우 중 큰 값.
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_wines = new int[_n + 1]; // 인덱스 1번부터.
		for (int i = 1; i <= _n; i++) {
			_wines[i] = Integer.parseInt(input.readLine());
		} // 입력 끝.

		_memo = new int[_n + 1][3];
		for (int i = 0; i < _n + 1; i++) {
			Arrays.fill(_memo[i], -1);
		}

		int answer = dp(1, 0);

//		for (int[] row : _memo) {
//			System.out.println(Arrays.toString(row));
//		}

		System.out.println(answer);

	}

}

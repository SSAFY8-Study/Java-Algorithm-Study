package boj.s1;

public class S1_01149_RGB거리 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[][] dp = new int[N][3];
		
		dp[0][0] = in.nextInt();
		dp[0][1] = in.nextInt();
		dp[0][2] = in.nextInt();
		
		for(int i = 1; i < N; i++) {
			dp[i][0] = in.nextInt() + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = in.nextInt() + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = in.nextInt() + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}

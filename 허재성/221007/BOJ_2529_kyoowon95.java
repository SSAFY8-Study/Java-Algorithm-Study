import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 12032KB	96ms

public class Main {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static boolean[] op;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(reader.readLine());
		StringTokenizer tokens = new StringTokenizer(reader.readLine(), " ");
		
		op = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			char opr = tokens.nextToken().charAt(0);
			if (opr == '<') {
				op[i] = true;
			}
		}
		
		for (int i = 0; i <= 9; i++)
			permutation(1, i, 1 << i);
		if (String.valueOf(maxResult).length() <= N){
			output.append('0').append(maxResult).append("\n");
		}else {
			output.append(maxResult).append("\n");
		}
		if (String.valueOf(minResult).length() <= N){
			output.append('0').append(minResult);
		}else {
			output.append(minResult).append("\n");
		}
		System.out.print(output);
	}
	static long maxResult = Long.MIN_VALUE;
	static long minResult = Long.MAX_VALUE;
	
	private static void permutation(int cnt, long sum, int bitmask) {
		if(cnt == N + 1) {
			maxResult = Math.max(maxResult, sum);
			minResult = Math.min(minResult, sum);
			return;
		}
		for(int i = 0; i <= 9; i++) {
			if((bitmask & 1 << i) == 0) {
				if (op[cnt - 1] && (sum % 10) < i) { // < 인 경우
					permutation(cnt + 1, sum * 10 + i, bitmask | 1 << i);
				}else if (!op[cnt - 1] && (sum % 10) > i){ //  > 인 경우
					permutation(cnt + 1, sum * 10 + i, bitmask | 1 << i);					
				}
			}
		}
	}
}
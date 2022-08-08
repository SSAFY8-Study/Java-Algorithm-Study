import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 
 * @author SSAFY
 * @see https://www.acmicpc.net/submit/1254
 * @performance 11504	76
 * @category #단순구현 #문자열
 * @memo 
 * 
 *
 */

public class BOJ_S2_1254_팰린드롬만들기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static char[] ch;

	public static void main(String[] args) throws IOException {
		ch = input.readLine().toCharArray();
		int cnt = 1;
		if (isPalin(0))
			System.out.println(ch.length);
		else {
			for (int i = 1; i < ch.length; i++) {
				if (isPalin(i)) {
					break;
				} else
					cnt++;
			}
			System.out.println(ch.length + cnt);
		}
	}

	public static boolean isPalin(int num) {
		boolean check = true;
		for (int i = num, j = ch.length - 1; i < (ch.length + i) / 2; i++) {
			if (ch[i] != ch[j]) {
				check = false;
				return check;
			} else
				j--;
		}
		return check;
	}
}
/**
 * @author seungheecho
 * @number bj6550
 * @see https://www.acmicpc.net/problem/6550
 * @performance 15148	132
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6550_부분문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		StringTokenizer st;

		while((str = br.readLine())!= null) {
			st = new StringTokenizer(str);
			
			String s = st.nextToken();
			String t = st.nextToken();
			
			int sIdx = 0;
			int tIdx = 0;
			
			while(tIdx < t.length() && sIdx < s.length()) {
				if (s.charAt(sIdx) == t.charAt(tIdx)) {
					sIdx++;
				}
				tIdx++;
			}
			if(sIdx == s.length()) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
			
		}
	}

}

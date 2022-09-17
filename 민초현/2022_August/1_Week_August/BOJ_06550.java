package sw_day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_06550 {

	static boolean isContain(char[] t, char[] s) {
		
		if(t.length >= s.length) {
			
			int sIdx = 0;
			
			for(int i=0; i<t.length && sIdx<s.length; i++) {
				if(t[i] == s[sIdx]) {
					sIdx++;
				}
			}
			return sIdx == s.length;
		}
		return false;
	}
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static String str;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens;
		while((str = input.readLine()) != null) {
			try {
				tokens = new StringTokenizer(str);
				char[] s = tokens.nextToken().toCharArray();
				char[] t = tokens.nextToken().toCharArray();
				
				String ans = isContain(t,s)? "Yes":"No";
				
				System.out.println(ans);
			} catch(Exception e) {
				break;
			}
		}
	}

}

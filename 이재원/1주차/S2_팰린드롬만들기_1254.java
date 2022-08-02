import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_팰린드롬만들기_1254 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int idx = str.length() / 2;
		int ans = 0;
		
		for(int i = idx; i < str.length(); i++) {
			boolean p = true, p2 = true;
			int j = 1;
			while(true) {
				if(i+j >= str.length()) {
					break;
				}
				
				if(i-j < 0 || str.charAt(i-j) != str.charAt(i+j)) {
					p = false;
					break;
				}
				
				j++;
			}
			
			j = 1;
			if(str.length() > 1 && str.charAt(i-1) == str.charAt(i)) {
				while(true) {
					if(i+j >= str.length()) {
						break;
					}
					
					if(i-j-1 < 0 || str.charAt(i-1-j) != str.charAt(i+j)) {
						p2 = false;
						break;
					}
					
					j++;
				}
			}
			else {
				p2 = false;
			}
			
			if(p2) {
				ans = 2 * i;
				break;
			}
			
			if(p) {
				ans = 2 * i + 1;
				break;
			}
		}
		
		System.out.println(ans);
	}
}

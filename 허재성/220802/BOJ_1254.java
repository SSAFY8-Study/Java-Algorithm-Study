import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/1254
 * @difficulty Silver2
 * @performance 11496KB   80ms
 * @category # 문자열, 팰린드롬 판별, 부분문자열
 * @memo 원래 문자열의 접미사 문자열이 팰린드롬이 되는지 찾아야했었음.
 * @memo 접미사 문자열 중 팰린드롬이 되는 최대 문자열을 찾고 접두사 문자열을 붙여주면 되는데
 * @memo idea를 떠올리는 것이 쉽지 않았음. idea만 떠오르면 구현 자체는 어렵지 않음
 *  
 */


public class BOJ_1254 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static String input;
	static int ans = 0;
	
	static boolean isPalindrome(String str) {
		int len = str.length();
		int mid = len / 2;
		int left, right;
		
		if(len % 2 == 0) {
			left = mid - 1;
			right = mid;
		}
		else {
			left = mid - 1;
			right = mid + 1;
		}
		
		while(left >= 0 && right < len) {
			if(str.charAt(left) != str.charAt(right))
				return false;
			
			left--;
			right++;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input = bf.readLine();
//		System.out.println(isPalindrome(input) ? "YES!!" : "NO!!");
	
		for(int i = 0; i < input.length(); i++) {
			String partial = input.substring(i);
			
			if(isPalindrome(partial)) {
				ans = input.length() + i;
				break;
			}
		}
		
		System.out.println(ans);
	
	}

}

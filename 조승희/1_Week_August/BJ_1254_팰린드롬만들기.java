/**
 * @author seungheecho
 * @number bj1254
 * @see https://www.acmicpc.net/problem/1254
 * @performance 12892	120
 * 
 */
import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int result = 0;
		boolean check = false;
		for(int i=0; i<s.length(); i++) {
			String sub = s.substring(i);
			for(int j=0; j<sub.length()/2; j++) {
				check = true;
				if(sub.charAt(j) != sub.charAt(sub.length()-1-j)) {
					check = false;
					break;
				}
			}
			if(check) {
				result=i+s.length();
				break;
			}
		}
		if(!check) {
			result = 2*s.length()-1;
		}
		System.out.println(result);
		
	}

}

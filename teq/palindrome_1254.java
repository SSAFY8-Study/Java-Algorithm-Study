import java.util.Arrays;
import java.util.Scanner;
/**
 * 
 * @author TEQ   
 * @see https://www.acmicpc.net/problem/1254
 * @performance 17576	200
 * @category 단순구현
 * @memo 메모리 
 *
 */
public class palindrome_1254 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);    
		char[] input = sc.next().toCharArray();
		int pivot = -1;
		for(int i=0; i<input.length; i++) {
			char[] tmp = Arrays.copyOfRange(input, i, input.length);
			if(is_palindrome(tmp)) {
				pivot = i;
				break;
			}
		}
		System.out.println(pivot+input.length);
	}
	static boolean is_palindrome(char[] input) {
		if(input.length==1) return true;
		for(int p=0; p<input.length/2; p++) {
			if(input[p]!=input[input.length-1-p]) return false;
		}
		return true;
	}
}

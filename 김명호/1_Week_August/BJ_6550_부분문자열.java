import java.util.*;
import java.lang.*;
import java.io.*;

public class BJ_6550_부분문자열 {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while(sc.hasNext()) {
			String S = sc.next();
			String T = sc.next();
			System.out.println(solution(S, T) == false ? "No" : "Yes");
		}
	}

	public static boolean solution(String S, String T) {
		//int lastChar = -1;
		for (int i = 0; i < S.length(); i++) {
			if (T.indexOf(S.charAt(i)) == -1) {
				return false;
			} else {
				T = T.substring(T.indexOf(S.charAt(i))+1);
			}
		}

		return true;
	}
}
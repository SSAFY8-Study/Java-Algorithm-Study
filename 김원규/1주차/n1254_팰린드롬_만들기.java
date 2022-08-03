import java.util.Scanner;

public class n1254_팰린드롬_만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String temp = str;
		
		if(check(str)) System.out.println(str.length());
		else {
			int idx = 0; 
			
			while(true) {
				for(int i = idx; i >= 0; i--) {
					str = str + str.charAt(i);
				}
				
				if(check(str)) break;
//				System.out.println(str);
				str= temp;
				idx++;
			}
			
			System.out.println(str.length());
		}
	}
	
	public static boolean check(String str) {
		String str2 = "";
		for(int i = str.length()-1; i >= 0; i--) {
			str2 = str2 + str.charAt(i);
		}
//		System.out.println(str2);
		if(str.equals(str2)) return true;
		return false;
	}
}

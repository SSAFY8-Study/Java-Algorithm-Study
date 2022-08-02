import java.util.Scanner;

public class BOJ_1254 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String msg = sc.nextLine();
		
		int answer = 0;
		
		// 입력받은 문자열이 이미 팰린드롬이라면
		if(msg.equals(new StringBuilder(msg).reverse().toString())) {
			answer = msg.length();
		}else { // 아니라면 팰린드롬 만들기
			for(int i=1; i<msg.length(); i++) {
				StringBuilder b = new StringBuilder(msg);
				// 한글자씩 늘려가면서 뒤에 붙이기
				b.append(new StringBuilder(msg.substring(0, i)).reverse());
				// 팰린드롬 확인
				if(b.toString().equals(b.reverse().toString())) {
					answer = b.length();
				}
			}
		}
		
		System.out.println(answer);
		
		

	}

}

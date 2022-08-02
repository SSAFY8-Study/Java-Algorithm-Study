package BOJ.구현;
/**
 * @author jisoo
 * @see https://www.acmicpc.net/problem/1254 팰린드롬 만들기
 * @performance 14340	128
 * @category #문자열 #브루트포스
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_01254 {

	public static void main(String[] ars) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine(); //입력받은 값
		sb.append(str);
		
		if(sb.toString().equals(new StringBuilder(sb.toString()).reverse().toString())) { //이미 처음에 입력받은 값이 팰린드롬일때
			System.out.println(str.length());
		}
		else {
			for(int i=1; i<str.length();i++) {
				sb.append(new StringBuilder(str.substring(0,i)).reverse().toString()); //입력받은 값을 뒤집어서 추가해보며 팰린드롬이 만들어지는지 확인해본다.
				//System.out.println(i+" : "+sb.toString());
				if(sb.toString().equals(new StringBuilder(sb.toString()).reverse().toString())) {
					System.out.println(sb.length());
					break;
				}
				sb.delete(0, sb.length());//전체 삭제 (새롭게 substring을 받기 위해)
				sb.append(str);//입력받은 값 뒤에다 추가해야 하기 때문에 먼저 초기값은 입력되어야 한다.
			}
		}
		//System.out.println(sb.toString());
		//System.out.println(sb.length());
	}

}

//abbca

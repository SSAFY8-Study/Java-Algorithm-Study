package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**

@author jisoo
@since 2022. 8. 7.
@see https://www.acmicpc.net/problem/9935 문자열 폭발
@performance 87276	528
@difficulty Gold4
@category #스택
@note pop할때 전체 stack.size()가 유동적으로 변한다는 걸 잊지말자
 */
public class BOJ_09935{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		String boom = br.readLine();//폭발 문자열
		
		/*
		 * 메모리 초과
		sb.append(str);
		for(int i=0; i<=sb.length()-boom.length(); i++) {
			if(sb.substring(i,i+boom.length()).equals(boom)) {
				sb = new StringBuilder(sb.substring(0,i)+sb.substring(i+boom.length(),sb.length()));
				i=0;
			}
		}
		if(sb.length()!=0 && !sb.toString().equals(boom)) System.out.println(sb.toString());
		else System.out.println("FRULA");
		*/
		
		/*
		 * 메모리 초과 2..
		int index = -1;
		
		sb.append(str);
		while(true) {
			if(str.contains(boom)) {
				index = str.indexOf(boom); //시작점
				sb = new StringBuilder(sb.substring(0,index)+sb.substring(index+boom.length()));
				str = sb.toString();
			}
			else {
				break;
			}
		}
		
		if(str.length()!=0) System.out.println(str);
		else System.out.println("FRULA");
		*/
		
		
		Stack<Character> stack = new Stack<>();
		Stack<Character> tmp = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			stack.add(str.charAt(i));
			
			if(stack.size()>=boom.length() && stack.peek() == boom.charAt(boom.length()-1)) {//마지막 글자가 동일하면
				for(int j=boom.length()-1; j>=0; j--) {
					if(boom.charAt(j) == stack.peek() ) {
						tmp.add(stack.pop());
					}
					else {//하나라도 글자가 틀리면
						int sizeTmp=tmp.size(); //tmp.pop()하면 사이즈가 계속 변하니까 주의해야함 !!!!
						for(int k=0; k<sizeTmp; k++) {
							stack.add(tmp.pop()); //빼냈던거 다시 추가..
						}
						break;
					}
				}
				tmp.clear();
			}
		}
		int size = stack.size();
		if(stack.size()!=0) {
			for(int i=0; i<size; i++) {
				sb.append(stack.pop());
			}
			System.out.println(sb.reverse().toString());
		}
		else
			System.out.println("FRULA");
	}
}
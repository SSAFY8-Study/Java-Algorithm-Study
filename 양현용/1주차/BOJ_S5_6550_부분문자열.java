package on0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 9.
@see https://www.acmicpc.net/problem/6550
@performance 15412	112
@category #문자열	#그리디
@note */
public class BOJ_S5_6550_부분문자열 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static char[] s,t;
	static boolean check;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		while(true) {
			String str=input.readLine();
			check=true;
			if(str==null) break;  //입력 없으면 종료
			tokens=new StringTokenizer(str);
			s=tokens.nextToken().toCharArray();
			t=tokens.nextToken().toCharArray();
			
			//t 전체 탐색을 피하기 위한 idx
			int left=0;
			for(int i=0;i<s.length;i++) {
				for(int j=left;j<t.length;j++) {
					if(s[i]==t[j]) {
						left=j+1;
						check=true;
						break;
					}
					check=false;
				}
				//t에 s의 문자가 없으면 바로 끝내기 
				if(!check)
					break;
			}
			
			if(check)
				output.append("Yes\n");
			else
				output.append("No\n");
		}
		System.out.println(output);
	}
}
package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String line = br.readLine();//들어오는 문자열
		
		if(line.equals(new StringBuilder(line).reverse().toString())) {
			System.out.println(line.length());
		}else {
			for (int i = 0; i < line.length(); i++) {
				StringBuilder sb = new StringBuilder(line);
				sb.append(new StringBuilder(line.substring(0,i)).reverse());
				if(sb.toString().equals(sb.reverse().toString())) {
					System.out.println(sb.length());
					break;
				}
			}
		}
	}
}

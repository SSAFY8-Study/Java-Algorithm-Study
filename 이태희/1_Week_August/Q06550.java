package com.boj.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* @author - Malachai
 *  @see - https://www.acmicpc.net/problem/6550
 *  @performance - 
 *  @category - string
 *  @memo
 */

public class Q06550 {

	//default
	public static int T;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		while(true) {
			String str = br.readLine();
			if (str == null) break;
			//if (str.equals("")) break;
			StringTokenizer st = new StringTokenizer(str, " ");	
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			int p1 = 0; int p2 = 0; boolean valid = false;
			while(p2 < str2.length()) {
				if(str1.charAt(p1) == str2.charAt(p2)) p1++;
				p2++;
				if(p1 >= str1.length()) {
					valid = true;
					break;
				}
			}
			if (valid) sb.append("Yes\n");
			else sb.append("No\n");
		}
		System.out.println(sb);
	}
	
}

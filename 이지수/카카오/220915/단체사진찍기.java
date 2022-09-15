package Programmers.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 14.
@see https://school.programmers.co.kr/learn/courses/30/lessons/1835
@performance
@difficulty 
@category #
@note */
public class 단체사진찍기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		String[] data = {"N~F=0", "R~T>2"};
		System.out.println(solution(2,data));
	}
	
	public static int solution(int n, String[] data) {
		permutation(new boolean[arr.length], new char[arr.length],0,data);
        return answer;
    }
	
	//A, C, F, J, M, N, R, T
	static void permutation(boolean[] visited, char[] choosed, int nth, String[] data) {
		if(nth == arr.length) {
			for(int i=0; i<data.length; i++) {
				int interval = 0;
				boolean flag = false;
				
				for(int j=0; j<choosed.length; j++) {
					if(choosed[j]==data[i].charAt(0) || choosed[j]==data[i].charAt(2)) {
						flag = flag ? false : true ;
						if(!flag) break;
					}
					else if(flag) {
						interval ++;
					}
				}
				switch(data[i].charAt(3)) {
				case '<':
					if(interval >= (data[i].charAt(4)-'0')) {
						return;
					}
					break;
				case '>':
					if(interval <= (data[i].charAt(4)-'0')) {
						return;
					}
					break;
				case '=':
					if(interval != (data[i].charAt(4)-'0')) {
						return;
					}
					break;
				}	
			}
			
			answer ++;
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = arr[i];
				permutation(visited, choosed, nth+1, data);
				visited[i] = false;
			}
		}
	}
}
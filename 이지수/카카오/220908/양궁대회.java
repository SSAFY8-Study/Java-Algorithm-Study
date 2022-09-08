package Programmers.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 5.
@see https://school.programmers.co.kr/learn/courses/30/lessons/92342 양궁대회
@performance
@difficulty level2
@category #
@note */
public class 양궁대회 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[] apeach, ryan, result;
	static int diff = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		apeach = new int[11];//10~0
		ryan = new int[11];
		result = new int[11];
		
		tokens = new StringTokenizer(br.readLine(),",");
		for(int i=0; i<11; i++) {
			apeach[i] = Integer.parseInt(tokens.nextToken());
		}
		
		combination(0,0, new int[N]);
		
		boolean flag = false;
		
		for(int i=0; i<11; i++) {
			if(result[i]!=0) {
				flag = true;
				break;
			}
		}
		
		if(flag)
			System.out.println(Arrays.toString(result));
		else
			System.out.println(-1);
	}
	static void combination(int nth, int start, int[] choosed) { //라이언이 획득할 수 있는 가능한 점수들을 중복조합으로 뽑는다.
		if(nth == N) {
			for(int i=0; i<N; i++) {
				ryan[choosed[i]]++;
			}
			
			int apeachA = 0;
			int ryanB = 0;
			for(int i=0; i<11; i++) {
				if(apeach[i]>=ryan[i] && apeach[i]!=0) {
					apeachA += (10-i);
				}
				else if(apeach[i] < ryan[i]) {
					ryanB += (10-i);
				}
			}
			
			if(ryanB>apeachA) {
				int max = diff;
				diff = Math.max(diff, ryanB-apeachA);
				
				if(diff == (ryanB-apeachA)) { 
					//라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 우선순위
					//System.out.println(ryanB+ " : "+apeachA);
					int minIndex=0;
					if(diff == max) {
						for(minIndex=10; minIndex>=0; minIndex--) {
							if(result[minIndex]<ryan[minIndex] && ryan[minIndex]!=0) {
								break;
							}
							else if(result[minIndex]>ryan[minIndex]) {
								minIndex = -2;
								break;
							}
						}
					}
					
					
					if(minIndex!=-2) {
						for(int i=0; i<11; i++) {
							result[i] = ryan[i];
						}
					}
				}
			}
			
			for(int i=0; i<11; i++) {
				ryan[i] = 0;
			}
			return;
		}
		
		for(int i=start; i<11 ; i++) {
			choosed[nth] = i;
			combination(nth+1, i, choosed);
		}
	}
}
//5
//2,1,1,1,0,0,0,0,0,0,0
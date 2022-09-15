package Programmers.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 15.
@see https://school.programmers.co.kr/learn/courses/30/lessons/64062
@performance
@difficulty 
@category #
@note 이분탐색
https://minhamina.tistory.com/129
*/
public class 징검다리건너기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		//System.out.println(solution(stones, 3));
		System.out.println(solution2(stones, 3));
	}
	
	public static int solution(int[] stones, int k) {
        int answer = 0;
        
        out : while(true) {
        	int min = Integer.MAX_VALUE;
            
        	int cnt=0;
            for(int i=0; i<stones.length; i++) {
            	if(stones[i] == 0) {
            		cnt++;
            		continue;
            	}
            	if(cnt>=k) break out;
            	cnt = 0;

            	min = Math.min(min, stones[i]);
            }
            
            for(int i=0; i<stones.length; i++) {
            	if(stones[i] !=0)
            		stones[i] -= min;
            }
            answer += min;
        }
        
        return answer;
    }
	
	//이분탐색
	public static int solution2(int[] stones, int k) {
		int answer = 0;
		int low = 1;
		int high = 200000000;
		int mid = (low + high)/2;
		
		while(low<=high) {
			mid = (low + high)/2;
			//mid를 key로 가져간다.
			if(!check(stones,k,mid)) { //이 mid값으로 건널 수 없다.
				high = mid-1; //그럼 mid 이상인 값으로는 다리 건널 수 없다는 뜻이 되버림
			}
			else {
				low = mid+1;
				answer = Math.max(answer, mid); //건널 수 있는 최대 인원 계산
			}
		}
		
		return answer;
	}
	
	static boolean check(int[] stones, int k, int key) {
		int cnt=0;
		for(int i=0; i<stones.length; i++) {
			if(stones[i]-key<0) {
				cnt++;
			}
			else {
				cnt=0;
			}
			if(cnt>=k) return false;//건널 수 없다.

		}
		return true;
	}
}
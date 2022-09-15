package Programmers.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 13.
@see https://school.programmers.co.kr/learn/courses/30/lessons/92341
@performance
@difficulty level2
@category #
@note */
public class 주차요금계산 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		//fee : 기본시간, 기본요금, 단위시간, 단위요금
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		System.out.println(Arrays.toString(solution(fees,records)));
	}
	
    public static int[] solution(int[] fees, String[] records) {

    	int[] answer = {};
    	
        HashMap<String, String> hash = new HashMap<>(); //차번호 : 출입시간
        HashMap<String, Integer> result = new HashMap<>();
        
        for(int i=0; i<records.length; i++) {
        	String[] arr = records[i].split(" ");
        	if(hash.containsKey(arr[1])) { //out이 들어온 경우
        		String[] start = hash.get(arr[1]).split(":"); //입차시간
        		String[] end = arr[0].split(":");//출차
        		int time = 0;
        		if((Integer.parseInt(end[1])-Integer.parseInt(start[1]))<0) {
        			time = (Integer.parseInt(end[0])-Integer.parseInt(start[0])-1) * 60
            				+
            				(Integer.parseInt(end[1]) + 60 - Integer.parseInt(start[1]));
        		}
        		else {
        			time = (Integer.parseInt(end[0])-Integer.parseInt(start[0])) * 60
            				+
            				(Integer.parseInt(end[1])-Integer.parseInt(start[1]));
        		}
        		
    			result.replace(arr[1], result.get(arr[1])+time);
        		hash.remove(arr[1]);
        	}
        	else {
        		hash.put(arr[1], arr[0]);
        		if(!result.containsKey(arr[1]))
        			result.put(arr[1], 0);
        	}
        }
        
        for(String key : hash.keySet()) {//마감시간이 지났는데도 아직 남아있다면
        	String[] start = hash.get(key).split(":"); //입차시간
        	
    		int time = (23-Integer.parseInt(start[0])) * 60
    				+
    				(59-Integer.parseInt(start[1]));
    		
    		result.replace(key, result.get(key)+time);
    		
        }
        answer = new int[result.size()];
        int index = 0;
        List<String> keys = new ArrayList<>(result.keySet());
        Collections.sort(keys);
        
        for(String key : keys) {
        	if(result.get(key)>fees[0]) { //기본시간 초과
    			int overtime = result.get(key) - fees[0]; //초과시간
    			result.replace(key, (int)Math.ceil((double)overtime/(double)fees[2])*fees[3] + fees[1]);
    		}
    		else {
    			result.replace(key, fees[1]); //기본요금 부과
    		}
        	answer[index] = result.get(key);
        	index++;
        }
        return answer;
    }
}
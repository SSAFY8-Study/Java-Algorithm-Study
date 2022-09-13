package study09_1;

import java.util.HashMap;
import java.util.Map;

public class Programmers_성격유형검사하기 {

    public String solution(String[] survey, int[] choices) {
        char[] type = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
		int[] score = new int['Z'-'A'+1];
        for(int i=0; i<survey.length; i++) {
        	int n = choices[i];
        	if(n==1) {
        		score[survey[i].charAt(0)-'A'] +=3;
        	} else if(n==2) {
        		score[survey[i].charAt(0)-'A'] +=2;
        	} else if(n==3) {
        		score[survey[i].charAt(0)-'A'] +=1;
        	} else if(n==4) {
        		continue;
        	} else if(n==5) {
        		score[survey[i].charAt(1)-'A'] +=1;
        	} else if(n==6) {
        		score[survey[i].charAt(1)-'A'] +=2;
        	} else {
        		score[survey[i].charAt(1)-'A'] +=3;
        	}
        }
        String ans = "";
        for(int i=0; i<type.length-1; i+=2) {
        	if(score[type[i]-'A']>=score[type[i+1]-'A']) {
        		ans+=type[i];
        	}else {
        		ans+=type[i+1];
        	}
        }
        return ans;
    }

}

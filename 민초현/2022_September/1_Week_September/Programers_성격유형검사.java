import java.util.Arrays;

class Solution {
    public void setScore(int[] score, char type, int plusScore){
        switch(type){
            case 'R':
                score[0] -= plusScore;
                break;
            case 'T':
                score[0] += plusScore;
                break;
            case 'C':
                score[1] -= plusScore;
                break;
            case 'F':
                score[1] += plusScore;
                break;
            case 'J':
                score[2] -= plusScore;
                break;
            case 'M':
                score[2] += plusScore;
                break;
            case 'A':
                score[3] -= plusScore;
                break;
            case 'N':
                score[3] += plusScore;
                break;
        }
    }
    
    public String solution(String[] survey, int[] choices) {
        
        // ex. score[0] : 1번 지표 점수(음수 => R, 양수 => T)
        int[] score = new int[4];
        
        for(int i=0, size = survey.length; i<size; i++){
            char[] curType = survey[i].toCharArray();  
            
            if(choices[i]<4) {               // 비동의일 경우 => curType[0] 점수 증가
                setScore(score, curType[0], 4-choices[i]);
            } else if(choices[i]>4){         // 동의일 경우 => curType[1] 점수 증가
                setScore(score, curType[1], choices[i]-4);
            }
        }
        
        
        StringBuilder result = new StringBuilder();
        // 1번 지표 결정
        if(score[0] <= 0)    result.append("R");
        else                result.append("T");
        
        // 2번 지표 결정
        if(score[1] <= 0)    result.append("C");
        else                result.append("F");
        
        // 3번 지표 결정
        if(score[2] <= 0)    result.append("J");
        else                result.append("M");
        
        // 4번 지표 결정
        if(score[3] <= 0)    result.append("A");
        else                result.append("N");
        
        return result.toString();
    }
}
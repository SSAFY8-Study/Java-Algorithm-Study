package com.programmers.level1;

public class Q1001 {

    public static String solution(String[] survey, int[] choices) {
        int RT=0;
        int CF=0;
        int JM=0;
        int AN=0;
        for(int i=0; i<survey.length; i++){
            switch(survey[i]){
                case "RT":
                    RT -= choices[i] - 4;
                    break;
                case "TR":
                    RT += choices[i] - 4;
                    break;
                case "CF":
                    CF -= choices[i] - 4;
                    break;
                case "FC":
                    CF += choices[i] - 4;
                    break;
                case "JM":
                    JM -= choices[i] - 4;
                    break;
                case "MJ":
                    JM += choices[i] - 4;
                    break;
                case "AN":
                    AN -= choices[i] - 4;
                    break;
                case "NA":
                    AN += choices[i] - 4;
                    break;
            }
        }
        String res = "";
        if(RT >= 0) res += "R";
        else res += "T";
        if(CF >= 0) res += "C";
        else res += "F";
        if(JM >= 0) res += "J";
        else res += "M";
        if(AN >= 0) res += "A";
        else res += "N";
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"TR", "RT", "TR"}, new int[] {7, 1, 3}));
    }
}

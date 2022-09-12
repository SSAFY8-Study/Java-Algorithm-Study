import java.util.*;
import java.io.*;
import java.lang.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        String[][] arr = {{"R", "0"}, {"T", "0"}, {"C", "0"}, {"F", "0"}, {"J", "0"}, {"M", "0"}, {"A", "0"}, {"N", "0"}};
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] < 4)
                for (int j = 0; j < 8; j++) {
                    if (arr[j][0].equals(String.valueOf(survey[i].charAt(0)))) {
                        arr[j][1] = String.valueOf(Integer.parseInt(arr[j][1]) + 4 - choices[i]);
                    }
                }
            else if (choices[i] > 4) {
                for (int j = 0; j < 8; j++) {
                    if (arr[j][0].equals(String.valueOf(survey[i].charAt(1)))) {
                        arr[j][1] = String.valueOf(Integer.parseInt(arr[j][1]) + choices[i] - 4);
                    }
                }
            }
        }
        for (int i = 0; i < arr.length; i += 2) {
            if (Integer.parseInt(arr[i + 1][1]) > Integer.parseInt(arr[i][1])) {
                answer += arr[i + 1][0];
            } else {
                answer += arr[i][0];
            }
        }
        return answer;
    }
}
import java.io.*;
import java.util.*;
class Solution {
    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        String[] temp;
        Map<String, Integer> map = new TreeMap<>();
        Map<String, String> listMap = new HashMap<>();

        for (String recode : records) {
            temp = recode.split(" ");
            if (temp[2].equals("IN")) {
                listMap.put(temp[1], temp[0]);
            } else {
                String[] after = temp[0].split(":");
                String[] before = listMap.get(temp[1]).split(":");
                if (map.containsKey(temp[1])) {//기록이 있는 경우
                    int time = (Integer.parseInt(after[0]) - Integer.parseInt(before[0])) * 60 + (Integer.parseInt(after[1]) - Integer.parseInt(before[1]));
                    map.put(temp[1], map.get(temp[1]) + time);
                } else {//없는경우
                    int time = (Integer.parseInt(after[0]) - Integer.parseInt(before[0])) * 60 + (Integer.parseInt(after[1]) - Integer.parseInt(before[1]));
                    map.put(temp[1], time);
                }
                listMap.remove(temp[1]);
            }
        }
        listMap.forEach((key, value) -> {
            String[] after = {"23", "59"};
            String[] before = value.split(":");
            if (map.containsKey(key)) {//기록이 있는 경우
                int time = (Integer.parseInt(after[0]) - Integer.parseInt(before[0])) * 60 + (Integer.parseInt(after[1]) - Integer.parseInt(before[1]));
                map.put(key, map.get(key) + time);
            } else {//없는경우
                int time = (Integer.parseInt(after[0]) - Integer.parseInt(before[0])) * 60 + (Integer.parseInt(after[1]) - Integer.parseInt(before[1]));
                map.put(key, time);
            }
        });
        answer = new int[map.size()];
        int idx=0;
        for (String key:map.keySet()) {
            answer[idx] = map.get(key)>fees[0]?(fees[1]+(int)Math.ceil((map.get(key)-fees[0])/(double)fees[2])*fees[3]):fees[1];
            idx++;
        }
        return answer;
    }
}
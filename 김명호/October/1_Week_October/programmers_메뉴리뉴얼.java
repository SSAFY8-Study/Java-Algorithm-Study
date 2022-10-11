import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] arr;
    static int[] check;
    static List<String> list;
    static List<Integer> listCnt;
    static Map<String, Integer> map = new HashMap<>();

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        list = new ArrayList<>();
        arr = new String[orders.length];
        for (int i = 0; i < orders.length; i++) {
            char[] temp = orders[i].toCharArray();
            Arrays.sort(temp);
            arr[i] = String.valueOf(temp);
        }
        //조합 구하기
        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                comb("", arr[j].split(""), 0, course[i]);
            }
            if (!map.isEmpty()) {

                listCnt = new ArrayList<>(map.values());
                int max = Collections.max(listCnt);
                if (max > 1) {
                    String[] keySet = map.keySet().toArray(new String[0]);
                    for (int k = 0; k < keySet.length; k++) {
                        if (map.get(keySet[k]) == max) {
                            list.add(keySet[k]);
                        }
                    }
                }
            }
            map.clear();

        }

        Collections.sort(list);
        answer = list.toArray(new String[0]);
        return answer;
    }

    private static void comb(String str, String temp[], int start, int cnt) {
        if (cnt == 0) {
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }
        for (int i = start; i < temp.length; i++) {
            comb(str + temp[i], temp, i + 1, cnt - 1);
        }
    }


}
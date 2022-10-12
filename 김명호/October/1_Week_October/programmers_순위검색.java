import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static HashMap<String, List<Integer>> map;

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<String, List<Integer>>();

        for (int i = 0; i < info.length; i++) {
            String[] userInfo = info[i].split(" ");
            cal(userInfo, "", 0);
        }
        for (String key : map.keySet())
            Collections.sort(map.get(key));
        String[] temp = new String[query.length];
        String[] queryArr;

        for (int i = 0; i < query.length; i++) {
            temp[i] = query[i].replaceAll(" and ", "");
            queryArr = temp[i].split(" ");
            answer[i] = map.containsKey(queryArr[0]) ? binarySearch(queryArr[0], Integer.parseInt(queryArr[1])) : 0;

        }

        return answer;
    }

    private static int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);
        int start = 0, end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return list.size() - start;
    }

    private static void cal(String[] userInfo, String str, int cnt) {
        if (cnt == 4) {
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<Integer>());
            }
            map.get(str).add(Integer.parseInt(userInfo[4]));
            return;
        }
        cal(userInfo, str + "-", cnt + 1);
        cal(userInfo, str + userInfo[cnt], cnt + 1);
    }

}
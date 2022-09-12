import java.util.*;

public class programmers_성격유형검사 {
	static char[][] types = { { 'R', 'T' }, { 'C', 'F' }, { 'J', 'M' }, { 'A', 'N' } };

	public String solution(String[] survey, int[] choices) {
		String answer = "";
		HashMap<Character, Integer> map = new HashMap<>();
		for (int t = 0; t < types.length; t++) {
			map.put(types[t][0], 0);
			map.put(types[t][1], 0);
		}

		for (int i = 0, size = survey.length; i < size; i++) {
			String type = survey[i];
			int point = choices[i];
			if (point < 4) {
				map.put(type.charAt(0), map.get(type.charAt(0)) + 4 - point);
			} else {
				map.put(type.charAt(1), map.get(type.charAt(1)) + point - 4);
			}
		}
		for (int t = 0; t < types.length; t++) {
			int a = map.get(types[t][0]);
			int b = map.get(types[t][1]);
			if (a >= b) {
				answer += types[t][0];
			} else {
				answer += types[t][1];
			}
		}
		return answer;
	}
}

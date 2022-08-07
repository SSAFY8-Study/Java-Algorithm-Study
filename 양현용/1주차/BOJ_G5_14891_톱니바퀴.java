

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
@author 양현용
@since 2022. 8. 6.
@see
@git
@performance
@category #시뮬레이션
@note */

public class BOJ_G5_14891_톱니바퀴 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	
	static List<Integer> gear;
	static Map<Integer,List<Integer>> gears;
	static int gearNum,turnD, result;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		
		gears = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < 4; i++) {
			gear = new LinkedList<>();
			String line = input.readLine();
			for (int j = 0; j < 8; j++) {
				gear.add(line.charAt(j) - '0');
			}
			gears.put(i, gear);
		}

//		System.out.println(gears); // 0:N극 1:S극
		
		int k = Integer.parseInt(input.readLine());
		for (int i = 0; i < k; i++) {
			tokens = new StringTokenizer(input.readLine());
			gearNum = Integer.parseInt(tokens.nextToken())-1;
			turnD = Integer.parseInt(tokens.nextToken());
			
			goLeft(gearNum-1,turnD*-1);
			goRight(gearNum+1,turnD*-1);
			rotate(gearNum,turnD);
		}		
		
		result=0;
		for(int i=0;i<4;i++) {
			result+=gears.get(i).get(0)*Math.pow(2, i);
		}
		System.out.println(result);
	}
	
	static void goLeft(int idx,int dir) {
		if(!isIn(idx))
			return;
		if(gears.get(idx).get(2)!=gears.get(idx+1).get(6)) {
			goLeft(idx-1,dir*-1);
			rotate(idx,dir);
		}
		else return;
	}
	
	static void goRight(int idx, int dir) {
		if(!isIn(idx))
			return;
		if(gears.get(idx).get(6)!=gears.get(idx-1).get(2)) {
			goRight(idx+1,dir*-1);
			rotate(idx,dir);
		}
		else return;
	}
	
	static void rotate(int idx,int dir) {
		if (dir == -1) {
			gears.get(idx).add(gears.get(idx).size(),gears.get(idx).get(0));
			gears.get(idx).remove(0);
		} else if (dir == 1) {
			gears.get(idx).add(0, gears.get(idx).get(gears.get(idx).size() - 1));
			gears.get(idx).remove(gears.get(idx).size() - 1);
		}
	}
	
	static boolean isIn(int idx) {
		return 0<=idx && idx<4;
	}
}
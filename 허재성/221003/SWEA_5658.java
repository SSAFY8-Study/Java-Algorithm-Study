import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class SWEA_5658 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int T;
	static int N, K;
	static int len;
	static char[] input;
	
	static Set<Integer> set;
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++) {
			set = new HashSet<>();
			list = new ArrayList<>();
			
			tokens = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			len = N / 4;
			
			input = new char[N];
			input = bf.readLine().toCharArray();
		
			for(int i = 0; i < len; i++) {
				rotate();
				
//				System.out.println(i + 1 + " 회전");
//				for(int j = 0; j < N; j++)
//					System.out.print(input[j]);
//				System.out.println();
				
				
				sides();
			}
		
			Collections.sort(list, Collections.reverseOrder());
			sb.append(String.format("#%d %d\n", t, list.get(K - 1)));
		}
		System.out.print(sb);
	}
	
	static int hexToDec(char[] hex) {
		int ret = 0;
		int mul = 1;
		
		for(int i = len - 1; i >= 0; i--) {
			if('0' <= hex[i] && hex[i] <= '9')
				ret += (hex[i] - '0') * mul;
			else if('A' <= hex[i] && hex[i] <= 'F')
				ret += (hex[i] - 'A' + 10) * mul;
			
			mul *= 16;
		}
		
		return ret;
	}
	
	//	1회전
	static void rotate() {
		char[] tmp = new char[N];
		
		for(int i = 0; i < N - 1; i++)
			tmp[i + 1] = input[i];
		tmp[0] = input[N - 1];
		
		input = tmp;
	}
	
	//	각 변 값 뽑아내기
	static void sides() {
//		char[] num1 = new char[len];
//		char[] num2 = new char[len];
//		char[] num3 = new char[len];
//		char[] num4 = new char[len];
//	
//		for(int i = 0; i < len; i++)
//			num1[i] = input[i];
//		for(int i = len; i < len * 2; i++)
//			num2[i] = input[i];
//		for(int i = len * 2; i < len * 3; i++)
//			num3[i] = input[i];
//		for(int i = len * 3; i < len * 4; i++)
//			num4[i] = input[i];
		
		char[][] hexNums = new char[4][len];
		int[] nums = new int[4]; 

		for(int i = 0; i < 4; i++) {
			for(int j = len * i; j < len * (i + 1); j++) 
				hexNums[i][j - (len * i)] = input[j];
		
			nums[i] = hexToDec(hexNums[i]);
		
			if(!set.contains(nums[i])) {
				set.add(nums[i]);
				list.add(nums[i]);
			}
		}
	}
}

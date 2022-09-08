package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_ssafy문제풀이_ShuffleOMatic {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder out = new StringBuilder();
	static StringTokenizer st;

	static int minCnt;		// 정답
	
	public static boolean isSorted(int[] cards, int n) {		// 오름차순, 내림차순 정렬되어 있는지 검사
		int diff = cards[0]-cards[1];
		
		if(diff == -1 || diff == 1) {
			for(int i=1; i<n-1; i++) {
				if(cards[i]-cards[i+1] != diff)					// 인접한 원소 차이가 1/-1가 아니라면 정렬되어 있지 않은 상태
					return false;
			}
		}else {					// |첫번째 원소값 - 두번째 원소값| != 1 => 정렬되지 않은 상태 
			return false;
		}
		return true;
	}
	
	public static void shuffle(int[] cards, int start, int n) {
		int temp;
		for(int left = start; left<n-start; left+=2) {
			temp = cards[left];
			cards[left] = cards[left+1];
			cards[left+1] = temp;
		}
	}
	
	public static void permutations(int cnt, int[] cards, int n) {
		if(cnt >= minCnt)	return;		// 가지치기
		
		if(isSorted(cards, n)) {				// 정렬여부 판별
			minCnt = Math.min(minCnt, cnt);		// 정답 업데이트
			return;
		}
		
		if(cnt == 5) return;		// 5번 초과 시 실패
		
		int half = n/2;
		
		int[] nxtCards = Arrays.copyOf(cards, n);
		for(int x=1; x<n; x++) {
			shuffle(nxtCards, Math.abs(x-half), n);
			permutations(cnt+1, nxtCards, n);
		}
	}
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; t++) {
			int n = Integer.parseInt(in.readLine());
			int[] cards = new int[n];
			
			st = new StringTokenizer(in.readLine());
			for(int i=0; i<n; i++)
				cards[i] = Integer.parseInt(st.nextToken());
			
			minCnt = Integer.MAX_VALUE;
			permutations(0, cards, n);
			
			if(minCnt == Integer.MAX_VALUE)	minCnt = -1;
			
			out.append("#"+t+" "+minCnt+"\n");
		}
		System.out.print(out);
	}

	// REMOVE_START
	private static String src = "5\r\n" + 
			"4\r\n" + 
			"1 2 3 4 \r\n" + 
			"4\r\n" + 
			"4 2 3 1 \r\n" + 
			"6\r\n" + 
			"6 5 4 2 3 1 \r\n" + 
			"8\r\n" + 
			"6 1 4 7 2 5 8 3 \r\n" + 
			"12\r\n" + 
			"2 7 4 1 3 5 8 10 12 9 6 11 ";
	// REMOVE_END
}
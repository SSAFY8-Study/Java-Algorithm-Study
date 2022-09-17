package ws_hw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_ssafy문제풀이_낚시터자리잡기 {

	static int n, exits[][] = new int[3][2];
	static int minDis;
	
	public static int searchSeat(int eNum, int fisherCnt, int accDis, boolean[] visitedS, boolean lFirst) {
		int nxtAccDis = accDis;
		
		int left = eNum, right = eNum;
		
		// 현재 출구 위치 eNum을 기준으로 한 칸씩 양쪽으로, 대칭적으로 탐색
		for(; left >= 0 && right < n; left--, right++) {
			
			if(fisherCnt == 0)	break;
			
			if(fisherCnt == 1 && !visitedS[left] && !visitedS[right]) {
				if(lFirst) {
					visitedS[left] = true;
					fisherCnt--;
					nxtAccDis += (eNum-left);
				}else {
					visitedS[right] = true;
					fisherCnt--;
					nxtAccDis += (right-eNum);
				}
			}
			
			if(fisherCnt > 0 && !visitedS[left]) {
				visitedS[left] = true;
				fisherCnt--;
				nxtAccDis += (eNum-left);
			}
			
			if(fisherCnt > 0 && !visitedS[right]) {
				visitedS[right] = true;
				fisherCnt--;
				nxtAccDis += (right-eNum);
			}
			
		}
		
		// 남은 것 처리
		if(left < 0) {
			for(; right < n && fisherCnt > 0; right++) {
				if(!visitedS[right]) {
					visitedS[right] = true;
					fisherCnt--;
					nxtAccDis += (right-eNum);
				}
			}
		} else {
			for(; left >= 0 && fisherCnt > 0; left--) {
				if(!visitedS[left]) {
					visitedS[left] = true;
					fisherCnt--;
					nxtAccDis += (eNum-left);
				}
			}
		}
		
		return nxtAccDis;
	}
	
	public static void dfs(int idx, int accDis, boolean[] visitedG, boolean[] visitedS) {
		// 가지치기
		if(accDis >= minDis)	return;
		
		if(idx == 3) {
			minDis = Math.min(minDis, accDis);
			return;
		}
		
		boolean[] nxtVisitedS;
		int nxtAccDis = 0;
		
		for(int i=0; i<3; i++) {
			if(!visitedG[i]) {
				visitedG[i] = true;
				
				// 현재 게이트에 대기 인원에 상관없이 왼쪽 먼저 시작하는 경우
				nxtVisitedS = Arrays.copyOf(visitedS, n);
				nxtAccDis = searchSeat(exits[i][0], exits[i][1], accDis, nxtVisitedS, true);
				dfs(idx+1, nxtAccDis, visitedG, nxtVisitedS);

				// 현재 게이트에 대기 인원이 짝수일 경우, 오른쪽 먼저 시작하는 경우도 고려
				if(exits[i][1] % 2 ==0) {
					nxtVisitedS = Arrays.copyOf(visitedS, n);
					nxtAccDis = searchSeat(exits[i][0], exits[i][1], accDis, nxtVisitedS, false);
					dfs(idx+1, nxtAccDis, visitedG, nxtVisitedS);
				}
				
				visitedG[i] = false;
			}
		}
		
		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		
		
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(in.readLine());
			
			int totalPeople = 0;
			for(int i = 0; i<3; i++) {
				st = new StringTokenizer(in.readLine());
				exits[i][0] = Integer.parseInt(st.nextToken())-1;
				exits[i][1] = Integer.parseInt(st.nextToken());
				totalPeople += exits[i][1];
			}
			
			minDis = Integer.MAX_VALUE;
			
			dfs(0, 0, new boolean[3], new boolean[n]);
			minDis += totalPeople;
			
			out.append("#"+t+" "+minDis+"\n");
		}
		
		System.out.print(out);

	}

}

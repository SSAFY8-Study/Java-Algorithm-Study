package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/11660 구간 합 구하기 5
 * @difficulty Silver1
 * @category #누적합
 *
 */
public class BOJ_11660 {

	static int[][] map;
	static int[][] memo;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]); // 표의 크기 N
		int M = Integer.parseInt(str[1]); //구해야 하는 횟수 M
		map = new int [N][N];
		memo = new int [N+1][N+1];
		
		for(int i=0; i<N+1; i++) {
			memo[i][0]=0;
			memo[0][i]=0;
		}
		
		for(int i=0; i<N; i++) {
			String[] str2 = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(str2[j]);
			}
		}
		
		/* 부분합 완성 */
		for(int i=1; i<=N; i++) {
			for(int k=1; k<=N; k++) {
				for(int j=1; j<=k;j++) {
					memo[i][k]+=map[i-1][j-1]; //해당 줄에 있는 것 합산
					//System.out.println(memo[i-1][j]+" : "+map[i-1][j-1]);
				}
				//System.out.println("===");
				memo[i][k]+=memo[i-1][k]; //위의 줄 더하기
			}
			
		}
//		System.out.println();
//		for(int i=0;i<N+1; i++) {
//			for(int j=0; j<N+1; j++) {
//				System.out.print(memo[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
		
		//합을 구해보자
		for(int i=0; i<M;i++) {
			String[] str3 = br.readLine().split(" ");
			int x1 = Integer.parseInt(str3[0]);
			int y1 = Integer.parseInt(str3[1]);
			int x2 = Integer.parseInt(str3[2]);
			int y2 = Integer.parseInt(str3[3]);
			
			//System.out.println(memo[x2][y2]+ " : "+memo[x2][y1-1]+ " : "+memo[x1-1][y2]+ " : "+memo[x1-1][y1-1]);
			
			sb.append(memo[x2][y2]-memo[x2][y1-1]-memo[x1-1][y2]+memo[x1-1][y1-1]+"\n");
			//큰 사각형 - 부분사각형1(영역 해당안되는 부분) - 부분사각형2 + 겹치는 부분
		}
		System.out.println(sb.toString());
		
	}

}

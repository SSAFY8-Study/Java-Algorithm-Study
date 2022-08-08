package on0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 양현용
@since 2022. 8. 7.
@see
@git
@youtube
@performance
@category #완전탐색
@note */
public class BOJ_S1_2468_안전영역 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] arr;
	static boolean[][] visited;
	static int max,min,h, result, n, cnt;
	static int[][] deltas= {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		n=Integer.parseInt(input.readLine());
		arr=new int[n][n];
		max=0;		//최소 높이 1
		min=101;	//최대 높이 100
		result=0;
		
		//arr 배열 초기화
		for(int i=0;i<n;i++) {
			tokens=new StringTokenizer(input.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(tokens.nextToken());
				max=Math.max(max, arr[i][j]);
				min=Math.min(min, arr[i][j]);
			}
		}
		
		//초기에 물에 잠긴 배열 설정
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				arr[i][j]-=min;
			}
		}

		//배열이 전부 잠길때 까지 진행
		for(int t=0;t<max-min;t++) {
			cnt=0;
			visited=new boolean[n][n];
			
			//안전 영역 개수 체크
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(arr[i][j]>0 && !visited[i][j]) {
						cnt+=search(i,j);
					}
				}
			}
			result = Math.max(result, cnt);

			//물에 잠긴 영역 재설정
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(arr[i][j]!=0)
						arr[i][j]-=1;
				}
			}
		}
		
		//모든 영역의 높이가 같을 때 예외 처리
		if(result==0) {
			System.out.println(1);
		}
		else {
			System.out.println(result);
		}
	}
	
	static int search(int x, int y) {
		visited[x][y]=true;
		for(int i=0;i<deltas.length;i++) {
			int nx=x+deltas[i][0];
			int ny=y+deltas[i][1];
			if(isIn(nx,ny)) {
				if(!visited[nx][ny]&&arr[nx][ny]>0) {
					search(nx,ny);
				}
			}
		}
		return 1;
	}
	
	static boolean isIn(int x,int y) {
		return 0<=x && x<n && 0<=y && y<n;
	}
}
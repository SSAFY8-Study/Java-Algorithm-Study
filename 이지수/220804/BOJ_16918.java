package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/16918 봄버맨
 * @performance 22680	704
 * @memo 매우 비효율적 ?
 */
public class BOJ_16918 {

	static String[][] map;
	static boolean[][] boom;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static int R,C,N;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		N = Integer.parseInt(str[2]);
		
		map = new String[R][C];
		boom = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String[] input = br.readLine().split("");
			for(int j=0; j<C; j++) {
				map[i][j]=input[j];
				boom[i][j]=false;//초기화
				
				if(map[i][j].equals("O")) {
					boom[i][j]=true;
				}
			}
		}
		
		//1초동안은 아무런 일도 일어나지 않기 때문에 time=2부터 시작한다.
		//다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
		//1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
		for(int time=2; time<=N; time++) {
			if(time%2==0) { //
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						map[i][j]="O"; //모든 칸에 폭탄을 설치한다.
					}
				}
			}
			
			else { //-2초 전에 설치된 폭발이 모두 폭발한다. (상하좌우)
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(boom[i][j]==true) {
							map[i][j]=".";//폭발위치 평지로 만듬
							for(int k=0; k<deltas.length; k++) {
								int nr = i+deltas[k][0];
								int nc = j+deltas[k][1];
								if(isIn(nr,nc)) {
									map[nr][nc]=".";
								}
							}
						}
					}
				}
				
				//폭발할지 안할지 여부 결정
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(map[i][j].equals("O")){ //1초전 설치된 것을 폭발하기 일보직전으로 만듬
							boom[i][j]=true;
						}
						else {
							boom[i][j]=false;
						}
					}
				}
			}
			
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<R && b>=0 && b<C;
	}

}

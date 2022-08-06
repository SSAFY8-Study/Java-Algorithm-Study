/**
 * @author seungheecho
 * @number bj14891
 * @see https://www.acmicpc.net/problem/14891
 * @performance 11956	80
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] gear = new int[4][8];
		int[] check = new int[4];
		
		for(int i=0; i<4; i++) {
			String[] s = br.readLine().split("");
			for(int j=0; j<8; j++) {
				gear[i][j] = Integer.parseInt(s[j]);
			}
		}
		int N = Integer.parseInt(br.readLine());
		for(int n=0; n<N; n++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st2.nextToken())-1;
			int dir = Integer.parseInt(st2.nextToken());
			
//			gearCheck(gearNum, dir);
			check[gearNum] = dir;
			//왼쪽 바퀴 검사
			int i = gearNum-1;
			while(i>=0 && check[i]==0) {
				if(gear[i][2]!=gear[i+1][6]) {
					check[i] = check[i+1]*(-1);
				}else {
					check[i]=0;
				}
				i--;
			}
			//오르쪽바퀴 검사
			i = gearNum+1;
			while(i<=3 && check[i]==0) {
				if(gear[i][6] != gear[i-1][2]) {
					check[i] = check[i-1]*(-1);
				}else {
					check[i] = 0;
				}
				i++;
			}

			//회전
			for(int k=0; k<4; k++) {
				if(check[k]!=0) {
					int[] temp = new int[8];
					
					int index;
					for(int j=0; j<8; j++) {
						index = j+check[k];
						
						if(index==-1) {
							index = 7;
						}else if(index==8) {
							index = 0;
						}
						
						temp[index] = gear[k][j];
					}
					gear[k] = temp;
				}
			}

			for(int k=0; k<4; k++) {
				check[k] = 0;
			}
			
		}
		//결과 출력
		int result = 0;
		for(int r=0; r<4; r++) {
			int num = gear[r][0];
			
			if(num == 1) {
				result += Math.pow(2, r);
			}
		}
		
		System.out.println(result);
	}
	

}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 톱니바퀴 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] wheels = new String[4];
		for(int i=0;i<4;i++) {
			wheels[i] = br.readLine();
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int t=0;t<k;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken())-1; // 톱니바퀴 번호
			int d = Integer.parseInt(st.nextToken());//회전 방향
			int tmpd = d; // tmpd : 인접 톱니 회전 시 사용할 방향 변수
			
			int initStatus = Integer.parseInt(wheels[n].substring(6,7)); // 기준 톱니바퀴 초기상태의 6번 저장
			
			// n 을 기준으로 오른쪽
			for(int i=n;i<4;i++) {
				int status = Integer.parseInt(wheels[i].substring(2, 3));
				if(tmpd==-1) {
					// 반시계
					wheels[i] = (wheels[i].substring(1, 8)+wheels[i].substring(0, 1));
				}else {
					// 시계
					wheels[i] = (wheels[i].substring(7, 8)+wheels[i].substring(0, 7));
				}
				
				if(i+1<4&&status == Integer.parseInt(wheels[i+1].substring(6, 7))) {
					break;
				}
				tmpd *= -1; // 다음은 반대 방향으로 회전
			}
			
			tmpd = d*-1;
			if(n-1>=0&&initStatus!=Integer.parseInt(wheels[n-1].substring(2, 3))) {
				for(int i=n-1;i>=0;i--) {
					int status = Integer.parseInt(wheels[i].substring(6, 7));
					if(tmpd==-1) {
						// 반시계
						wheels[i] = (wheels[i].substring(1, 8)+wheels[i].substring(0, 1));
					}else {
						// 시계
						wheels[i] = (wheels[i].substring(7, 8)+wheels[i].substring(0, 7));
					}
					
					if(i-1>=0&&status == Integer.parseInt(wheels[i-1].substring(2, 3))) {
						break;
					}
					tmpd *= -1; // 다음은 반대방향으로 회전
				}
			}
		}
		
		int sum = 0;
		int score = 1;
		for(int i=0;i<4;i++) {
			sum += score*(wheels[i].charAt(0)-'0');
			score *= 2;
		}
		System.out.println(sum);
	}
}
package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/14719 빗물
 * @performance
 * @difficulty Gold5
 *
 */
public class BOJ_14719 {
	
	static int[] map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int H = Integer.parseInt(str[0]); //세로 (나올 수 있는 최대높이)
		int W = Integer.parseInt(str[1]); //가로 (영역의 끝)
		map = new int[W];
		
		String[] str2 = br.readLine().split(" ");
		for(int i=0;i<str2.length;i++) {
			map[i]=Integer.parseInt(str2[i]);
		}
		
		int cnt=0; //최종 빗물
		
		for(int i=1; i<W-1; i++) { //경계는 제외
			int left = -1;
			int right = -1;
			
			for(int j=0;j<i;j++) {
				left = Math.max(left, map[j]);
			} //현재 위치(map[i])에서 왼쪽 영역의 최대 높이
			
			for(int j=i+1;j<W;j++) {
				right = Math.max(right, map[j]);
			}//오른쪽
			
			if(left==0 || right==0 || left<=map[i] || right<=map[i]) {
				continue; //양옆에 블록의 막힘이 없어서 빗물을 채울수 없으므로
			}//혹은 본인이 더 높은 경우?
			
			//System.out.println(left+" : "+right);
			
			int fill = Math.min(left, right);
			//둘 중에 최소 높이까지 빗물을 채워야 한다.
			
			cnt += (fill-map[i]);
		}
		System.out.println(cnt);
		
	}

}
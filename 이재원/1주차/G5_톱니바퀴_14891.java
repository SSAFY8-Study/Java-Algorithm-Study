import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_톱니바퀴_14981 {
	static int[][] gears = new int[4][8];
	static int[] spins = new int[4];
	static int score = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 4; i++) {
			String line = br.readLine();
			for(int j = 0; j < 8; j++) {
				gears[i][j] = line.charAt(j) - '0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			spinGear(num, dir, 99);
		}
		
		for(int i = 0; i < 4; i++) {
			if(gears[i][spins[i]%8] == 1) {
				score += (int) Math.pow(2, i);
			}
		}
		
		System.out.println(score);
	}
	
	static void spinGear(int num, int dir, int prev) {
		int left = num - 1;
		int right = num + 1;
		
		if(left != prev && isIn(left) && (gears[num][(spins[num]+6)%8] != gears[left][(spins[left]+2)%8])) {
			spinGear(left, -dir, num);
		}
		
		if(right != prev && isIn(right) && (gears[num][(spins[num]+2)%8] != gears[right][(spins[right]+6)%8])) {
			spinGear(right, -dir, num);
		}
		
		if(dir == 1) {
			spins[num] += 7;
		}
		else {
			spins[num] += 1;
		}
	}
	
	static boolean isIn(int num) {
		return 0 <= num && num < 4;
	}
}

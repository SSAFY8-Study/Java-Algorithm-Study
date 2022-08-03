import java.util.Scanner;

public class n14891_톱니바퀴 {
	public static class Cog{
		int[] tooth;
		
		public Cog(int[] tooth) {
			this.tooth = tooth;
		}
	}
	
	static Cog[] cog = new Cog[5];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int j = 1; j <= 4; j++) {
			int[] tooth = new int[8];
			String str = sc.next();
			for(int i = 0; i < 8; i++) {
				char c = str.charAt(i);
				tooth[i] = Character.getNumericValue(c);
			}
			cog[j] = new Cog(tooth);
		}
		
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			int c = sc.nextInt();
			int d = sc.nextInt();
			
			boolean[] sn = new boolean[3];
			if(cog[1].tooth[2] == cog[2].tooth[6]) sn[0] = true;
			if(cog[2].tooth[2] == cog[3].tooth[6]) sn[1] = true;
			if(cog[3].tooth[2] == cog[4].tooth[6]) sn[2] = true;
			
			if(c == 1) {
				turn(1, d);
				if(!sn[0]) {
					turn(2, -1*d);
					if(!sn[1]) {
						turn(3, d);
						if(!sn[2]) {
							turn(4, -1*d);
						}
					}
				}
			}else if(c == 2) {
				turn(2, d);
				if(!sn[0]) {
					turn(1, -1 * d);
				}
				if(!sn[1]) {
					turn(3, -1 * d);
					if(!sn[2]) {
						turn(4, d);
					}
				}
				
			}else if(c == 3) {
				turn(3, d);
				if(!sn[2]) {
					turn(4, -1 * d);
				}
				if(!sn[1]) {
					turn(2, -1 * d);
					if(!sn[0]) {
						turn(1, d);
					}
				}
			}else {
				turn(4, d);
				if(!sn[2]) {
					turn(3, -1*d);
					if(!sn[1]) {
						turn(2, d);
						if(!sn[0]) {
							turn(1, -1*d);
						}
					}
				}
			}
		}
		
		int sum = 0;
		if(cog[1].tooth[0] == 1) sum++;
		if(cog[2].tooth[0] == 1) sum += 2;
		if(cog[3].tooth[0] == 1) sum += 4;
		if(cog[4].tooth[0] == 1) sum += 8;
		
		System.out.println(sum);
	}
	
	public static void turn(int n, int d) {
		int temp = cog[n].tooth[7];
		if(d == 1) {
			for(int i = 7; i >= 1; i--) {
				cog[n].tooth[i] = cog[n].tooth[i-1];
			}
			cog[n].tooth[0] = temp;
		}else {
			temp = cog[n].tooth[0];
			for(int i = 0; i < 7; i++) {
				cog[n].tooth[i] = cog[n].tooth[i+1];
			}
			cog[n].tooth[7] = temp;
		}
	}
}

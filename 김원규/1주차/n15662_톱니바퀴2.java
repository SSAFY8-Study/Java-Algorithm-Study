import java.util.Scanner;

public class n15662_톱니바퀴2 {
	public static class Cog{
		int[] tooth;
		
		public Cog(int[] tooth) {
			this.tooth = tooth;
		}
	}
	
	static Cog[] cog;
	static int gear;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		gear = sc.nextInt();
		cog = new Cog[gear+1];
		
		for(int j = 1; j <= gear; j++) {
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
			turn(c, d, c);
		}
		
		int sum = 0;
		for(int i = 1; i <= gear; i++) {
			if(cog[i].tooth[0] == 1) sum++;
		}
		System.out.println(sum);
	}
	
	public static void turn(int n, int d, int from) {
		if(n != 1) {
			if(cog[n-1].tooth[2] != cog[n].tooth[6] && n-1 != from) turn(n-1, -1*d, n);
		}
		if(n != gear) {
			if(cog[n+1].tooth[6] != cog[n].tooth[2] && n+1 != from) turn(n+1, -1*d, n);
		}
		
		int temp = cog[n].tooth[7];
		if(d == 1) {
			for(int i = 7; i >= 1; i--) cog[n].tooth[i] = cog[n].tooth[i-1];
			cog[n].tooth[0] = temp;
		}else {
			temp = cog[n].tooth[0];
			for(int i = 0; i < 7; i++) cog[n].tooth[i] = cog[n].tooth[i+1];
			cog[n].tooth[7] = temp;
		}
	}
}

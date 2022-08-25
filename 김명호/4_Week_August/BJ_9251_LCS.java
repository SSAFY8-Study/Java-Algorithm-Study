import java.io.*;
import java.lang.*;
import java.util.*;

public class BJ_9251_LCS{

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int[][] arr;
	public static String[] datas1,datas2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		datas1 = br.readLine().split("");
		datas2 = br.readLine().split("");
		arr = new int[datas1.length+1][datas2.length+1];
		dp();
		bw.write(arr[datas1.length][datas2.length]+"\n");
		bw.flush();
		bw.close();
	}

	private static void dp() {
		for (int c = 1; c <= datas2.length; c++) {
			for (int r = 1; r <= datas1.length; r++) {
				if(datas1[r-1].equals(datas2[c-1])) {
					arr[r][c] = arr[r-1][c-1]+1;
				}else {
					arr[r][c] = Math.max(arr[r][c-1], arr[r-1][c]);
				}
			}
		}
		
	}

	
}
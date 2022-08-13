package algo;

import java.util.Scanner;

public class substring_6550 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			char[] A = sc.next().toCharArray();
			char[] B = sc.next().toCharArray();
			int pivot = 0;
			for (int i = 0; i < B.length; i++) {
				if (pivot == A.length)
					break;
				if (B[i] == A[pivot])
					pivot++;
			}
			if (pivot < A.length)
				System.out.println("NO");
			else
				System.out.println("YES");
		}

	}

}

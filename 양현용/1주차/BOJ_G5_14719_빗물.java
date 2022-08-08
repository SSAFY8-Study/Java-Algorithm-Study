import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14719_빗물 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./input.txt"));
		tokens = new StringTokenizer(input.readLine());
		int h = Integer.parseInt(tokens.nextToken());
		int w = Integer.parseInt(tokens.nextToken());

		tokens = new StringTokenizer(input.readLine());
		arr = new int[w];
		for (int i = 0; i < w; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		int left = 0;
		int right = w - 1;
		int maxL = arr[left];
		int maxR = arr[right];
		int cnt = 0;

		while (true) {
			if (left > right)
				break;
			maxL = Math.max(maxL, arr[left]);
			maxR = Math.max(maxR, arr[right]);
			if (maxL >= maxR) {
				cnt += maxR - arr[right];
				right--;
			} else {
				cnt += maxL - arr[left];
				left++;
			}
		}
		System.out.println(cnt);
	}
}

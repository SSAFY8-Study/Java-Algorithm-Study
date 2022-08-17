import java.util.Scanner;

public class gear_14891 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(input);
		char[][] gears = new char[4][8];
		for (int i = 0; i < 4; i++) {
			gears[i] = sc.next().toCharArray();
		}
		int reps = sc.nextInt();
		for (int i = 0; i < reps; i++) {
			int pivot = sc.nextInt() - 1;
			int is_clock = sc.nextInt();
			int tmp_left = pivot;
			int tmp_right = pivot;
			int tmp_cl = is_clock;
			int tmp_cr = is_clock;
			gears[pivot] = is_clock == 1 ? clockwise(gears[pivot]) : counter_clockwise(gears[pivot]);
			while (check_left(tmp_left, gears)) {
				tmp_left--;
				gears[tmp_left] = tmp_cl == 1 ? counter_clockwise(gears[tmp_left]) : clockwise(gears[tmp_left]);
				tmp_cl = tmp_cl == 1 ? 0 : 1;
			}
			while (check_right(tmp_right, gears)) {
				tmp_right++;
				gears[tmp_right] = tmp_cr == 1 ? counter_clockwise(gears[tmp_right]) : clockwise(gears[tmp_right]);
				tmp_cr = tmp_cr == 1 ? 0 : 1;
			}
		}
		int score = 0;
		for (int i = 0; i < gears.length; i++) {
			if (gears[i][0] == '1')
				score += Math.pow(2, i);
		} // score
		System.out.println(score);
	}

	static boolean is_in(int idx) {
		return idx > -1 && idx < 4;
	} // is it not out of index

	static char[] clockwise(char[] input) {
		char[] output = new char[input.length];
		output[0] = input[input.length - 1];
		for (int i = 1; i < output.length; i++)
			output[i] = input[i - 1];
		return output;
	} // run it clockwise

	static char[] counter_clockwise(char[] input) {
		char[] output = new char[input.length];
		output[input.length - 1] = input[0];
		for (int i = 0; i < output.length - 1; i++)
			output[i] = input[i + 1];
		return output;
	} // run it counter clockwise

	static boolean check_right(int idx, char[][] gears) {
		return is_in(idx + 1) && gears[idx][2] == (gears[idx + 1][6]);
	} // if can do right

	static boolean check_left(int idx, char[][] gears) {
		return is_in(idx - 1) && gears[idx - 1][2] == (gears[idx][6]);
	} // if can do left

	static String input = "10010011\r\n" + 
			"01010011\r\n" + 
			"11100011\r\n" + 
			"01010101\r\n" + 
			"8\r\n" + 
			"1 1\r\n" + 
			"2 1\r\n" + 
			"3 1\r\n" + 
			"4 1\r\n" + 
			"1 -1\r\n" + 
			"2 -1\r\n" + 
			"3 -1\r\n" + 
			"4 -1";
}

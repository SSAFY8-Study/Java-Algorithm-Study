import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_S3_01254_팰딘드롬만들기 {

	// aaabbbaa - 반례 / abbcecb - 반례
//		public static void main(String[] args) throws IOException {
//		// inputs - String 한줄
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		char[] inputs = reader.readLine().toCharArray();
//		
//		// 앞abcdba과 뒤를 비교하기 위해서 i는 앞에 인덱스, j는 뒤에 인덱스로 접근
//		int j = inputs.length - 1;
//		boolean serial = true; 
//		int addLen = 0;
//		for (int i = 0; i < inputs.length && i <= j; i++) {
//			if (i != 0 && inputs[i] != inputs[i - 1]) {
//				serial = false;
//			}
//			if (inputs[i] == inputs[j]) {
//				j--;
//			}else {
//				if (serial) {
//					addLen = (i + 1) - (inputs.length - 1 - j);
//				}else {
//					addLen = i + 1;
//					j = inputs.length - 1;
//				}
//			}
//		}
//		System.out.println(inputs.length + addLen);
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		char[] inputs = reader.readLine().toCharArray();
		// 총 길이를 위해 
		int i;
		// 처음부터 시작을 해서 만약 체크를 했을 때 나머지 팰딘드롬인지 확인 아닐 경우 다음 인덱스에서 
		for (i = 0; i < inputs.length; i++) {
			// 만약 팰딘드롬인 경우
			if (isChecked(i, inputs)) {
				break;
			}
		}
		// 팰딘드롬 시작 위치 + 나머지 문자열의 길이 = 앞까지는 팰딘드롬이 되지 못한 문자열이므로 뒤에 그만큼 붙어야함.
		System.out.println(i + inputs.length);
	}
	
	// 팰딘드롬인지 확인
	public static boolean isChecked(int start, char[] arr) { 
		int j = arr.length - 1;
		for (int i = start; i < arr.length && i <= j; i++) {
			if (arr[i] == arr[j]) {
				j--;
			}else {
				return false;
			}
		}
		return true;
	}
}

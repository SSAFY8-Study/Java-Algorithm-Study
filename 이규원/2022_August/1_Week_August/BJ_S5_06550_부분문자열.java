import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class bj_S5_06550_부분문자열 {
	public static void main(String[] argv) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		reader = new BufferedReader(new StringReader(str));
		String inputs = null;
		while((inputs = reader.readLine()) != null) {
			StringTokenizer tokens = new StringTokenizer(inputs);
			String s = tokens.nextToken();
			String t = tokens.nextToken();			
			int j = 0;
			for (int i = 0; i < t.length()&& j < s.length(); i++) {
				if (t.charAt(i) == s.charAt(j)) {
					j++;
				}
			}
			if (j == s.length()) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}
	}
	
	public static String str = "sequence subsequence\n"
			+ "person compression\n"
			+ "VERDI vivaVittorioEmanueleReDiItalia\n"
			+ "caseDoesMatter CaseDoesMatter";
}

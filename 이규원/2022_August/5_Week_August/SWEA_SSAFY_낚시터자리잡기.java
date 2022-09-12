import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_SSAFY_낚시터자리잡기 {
	static class Gate {
		int x;
		int p;

		public Gate(int x, int p) {
			super();
			this.x = x;
			this.p = p;
		}

		@Override
		public String toString() {
			return "Gate [x=" + x + ", p=" + p + "]";
		}
	}

	static int[][] cases = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } };
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	static int N;
	static Gate[] gates;

	public static void main(String[] args) throws NumberFormatException, IOException {
		reader = new BufferedReader(new StringReader(str));
		int T = Integer.parseInt(reader.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(reader.readLine());

			gates = new Gate[3];
			for (int g = 0; g < 3; g++) {
				tokens = new StringTokenizer(reader.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int p = Integer.parseInt(tokens.nextToken());
				gates[g] = new Gate(x - 1, p);
			}

			result = Integer.MAX_VALUE;
			for (int c = 0; c < cases.length; c++) {
				StartSetting(0, 0, new boolean[N], c);
			}
			output.append(String.format("#%d %d\n", t, result));
		}
		System.out.print(output);
	}

	static int result;

	private static void StartSetting(int cnt, int costs, boolean[] fishing, final int type) {
		if (cnt == 3) {
			result = Math.min(result, costs);
			return;
		}
		int person = gates[cases[type][cnt]].p;
		int cost = 0;
		while (person > 0) {
			if (person == 1) {
				int len = 0;
				int nx = gates[cases[type][cnt]].x;
				boolean[] cloneFishing = fishing.clone();
				for (;; len++) {
					int lx = nx + len * -1;
					int rx = nx + len * 1;

					if (lx >= 0 && !fishing[lx] && rx < N && !fishing[rx]) {
						fishing[lx] = true;
						StartSetting(cnt + 1, costs + cost + len + 1, fishing, type);
						fishing[lx] = false;
						cloneFishing[rx] = true;
						StartSetting(cnt + 1, costs + cost + len + 1, cloneFishing, type);
						return;
					} else {
						if (lx >= 0 && !fishing[lx]) {
							fishing[lx] = true;
							StartSetting(cnt + 1, costs + cost + len + 1, fishing, type);
							fishing[lx] = false;
							return;
						} else if (rx < N && !fishing[rx]) {
							fishing[rx] = true;
							StartSetting(cnt + 1, costs + cost + len + 1, fishing, type);
							fishing[rx] = false;
							return;
						}
					}
				}
			} else {
				int len = 0;
				int nx = gates[cases[type][cnt]].x;
				for (;; len++) {
					int lx = nx + len * -1;
					int rx = nx + len * 1;

					if (lx >= 0 && !fishing[lx]) {
						fishing[lx] = true;
						person--;
						break;
					} else if (rx < N && !fishing[rx]) {
						fishing[rx] = true;
						person--;
						break;
					}
					if (person == 1)
						break;
				}
				cost += len + 1;
			}
		}

	}

	static String str = "5\r\n" + "10\r\n" + "4 5\r\n" + "6 2\r\n" + "10 2\r\n" + "10\r\n" + "8 5\r\n" + "9 1\r\n"
			+ "10 2\r\n" + "24\r\n" + "15 3\r\n" + "20 4\r\n" + "23 7\r\n" + "39\r\n" + "17 8\r\n" + "30 5\r\n"
			+ "31 9\r\n" + "60\r\n" + "57 12\r\n" + "31 19\r\n" + "38 16\r\n" + "";
}

// 완탐
// 3!의 경우는 정해져 있음. 이는 그냥 하면 되는데
// 만약 거리가 같은 곳이 2군데면 우리는 이를 경우로 나눠서 접근해야 함
// 
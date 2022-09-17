import java.util.*;

public class programmers_L3_코딩테스트공부 {
	static int talp = 0;
	static int tcop = 0;
	static int[][] dps = new int[1001][1001];

	public int solution(int alp, int cop, int[][] problems) {
		for (int[] problem : problems) {
			if (talp < problem[0])
				talp = problem[0];
			if (tcop < problem[1])
				tcop = problem[1];
		}
		for (int[] dp : dps)
			Arrays.fill(dp, Integer.MAX_VALUE);
		dfs(alp, cop, 0, problems);
		return result;
	}

	static int result = Integer.MAX_VALUE;
	static int[] dp;

	static void dfs(int alp, int cop, int costs, int[][] problems) {
		if (result <= costs)
			return;
		if (dps[alp][cop] < costs)
			return;
		dps[alp][cop] = costs;
		if (alp >= talp && cop >= tcop) {
			result = Math.min(result, costs);
			return;
		}
		int minValue = Integer.MAX_VALUE;
		int minidx = -1;
		int minSum1 = 0;
		int minSum2 = 0;
		for (int p = 0; p < problems.length; p++) {
			if (problems[p][0] <= alp && problems[p][1] <= cop) {
				if (alp < talp && problems[p][2] > 0 || cop < tcop && problems[p][3] > 0)
					dfs(alp + problems[p][2], cop + problems[p][3], costs + problems[p][4], problems);
			} else {
				int sum1 = 0;
				int sum2 = 0;
				if (problems[p][0] > alp) {
					sum1 = problems[p][0] - alp;
				}
				if (problems[p][1] > cop) {
					sum2 = problems[p][1] - cop;
				}
				if (minValue > sum1 + sum2) {
					minidx = p;
					minSum1 = sum1;
					minSum2 = sum2;
					minValue = sum1 + sum2;
				}
			}
		}
		if (minSum1 + minSum2 > 0)
			dfs(alp + minSum1, cop + minSum2, costs + minSum1 + minSum2, problems);
	}
}
//백트레킹? +dp!!

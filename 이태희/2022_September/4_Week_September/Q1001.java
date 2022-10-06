package com.programmers.level2;

import java.util.Arrays;

public class Q1001 {

    static class Solution {

        public static int[] result = { -1 };
        public static int[] target;
        public static int max = -100;

        public void dfs(int[] info, int cnt, int n) {
            if(cnt == n+1) {
                int apeach = 0;
                int lion = 0;
                for(int i = 0; i <= 10; i++) {
                    if(info[i] != 0 || target[i] != 0) {
                        if(info[i] < target[i]) lion += 10 - i;
                        else apeach += 10 - i;
                    }
                }
                if(lion > apeach) { // [ -1 ] 을 위한 조건
                    if(lion - apeach >= max) {
                        result = target.clone();
                        max = lion - apeach;
                    }
                }
                return ;
            }
            for(int j = 0; j <= 10 && target[j] <= info[j]; j++) {
                target[j]++;
                dfs(info, cnt + 1, n);
                target[j]--;
            }
        }

        public int[] solution(int n, int[] info) {
            target = new int[11];
            dfs(info,1,n);
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(5, new int[] {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
    }

}

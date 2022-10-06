package com.programmers.level2;

public class Q1000 {

    public static int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        int N = queue1.length;
        int[] q1 = new int [4*N];
        for(int i=0; i<N; i++){
            sum1 += queue1[i];
            q1[i] = queue1[i];
        }
        long sum2 = 0;
        int[] q2 = new int [4*N];
        for(int i=0; i<N; i++){
            sum2 += queue2[i];
            q2[i] = queue2[i];
        }
        int tail1 = N, head1 = 0;
        int tail2 = N, head2 = 0;
        for(int i=0; i<3*N; i++) {
            if(sum1 == sum2) return i;
            if(sum1 > sum2){
                q2[tail2] = q1[head1];
                sum1 -= q1[head1];
                sum2 += q2[tail2];
                head1++;
                tail2++;
            } else if(sum1 < sum2){
                q1[tail1] = q2[head2];
                sum2 -= q2[head2];
                sum1 += q1[tail1];
                head2++;
                tail1++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] q1 = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 19, 1};
        int[] q2 = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        System.out.println(solution(q1, q2));
    }
}

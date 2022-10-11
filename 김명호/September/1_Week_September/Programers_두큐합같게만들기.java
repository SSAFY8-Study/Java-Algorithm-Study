import java.util.*;
import java.io.*;
import java.lang.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> queueA = new ArrayDeque<>();
        Queue<Integer> queueB = new ArrayDeque<>();
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        if ((sum1 + sum2) % 2 == 1)
            return -1;
        for (int i = 0; i < queue1.length; i++) {
            queueA.offer(queue1[i]);
        }
        for (int i = 0; i < queue2.length; i++) {
            queueB.offer(queue2[i]);
        }
        int count = 0;
        int max = (queue1.length + queue2.length) * 2;
        while (true) {
            if (sum1 == sum2) {
                return answer;
            } else {
                answer++;
                if (sum1 > sum2) {
                    int value = queueA.poll();
                    sum1 -= value;
                    sum2 += value;
                    queueB.offer(value);
                } else {
                    int value = queueB.poll();
                    sum1 += value;
                    sum2 -= value;
                    queueA.offer(value);
                }
                if (answer > max) return -1;
            }
        }
    }
}
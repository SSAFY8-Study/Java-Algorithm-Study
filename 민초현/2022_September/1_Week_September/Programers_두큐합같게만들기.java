import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        int maxCnt = 3*queue1.length;
        
        for(int n: queue1){
            sum1 += n;
            q1.offer(n);
        }
        for(int n: queue2){
            sum2 += n;
            q2.offer(n);
        }
        
        int pollNum, cnt = 0;
        
        while(true){
            
            if(sum1 > sum2){
                pollNum = q1.poll();
                
                q2.offer(pollNum);
                sum2 += pollNum;
                sum1 -= pollNum;
                
            } else if(sum1 < sum2){
                
                pollNum = q2.poll();
                
                q1.offer(pollNum);
                sum1 += pollNum;
                sum2 -= pollNum;
                
            } else{
                break;
            }
            if(++cnt > maxCnt){
                cnt = -1;
                break;
            }
        }
        
        return cnt;
    }
}
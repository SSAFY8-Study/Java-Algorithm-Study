import java.util.Arrays;

class Solution {
    static int minTime = Integer.MAX_VALUE;     // 정답
    static int maxAlgo, maxCoding;              // 필요한 최대 알고력, 코딩력
    // static int cnt;
    public void combinations(int start, int curAlgo, int curCoding, int curTime, int[][] problems){
        // if(++cnt >= 1000)
        //     return;
        
        // System.out.println(start+" curAlgo:"+curAlgo+" curCoding:"+curCoding+" curTime:"+curTime);
        
        // 가지치기
        if(curTime >= minTime)      return;
        
        // 목적 달성
        if(curAlgo >= maxAlgo && curCoding >= maxCoding){
            minTime = curTime;
            return;
        }
        
        // start부터 탐색
        for(int i=start, size = problems.length; i<size; i++){

            // 필요한 알고력, 코딩력이 만족 && 코딩공부를 하는 것보다 이 문제를 푸는게 더 효율적이면, 해당문제 풀기
            if(problems[i][0] <= curAlgo && problems[i][1] <= curCoding 
               && problems[i][4] <= problems[i][2]+problems[i][3]){
                
                combinations(i, curAlgo+problems[i][2], curCoding+problems[i][3], curTime+problems[i][4], problems);
                
            }
            else{       // 코딩공부하기
                
                int nxtAlgo = curAlgo;          // 다음 알고력
                int nxtCoding = curCoding;      // 다음 코딩력
                int nxtTime = curTime;          // 다음 누적시간
                
                if(problems[i][0] > curAlgo){
                    nxtAlgo = problems[i][0];
                    nxtTime += nxtAlgo-curAlgo;
                }
                if(problems[i][1] > curCoding){
                    nxtCoding = problems[i][1];
                    nxtTime += nxtCoding-curCoding;
                }
                combinations(i, nxtAlgo, nxtCoding, nxtTime, problems);
            }
        }
        
    }
    
    public int solution(int alp, int cop, int[][] problems) {
        // 필요한 최대 알고력, 코딩력 구함
        for(int[] problem: problems){
            maxAlgo = Math.max(maxAlgo, problem[0]);
            maxCoding = Math.max(maxCoding, problem[1]);
        }
        
        // 필요한 알고력, 코딩력 합이 가장 적은 순으로 정렬 
        Arrays.sort(problems, (p1, p2) -> Integer.compare(p1[0]+p1[1], p2[0]+p2[1]));
        
        combinations(0, alp, cop, 0, problems);
        
        return minTime;
    }
}
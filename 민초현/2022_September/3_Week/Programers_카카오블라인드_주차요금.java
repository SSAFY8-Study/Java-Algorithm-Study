import java.util.*;
import java.io.*;

class Solution {
    // 추가요금 계산 함수
    public int calFee(int term, int[] fees){
        return (int)(Math.ceil(1.0*term/fees[2])*fees[3]);
    }
    
    public int[] solution(int[] fees, String[] records) {
        StringTokenizer st;
        
        Map<Integer, Integer> totalTime = new TreeMap<>();      // 자동자 번호에 따른 누적 주차 시간(분)
        Map<Integer, Integer> startTime = new HashMap<>();      // out되지 않은 자동차의 입차시간(분)
        
        for(String record: records){
            st  = new StringTokenizer(record);
            
            String[] now = st.nextToken().split(":");           // HH:MM
            Integer num = Integer.parseInt(st.nextToken());     // 자동차번호
            String type = st.nextToken();                       // IN / OUT
            
            int timestamp = Integer.parseInt(now[0])*60 + Integer.parseInt(now[1]);     // 현재 시각을 분으로 환산
            
            if(type.equals("IN")){                  // 입차
                startTime.put(num, timestamp);      // 입차시간 저장
            }
            else{                                   // 출차
                // 기존에 누적시간에 추가시간 더함
                int curTime =  totalTime.containsKey(num) ? totalTime.get(num) : 0;     
                curTime += (timestamp - startTime.get(num));

                totalTime.put(num, curTime);        // 누적시간 더함
                startTime.remove(num);              // out되었으므로 제거
                
            }
        }
        
        int maxTimestamp = 23*60+59;    // 23:59 를 분으로 환산
        
        // 출차되지 않은 자동차는 23:59분에 출차
        for(Integer key: startTime.keySet()){
            if(totalTime.containsKey(key)){
                // 기존 누적시간에 추가시간 더함
                totalTime.put(key, totalTime.get(key) + maxTimestamp - startTime.get(key));
            } else{
                // 추가시간 더함
                totalTime.put(key, maxTimestamp - startTime.get(key));
            }
        }
        
        // 정답 배열
        int[] ans = new int[totalTime.size()];
        int idx = 0;
        for(Integer key : totalTime.keySet()){      // 누적시간들을 통해 요금 계ㅏㄴ
            ans[idx++] = (totalTime.get(key)<=fees[0]) ? fees[1] : calFee(totalTime.get(key)-fees[0], fees)+fees[1];
        }
        
        return ans;
    }
}